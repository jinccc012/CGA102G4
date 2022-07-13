package com.rcarorder.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.event.model.EventInformationService;
import com.event.model.EventInformationVO;

public class OrderPaymentService {
	// 天數價格
	private static final Map<String, Integer> prices = new HashMap<String, Integer>();
	// 甲地乙還得adjency matrix
	private static final int differentStorePrices[][] = new int[3][3];
	// 站點轉換成陣列索引值
	Map<String, Integer> storeIndex = new HashMap<String, Integer>();

	public OrderPaymentService() {
		utilInit();
	}

	public void utilInit() {
		prices.put("A", 2500);
		prices.put("B", 3000);
		prices.put("C", 3500);
		prices.put("D", 5000);

		// 台北到台北 台北到台中 台北到高雄
		differentStorePrices[0][0] = 0;
		differentStorePrices[0][1] = 500;
		differentStorePrices[0][2] = 1000;

		// 台中到台北 台中到台中 台中到高雄
		differentStorePrices[1][0] = 500;
		differentStorePrices[1][1] = 0;
		differentStorePrices[1][2] = 1000;

		// 高雄到台北 高雄到台中 高雄到高雄
		differentStorePrices[2][0] = 1000;
		differentStorePrices[2][1] = 500;
		differentStorePrices[2][2] = 0;

		// 輸入站點字串 得到注標 用於存取價格陣列
		storeIndex.put("TPE", 0);
		storeIndex.put("TC", 1);
		storeIndex.put("KH", 2);
	}

	public int originOrderPay(RcarOrderVO orderVO) {

		Timestamp sTime = orderVO.getRcaro_ppicktime();
		Timestamp rTime = orderVO.getRcaro_pprettime();
		String pickUpStore = orderVO.getRcaro_pickuploc();
		String returnStore = orderVO.getRcaro_returnloc();

		long days = rTime.getTime() - sTime.getTime();
		days /= (1000 * 60 * 60 * 24);

		return (int) (days * prices.get(orderVO.getLevel_no())) + diffStorePay(pickUpStore, returnStore);
	}

	private int diffStorePay(String pickS, String returnS) {
//		System.out.println("起租站點:" + pickS);
//		System.out.println("起租站點:" + returnS);
//		System.out.println("甲租乙還費用: " + differentStorePrices[storeIndex.get(pickS)][storeIndex.get(returnS)]);

		return differentStorePrices[storeIndex.get(pickS)][storeIndex.get(returnS)];
	}

	// 回傳套用完折扣 新的訂單金額
	// 如果有就回傳套用完活動的金額 沒有就回傳原本訂單的金額
	public int applyEventDiscount(RcarOrderVO orderVO) {

		int payAfterDiscount = orderVO.getRcaro_pay();

		EventInformationService eventSVC = new EventInformationService();
		Map<String, Object> result = eventSVC.getEventForOrder(orderVO);

		if ((Integer) result.get("eventNo") != -1) {
			payAfterDiscount = (Integer) result.get("payAfterDiscount");
		}

		return payAfterDiscount;
	}

	// 訂單套用完點數 直接修改完傳入訂單物件的訂單金額
	public void applyPoint(RcarOrderVO orderVO, int consume) {

		orderVO.setRcaro_pay(orderVO.getRcaro_pay() - consume);

	}
	public int calExtraPay(RcarOrderVO order) {
		// 多一小 租金的1/10
		// 其他站點還車 算要多收多少
		int extraPay = 0;
		
		Timestamp dropOffTime = order.getRcaro_pprettime();
		Timestamp realDropOffTime = order.getRcaro_rrettime();
		String pickUpLoc = order.getRcaro_pickuploc();
		String dropOffLoc = order.getRcaro_returnloc();
		String realDropOffLoc = order.getRcaro_returnloc_actual();
		Integer orderPay = order.getRcaro_pay();
		// 先算未按照訂單站點還車
		if(!dropOffLoc.equals(realDropOffLoc)) {
			int oldPay = diffStorePay(pickUpLoc, dropOffLoc);
			int newPay = diffStorePay(pickUpLoc, realDropOffLoc);
			int i =  newPay - oldPay;
			// 判斷還車真實站點是否需要超收金額
			if(i > oldPay) {
				extraPay += i;
			}
		}
		
		// 再算超時額外收費
		long hours = 0;
		if(realDropOffTime.getTime() > dropOffTime.getTime()) {
			hours = (realDropOffTime.getTime() - dropOffTime.getTime())/(1000 * 60 * 60);
			System.out.println("hours is " + hours);// added
			extraPay += orderPay * (0.1) * hours;
		}
		
		return extraPay;
	}
}
