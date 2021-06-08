const moderatorsGroupId = "moderators";

$(function() {
	const chatWindow = $(".chat-window");
	const sockJsClient = new SockJS("/chat");
	const stompClient = Stomp.over(sockJsClient);

	const chat = chatWindow.find("#chat");
	const messagePrototype = chat.find("li.prototype");
	function addMessageFromUs(message) {
		addMessage(message, "me");
	}
	function addMessageFromThem(message) {
		addMessage(message, "you");
	}
	function addMessage(message, className) {
		const messageElement = messagePrototype.clone();
		messageElement.removeClass("prototype");
		messageElement.addClass(className);

		messageElement.find(".author-name").text(message.senderName);
		messageElement.find(".message").text(message.content);
		messageElement.find(".message-datetime").text(
			new Date(message.creationTimestamp).toLocaleString());

		messageElement.appendTo(chat);
	}

	let selectedRecipient;
	const selectedRecipientName = $("#selected-recipient-name");
	const selectedRecipientPhoto = $("#selected-recipient-photo");

	const imageBaseUrl = "/api/images/";
	const userCache = new Map();
	const userList = $(".chat-window .users");
	const userPrototype = userList.find("li.prototype");
	function onClickOnUser() {
		const userId = $(this).data("user-id");
		selectedRecipient = userCache.get(userId);
		selectedRecipientName.html(selectedRecipient.fullName);
		selectedRecipientPhoto.attr("src", imageBaseUrl + selectedRecipient.photoFileName);
		chatWindow.addClass("recipient-selected");
	}
	function addUser(user) {
		userCache.set(user.id, user);

		const userElement = userPrototype.clone();
		userElement.removeClass("prototype");

		userElement.data("user-id", user.id);
		userElement.find(".user-name").text(user.fullName);
		userElement.find(".profile-photo").attr("src", imageBaseUrl + user.photoFileName);

		userElement.click(onClickOnUser);

		userElement.prependTo(userList);
	}
	function fetchUserList() {
		if (isModerator)
			$.get(
				"/chat/users",
				function(usersResponse) {
					usersResponse.forEach(addUser);
				});
	}
	function fetchUser(userId) {
		if (isModerator && !userCache.has(parseInt(userId, 10)))
			$.get("/chat/users/" + userId, addUser);
	}

	function onMessageReceived(stompMessage) {
		const message = JSON.parse(stompMessage.body);
		if (message.senderId === currentUserId)
			addMessageFromUs(message);
		else {
			fetchUser(message.senderId);
			addMessageFromThem(message);
		}
	}
	function onStompClientConnected() {
		stompClient.subscribe("/chat/users/" + currentUserId + "/messages", onMessageReceived);
		fetchUserList();

		chatWindow.removeClass("not-connected");
	}
	function onStompClientConnectionError() {
		chatWindow.addClass("not-connected");
	}
	stompClient.connect({}, onStompClientConnected, onStompClientConnectionError);

	const messageContent = $(".message-content");
	$(".send-button").click(function(event) {
		event.preventDefault();

		const message = {
			senderId: currentUserId,
			recipientId: isModerator ? selectedRecipient.id : moderatorsGroupId,
			content: messageContent.val()
		};
		stompClient.send("/chat/send", {}, JSON.stringify(message));

		messageContent.val("");
	});
});
