$("#send").click(function(e) {
	e.preventDefault();
	
    let from = $('#test').serialize()
	console.log($('#test').serialize());
	$.ajax({
		type: "post",
		url: "../../IssueController",
        data:from

	})
})