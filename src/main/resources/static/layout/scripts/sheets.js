$(function () {
	const searchForm = $("#search-form");
	const searchFormMethod = searchForm.prop("method");
	const searchFormActionUrl = searchForm.prop("action");
	const sheetsBlock = $("#sheets-output-block");
	searchForm.submit(function (event) {
		event.preventDefault();
		$.ajax({
			method: searchFormMethod,
			url: searchFormActionUrl +"?" + $(this).serialize(),
			datatype: "xml",
			success: function(response) {
				sheetsBlock.html(response.getElementsByTagName("content")[0].innerHTML);
			}
		});
	});
});
