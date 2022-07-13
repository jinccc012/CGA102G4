package com.event.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.rcarorder.model.RcarOrderVO;
import com.websocket.controller.WebSocket;

public class EventInformationService {
	private EventInformationDAO_Implement dao;

	public EventInformationService() {
		dao = new EventInformationDAO_Implement();
	}

	public void insert(EventInformationVO vo) {
		dao.insert(vo);

	}

	// 編輯
	public void update(EventInformationVO vo) {
	
		EventInformationVO event = dao.findByPrimaryKey(vo.getEvent_no());
		
		dao.update(vo);

	}

	public List<EventInformationVO> getAll() {
		return dao.getAll();
	}

	// 得到一個
	public EventInformationVO findByOne(Integer event_no) {
		return dao.findByPrimaryKey(event_no);

	}
public List<EventInformationVO> getEventsByOrderTime(Timestamp start) {
		return dao.getEventsByOrderTime(start);
	}
	
	// 0628 回傳一個訂單最便宜的活動優惠編號，如果沒有符合的優惠 回傳-1
	// 0629 改為回傳一個Map<String,ObjectInteger>
	// 		("eventNo", 那個eventNo的編號(沒有則為-1))
	//		("payAfterDiscount", 套用完優惠的價格(沒有則為原始訂單價格))
	//		("eventTitle", 合適活動的標題(沒有則為"無合適活動"))
	public Map<String, Object> getEventForOrder(RcarOrderVO orderVO){
		List<EventInformationVO> all = this.getEventsByOrderTime(orderVO.getRcaro_ppicktime());
		Map<String, Object> result = new HashMap<String, Object>();
		
		all = all.stream()
				.filter(eventVO -> eventVO.getModel_no().equals(orderVO.getModel_no()) || eventVO.getModel_no().equals("全部"))
				.collect(Collectors.toList());
				
		
		int minPrice = orderVO.getRcaro_pay();
		
		int eventNo = -1;
		
		result.put("eventTitle", "無合適活動");
		for (EventInformationVO eventVO : all) {
//			System.out.println(eventVO);
			
			String discount = eventVO.getEvent_discount();
			
			char operator = discount.charAt(0);
			double i = Double.valueOf(discount.substring(1));
			
			int tmp = orderVO.getRcaro_pay();
			
			switch(operator) {
				case '-':
					tmp -= i;
					break;
				case '*':
					tmp *= i;
					break;
			}
			
			if(tmp < minPrice) {
				minPrice = tmp;
				eventNo = eventVO.getEvent_no();
				result.put("eventTitle", findByOne(eventNo).getEvent_title());
			}
		}
//		System.out.println("EventInformationService@99: eventNo is " + eventNo);
		result.put("eventNo", eventNo);
		result.put("payAfterDiscount", minPrice);
		
		return result;
	}


}
