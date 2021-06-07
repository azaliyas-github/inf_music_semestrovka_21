$(function() {
	let selectedRecipient;
	const chatWindow = $(".chat-window");
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
});
