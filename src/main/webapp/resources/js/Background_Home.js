$('#showFu').on('click','.lss',function (e) {
    const target = e.target;
    //if (target.classList.contains('lss')) {
		if($(this).find('div').css('display') == 'none'){
			$(this).find('div').slideDown(500)
			return;
		}else {
			$(this).find('div').slideUp(500)
			return;
		//}
      //  $(this).find('div').slideToggle(500);
    }
})