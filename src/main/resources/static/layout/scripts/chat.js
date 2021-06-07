$(function() {
	const chatWindow = $(".chat-window");
	const sockJsClient = new SockJS("/chat");
	const stompClient = Stomp.over(sockJsClient);

	function onStompClientConnected() {
		stompClient.subscribe("/chat/users/" + currentUserId + "/messages");
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
