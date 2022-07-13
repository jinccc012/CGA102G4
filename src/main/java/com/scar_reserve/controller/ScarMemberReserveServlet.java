package com.scar_reserve.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberVO;
import com.scar_reserve.model.Scar_ReserveService;
import com.scar_reserve.model.Scar_ReserveVO;
// 會員預約
@WebServlet("/scar/scarMebReserve")
public class ScarMemberReserveServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("scarMebReserve");
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("account");
		String meb_no = memberVO.getMeb_no();
		Scar_ReserveService sr = new Scar_ReserveService();
		List<Scar_ReserveVO> list = sr.getMebScarReserve(meb_no);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/front_end/scar/scarReserve.jsp").forward(request, response);
	}
	
	

}
