// 篩選狀態
$('#order_status777').click(function() {
	$(`.tbody tr`).show();
})
$('#order_status0').click(function() {
	$(`.tbody tr`).show();
	for (let i = 1; i <= $('.tbody tr td:nth-child(5)').length; i++) {
		// alert($('.tbody tr td:nth-child(5)').length);
		// alert($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text());
		if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '出車中') {
			$(`.tbody tr:nth-child(${i})`).hide();
		} else if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '已結案') {
			$(`.tbody tr:nth-child(${i})`).hide();
		} else if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '未結案') {
			$(`.tbody tr:nth-child(${i})`).hide();
		}else if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '訂單已取消') {
			$(`.tbody tr:nth-child(${i})`).hide();
		}
	}
})
$('#order_status1').click(function() {
	$(`.tbody tr`).css('display', '');
	for (let i = 1; i <= $('.tbody tr td:nth-child(5)').length; i++) {
		// alert($('.tbody tr td:nth-child(5)').length);
		// alert($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text());
		if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '訂單成立') {
			$(`.tbody tr:nth-child(${i})`).hide();
		} else if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '已結案') {
			$(`.tbody tr:nth-child(${i})`).hide();
		} else if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '未結案') {
			$(`.tbody tr:nth-child(${i})`).hide();
		}else if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '訂單已取消') {
			$(`.tbody tr:nth-child(${i})`).hide();
		}
	}
})
$('#order_status2').click(function() {
	$(`.tbody tr`).css('display', '');
	for (let i = 1; i <= $('.tbody tr td:nth-child(5)').length; i++) {
		// alert($('.tbody tr td:nth-child(5)').length);
		// alert($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text());
		if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '訂單成立') {
			$(`.tbody tr:nth-child(${i})`).hide();
		} else if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '出車中') {
			$(`.tbody tr:nth-child(${i})`).hide();
		} else if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '未結案') {
			$(`.tbody tr:nth-child(${i})`).hide();
		}else if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '訂單已取消') {
			$(`.tbody tr:nth-child(${i})`).hide();
		}
	}
})
$('#order_status3').click(function() {
	$(`.tbody tr`).css('display', '');
	for (let i = 1; i <= $('.tbody tr td:nth-child(5)').length; i++) {
		// alert($('.tbody tr td:nth-child(5)').length);
		// alert($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text());
		if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '訂單成立') {
			$(`.tbody tr:nth-child(${i})`).hide();
		} else if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '出車中') {
			$(`.tbody tr:nth-child(${i})`).hide();
		} else if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '已結案') {
			$(`.tbody tr:nth-child(${i})`).hide();
		}else if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '訂單已取消') {
			$(`.tbody tr:nth-child(${i})`).hide();
		}
	}
})
$('#order_status4').click(function() {
	$(`.tbody tr`).css('display', '');
	for (let i = 1; i <= $('.tbody tr td:nth-child(5)').length; i++) {
		// alert($('.tbody tr td:nth-child(5)').length);
		// alert($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text());
		if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '訂單成立') {
			$(`.tbody tr:nth-child(${i})`).hide();
		} else if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '出車中') {
			$(`.tbody tr:nth-child(${i})`).hide();
		} else if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '已結案') {
			$(`.tbody tr:nth-child(${i})`).hide();
		}else if ($(`.tbody tr:nth-child(${i}) td:nth-child(5)`).text() === '未結案') {
			$(`.tbody tr:nth-child(${i})`).hide();
		}
	}
})