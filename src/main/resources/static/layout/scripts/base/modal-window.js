$(function () {
	$(".show-modal-window").click(function (event) {
		if ($(this).hasClass("prevent-default-onclick"))
			event.preventDefault();

		$(".modal-window, #modal-window-overlay").hide();

		const modalWindowId = $(this).attr("href");
		$(modalWindowId + ", #modal-window-overlay").show();
	});

	$(".hide-modal-window, #modal-window-overlay").click(function (event) {
		if ($(this).hasClass("prevent-default-onclick"))
			event.preventDefault();

		$(".modal-window, #modal-window-overlay").hide();
	});
});
