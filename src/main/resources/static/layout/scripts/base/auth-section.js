$(function () {
	const container = $(".auth-section");
	$("#show-signUp").click(function () { container.addClass("right-panel-active"); });
	$("#show-signIn").click(function () { container.removeClass("right-panel-active"); });
});
