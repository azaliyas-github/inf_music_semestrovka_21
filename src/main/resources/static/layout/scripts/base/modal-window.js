$(function () {
	$(".show-modal-window").click(function (event) {
		if ($(this).hasClass("prevent-default-onclick"))
			event.preventDefault();

		$(".modal-window").hide();

		const modalWindowId = $(this).attr("href");
		$(modalWindowId).show();
	});
	$(".hide-modal-window").click(function (event) {
		if ($(this).hasClass("prevent-default-onclick"))
			event.preventDefault();

		$(".modal-window").hide();
	});
});
