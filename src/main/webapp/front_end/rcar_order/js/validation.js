/**
 * 
 */
const levelList = {
	'A' :['COLTPLUS', 'TIIDA', 'YARIS'],
	'B' :['ALTIS', 'FOCUS', 'SENTRA'],
	'C' :['RAV4', 'KUGA', 'CX-5'],
	'D' :['Starex', '旅行家']
}

function showHideModel(levelNo){
	$("#car_model_select").children('option').hide();
	
	if(levelNo === 'unpicked') return;
	
	// model 是 車型字串
	for(const model of levelList[levelNo]){
		$("#car_model_select").children("option[value^=" + model + "]").show()
	}
	
	$("#car_model_select").children("option[value^=unpicked]").show()
}

function timeValidate(){
	let st = $(order_car_time).val();
	let et = $(order_return_car_time).val();
	
	$(order_car_time).val(st.substr(0,16));
	$(order_return_car_time).val(et.substr(0,16));
	
}

$(document).ready(()=>{
	$(".TPEHO").hide();// 總公司隱藏
	
	// 時間格式驗證
	timeValidate();
	
	// 讓使用者依定要先選車輛等級才能動
	let level = $(car_level_id).val();
	showHideModel(level);
	
	// 車型選單隨車輛等級改變
	$(car_level_id).on('change',()=>{
		let level = $(car_level_id).val();
		
		showHideModel(level);
		
	})
	
	// 不讓使用者手動填寫時間
	$(order_car_time).on('keydown',()=>{
		swal('不允許的操作','請選擇取車時間', 'error');
	})
	
	// 不讓使用者手動填寫時間
	$(order_return_car_time).on('keydown',()=>{
		swal('不允許的操作','請選擇還車時間', 'error');
	})
	
	// 卡掉分鐘
	$(order_car_time).on('change',()=>{
		let i = $(order_car_time).val();
//		console.log(i.substring(0,14) + '00');
		$(order_car_time).val(i.substring(0,14) + '00');
	})
	// 卡掉分鐘
	$(order_return_car_time).on('change',()=>{
		let i = $(order_return_car_time).val();
		$(order_return_car_time).val(i.substring(0,14) + '00');
	})
	
})