$(function() {
	const profilePhotoDiv = $(".profile-pic-div");
	const uploadButton = $(".upload-button");
	profilePhotoDiv.on("mouseenter", function() {
		uploadButton.addClass("upload-button-hovered");
	});
	profilePhotoDiv.on("mouseleave", function() {
		uploadButton.removeClass("upload-button-hovered");
	});

	const photo = $("#profile-photo");
	const file = $("#photo-input");
	const fileReader = new FileReader();
	fileReader.addEventListener("load", function() {
		photo.attr("src", fileReader.result);
	});
	file.on("change", function() {
		const chosenFile = this.files[0];
		if (!chosenFile)
			return;

		fileReader.readAsDataURL(chosenFile);
	});
});
