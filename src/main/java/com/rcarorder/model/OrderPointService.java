package com.rcarorder.model;

import com.member.model.MemberService;
import com.member.model.MemberVO;

// 處理所有關於點數的服務
public class OrderPointService {
	// 注入order
	RcarOrderVO orderVO;
	MemberVO memberVO;

	public OrderPointService(RcarOrderVO orderVO) {
		this.orderVO = orderVO;
		memberVO = new MemberService().member(orderVO.getMeb_no());
	}

	public int getMemberPoint() {
		return memberVO.getMeb_bonus();
	}

	// getParameter -> 餵進來
	public void consumeMemberPoint(int consume) {
		// DEBUG
//		System.out.println("會員原本點數:" + memberVO.getMeb_bonus());
//		System.out.println("會員消耗點數:" + consume);
		// DEBUG
		int newPoint = memberVO.getMeb_bonus() - consume;

		memberVO.setMeb_bonus(newPoint);
		new MemberService().update(memberVO);
	}
	
	public void earnPoint(RcarOrderVO order) {
		int finalPay = order.getRcaro_pay();
		int point = (finalPay) / 10;
		
		// 設定訂單獲得點數
		order.setEarn_point(point);
		// 設定會員點數
		
		int bonus = memberVO.getMeb_bonus() + point;
		memberVO.setMeb_bonus(bonus);
		new MemberService().update(memberVO);
	}
	
	

}
