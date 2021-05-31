$(function () {
	const body = $("body");

	$(".show-modal-window").click(function (event) {
		if ($(this).hasClass("prevent-default-onclick"))
			event.preventDefault();

		$(".modal-window, #modal-window-overlay").hide();

		body.addClass("modal-window-opened");

		const modalWindowId = $(this).attr("href");
		$(modalWindowId + ", #modal-window-overlay").show();
	});

	$(".hide-modal-window, #modal-window-overlay").click(function (event) {
		if ($(this).hasClass("prevent-default-onclick"))
			event.preventDefault();

		$(".modal-window, #modal-window-overlay").hide();

		body.removeClass("modal-window-opened");
	});
});
