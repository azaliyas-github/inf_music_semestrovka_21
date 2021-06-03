$(function () {
	const banUserForm = $("#ban-user-form");
	const banUserFormMethod = banUserForm.attr("method");

	banUserForm.submit(function (event) {
		event.preventDefault();

		const userId = $("#ban-user-form [name='userId']").val();
		$.ajax({
			method: banUserFormMethod,
			url: "/api/auth/" + userId + "/ban",
			success: function (response) {
				alert("User with id " + userId + " was banned!");
			}
		});
	});
});
