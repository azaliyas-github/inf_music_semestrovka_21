$(function () {
    const container = $("#container-modal");
    $("#signUp").on("click", function () { container.addClass("right-panel-active"); });
    $("#signIn").on("click", function () { container.removeClass("right-panel-active"); });
});
