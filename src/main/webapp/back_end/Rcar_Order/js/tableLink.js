// 取消訂單
$('tbody').on('click','.btn',function () {
	let i = $(this).parents('tr').find('#rcaro_no').text();
	$.ajax({
		method:'post',
		url:'/CGA102G4/RcarOrder/cancel',
		data:{
			no:i
		},
		success:function(res){
			location.reload()
		}
	})
})


// 頁面跳轉
$('tbody').on('click','.transfer',function (){
	$(this).parents('tr').find('#rcaro_no').text();
	let id = $(this).parents('tr').find('#rcaro_no').text();
	window.location.assign("/CGA102G4/back_end/pickAndDropCar/pickAndDropCar.html?id="+id);
})