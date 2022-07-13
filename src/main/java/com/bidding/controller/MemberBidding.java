package com.bidding.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bidding.model.BiddingService;
import com.bidding.model.BiddingVO;
import com.member.model.MemberVO;

@WebServlet("/bidding/MemberBidding")
public class MemberBidding extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BiddingService svc = new BiddingService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("account");// 後續取得會員帳號做判斷
		String meb_no = memberVO.getMeb_no();
		List<BiddingVO> list = svc.getBidByMEB_NO(meb_no);
		request.getSession().setAttribute("getBidByMEB_NO", list);
		request.getRequestDispatcher("/front_end/bidding/Bidding.jsp").forward(request, response);

	}

}
