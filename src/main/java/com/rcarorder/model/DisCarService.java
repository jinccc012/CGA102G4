package com.rcarorder.model;

import java.util.*;

import com.rcar.model.*;
import com.rcarorder.model.*;

public class DisCarService {
	public List<String> rentableCar(Integer orderno) {
		RcarOrderService orderSvc =new RcarOrderService();
		RcarService rcarSvc =new RcarService(); 
		
		List<RcarOrderVO> allOrder= orderSvc.getAll();//取得全部訂單
		Collections.reverse(allOrder);
	    RcarOrderVO thisOrder = orderSvc.getMemberOrderDetail(orderno);//取得欲比較之訂單
	    List<RcarVO> allCar = rcarSvc.getAll();
	    List<String> rantableCarList = new ArrayList<String>();
	    List<String> cantRantCarList = new ArrayList<String>();
	    
	    System.out.println(thisOrder.getRcaro_no());
		for(int i=0;i<allOrder.size();i++) {
			if(dateCheck(thisOrder, allOrder.get(i))){ //檢查訂單是否在預定日期內
				cantRantCarList.add(allOrder.get(i).getRcar_no());
//				System.out.println(allCar.get(i).getRcar_no()+"====");
			}   
		}
		
//		for(int i=0;i<allOrder.size();i++) {
//			for(int j=0;j<cantRantCarList.size();j++) {
//				if(allCar.get(i).getRcar_no().equals(cantRantCarList.get(j)) == false ) {//比較該車是否不能使用
//					rantableCarList.add(allCar.get(i).getRcar_no());
//					break;
//				}
//			}
//		}
		
		for(int i=0;i<allCar.size();i++) {
			int isEqual=0;
			for(int j=0;j<cantRantCarList.size();j++) {
				if(allCar.get(i).getRcar_no().equals(cantRantCarList.get(j))) {
					isEqual =1;
				}
			}
			if(isEqual==0) {
				rantableCarList.add(allCar.get(i).getRcar_no());	
			}
		}
		
		for(int i=0;i<cantRantCarList.size();i++) {
			System.out.println(cantRantCarList.get(i)+"=+=");
		}
		System.out.println("===================================");
		return rantableCarList;
	}
	
	public boolean dateCheck(RcarOrderVO thisOrder, RcarOrderVO otherOrder) {
		System.out.println("S:"+thisOrder.getRcaro_ppicktime());
		System.out.println("E:"+thisOrder.getRcaro_pprettime());
		System.out.println("OS:"+otherOrder.getRcaro_ppicktime());
		System.out.println(thisOrder.getRcaro_pprettime().compareTo(otherOrder.getRcaro_ppicktime()) <= 0);
		if(thisOrder.getRcaro_ppicktime().compareTo(otherOrder.getRcaro_ppicktime()) <= 0 && thisOrder.getRcaro_pprettime().compareTo(otherOrder.getRcaro_ppicktime()) >= 0 ) {//如果訂單開始時間在日期內
			System.out.println("true");
			return true;
		}
		if(thisOrder.getRcaro_ppicktime().compareTo(otherOrder.getRcaro_pprettime()) <=0 && thisOrder.getRcaro_pprettime().compareTo(otherOrder.getRcaro_pprettime()) >=0) {//如果定結束時間在日期內
			System.out.println("true");
			return true;
		}else {
			System.out.println("false");
			return false;
		}
	} 

}
