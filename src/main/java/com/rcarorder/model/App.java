package com.rcarorder.model;

import java.sql.Timestamp;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RcarOrderDAOImpl orderDAOI = new RcarOrderDAOImpl();
		RcarOrderVO VO = new RcarOrderVO();
		
		VO.setRcaro_no(1);
		VO.setMeb_no("B123456789");
		
		VO.setModel_no("ALTIS");
		VO.setRcar_no("RKH-0081");
		
		VO.setRcaro_ppicktime(Timestamp.valueOf("2019-07-19 09:00:00"));
		VO.setRcaro_pprettime(Timestamp.valueOf("2019-07-19 09:00:00"));
		VO.setRcaro_rpicktime(Timestamp.valueOf("2019-07-19 09:00:00"));
		VO.setRcaro_rrettime(Timestamp.valueOf("2019-07-19 09:00:00"));
		
		VO.setRcaro_pickuploc("TPE");
		VO.setRcaro_returnloc("TPE");
		VO.setRcaro_returnloc_actual("TPE");
		
		VO.setRcaro_pay(15);
		VO.setRcaro_extra_pay(20);
		VO.setRcaro_extra_pay_status(25);
		VO.setConsume_point(30);
		
		VO.setEarn_point(100);
		VO.setEvent_no(1);
		VO.setRcaro_status(0);
		
		VO.setRcaro_note("我來測試了");
		VO.setLessee_name("大吳老師");
		
		if(orderDAOI.update(VO)) {
			System.out.println("成功");
		}
		
	}

}
