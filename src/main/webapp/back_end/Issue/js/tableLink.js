
$('tbody').on('click','.btn',function () {
	let i = $(this).parents('tr').find('#issue_no').text();
	$.ajax({
		method:'post',
		url:'/CGA102G4/Issue/cancel',
		data:{
			no:i
		},
		success:function(res){
			location.reload()
		}
	})
})