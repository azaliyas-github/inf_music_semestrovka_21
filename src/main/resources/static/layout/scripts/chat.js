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

	function onMessageReceived(stompMessage) {
		const message = JSON.parse(stompMessage.body);
		if (message.senderId === currentUserId)
			addMessageFromUs(message);
		else
			addMessageFromThem(message);
	}
	function onStompClientConnected() {
		stompClient.subscribe("/chat/users/" + currentUserId + "/messages", onMessageReceived);
		chatWindow.removeClass("not-connected");
	}
	function onStompClientConnectionError() {
		chatWindow.addClass("not-connected");
	}
	stompClient.connect({}, onStompClientConnected, onStompClientConnectionError);

	let selectedRecipient;
	const selectedRecipientName = $("#selected-recipient-name");
	const selectedRecipientPhoto = $("#selected-recipient-photo");
	const baseImageSrc = selectedRecipientPhoto.attr("basesrc");
	$(".recipient-selector").click(function() {
		const selectedRecipientId = $(this).find("input.user-id").val();
		$.get("/chat/users/" + selectedRecipientId,
			function(recipient) {
				selectedRecipient = recipient;

				selectedRecipientName.html(recipient.fullName);
				selectedRecipientPhoto.attr("src", baseImageSrc + recipient.photoFileName);
				chatWindow.addClass("recipient-selected");
			});
	});

	const messageContent = $(".message-content");
	$(".send-button").click(function(event) {
		event.preventDefault();

		const message = {
			senderId: currentUserId,
			recipientId: selectedRecipient.id,
			content: messageContent.val()
		};
		stompClient.send("/chat/send", {}, JSON.stringify(message));

		messageContent.val("");
	});
});
