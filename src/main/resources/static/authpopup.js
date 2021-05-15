const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container-modal');

signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});

// код открытия попапа по клику на профиль
$(".js-show-modal").on("click", function(event) {
    event.preventDefault();

    $(".js-modal, #js-overlay").fadeIn();

    $("body").addClass("open-modal");
});

// код закрытия попапа
$(".js-modal-close").on("click", function(event) {
    event.preventDefault();

    $(".js-modal, #js-overlay").fadeOut();

    $("body").removeClass("open-modal");
});
