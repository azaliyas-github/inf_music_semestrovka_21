const moderatorsGroupId = "moderators";

$(function() {
	const chatWindow = $(".chat-window");
	const sockJsClient = new SockJS("/chat");
	const stompClient = Stomp.over(sockJsClient);

	const chat = chatWindow.find("#chat");
	const messagePrototype = chat.find("li.prototype");
	function isMessageInChatWithSelectedRecipient(counterpartyId) {
		return !isModerator || counterpartyId === selectedRecipient?.id;
	}
	function addMessage(message) {
		const counterpartyId = message.senderId === currentUserId ? message.recipientId : message.senderId;
		if (!isMessageInChatWithSelectedRecipient(counterpartyId))
			return;

		const messageClassName = message.senderId === currentUserId ? "me" : "you";

		const messageElement = messagePrototype.clone();
		messageElement.removeClass("prototype");
		messageElement.addClass(messageClassName);

		messageElement.find(".author-name").text(message.senderName);
		messageElement.find(".message").text(message.content);
		messageElement.find(".message-datetime").text(
			new Date(message.creationTimestamp).toLocaleString());

		messageElement.appendTo(chat);
	}

	function addStompMessage(stompMessage) {
		const message = JSON.parse(stompMessage.body);
		message.senderId = parseInt(message.senderId, 10);
		message.recipientId = parseInt(message.recipientId, 10);
		addMessage(message);
	}
	function showChat() {
		chatWindow.removeClass("not-connected");
	}
	function hideChat() {
		chatWindow.addClass("not-connected");
	}
	stompClient.connect({},
		function() {
			stompClient.subscribe("/chat/users/" + currentUserId + "/messages", addStompMessage);
			showChat();
		},
		hideChat);

	let selectedRecipient;
	const selectedRecipientName = $("#selected-recipient-name");
	const selectedRecipientPhoto = $("#selected-recipient-photo");
	const baseImageSrc = selectedRecipientPhoto.attr("basesrc");
	if (isModerator)
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
			recipientId: isModerator ? selectedRecipient.id : moderatorsGroupId,
			content: messageContent.val()
		};
		stompClient.send("/chat/send", {}, JSON.stringify(message));

		messageContent.val("");
	});
});
