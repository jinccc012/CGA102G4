package com.scar_reserve.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scar_reserve.model.Scar_ReserveService;
// 會員中古車預約
@MultipartConfig()
@WebServlet("/scar/scarReserve")
public class ScarReserveServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String errorMsg = null;
		/* 接收請求參數 */
		String meb_no = request.getParameter("meb_no");
		String scar_no = request.getParameter("scar_no");
		String st_no = request.getParameter("st_no");
		HttpSession session = request.getSession();
		
		
		if ("".equals(request.getParameter("sr_time"))) {
			errorMsg = "請選擇賞車日期";
			session.setAttribute("errorMsg", errorMsg);
			out.write("err");
//			response.sendRedirect(request.getContextPath()+"/scar/scarAuctionAll");
			return;
		}
		Timestamp sr_time = Timestamp.valueOf(request.getParameter("sr_time")+":00.0");

		/* 呼叫service，將請求參數送過去，存到資料庫 */
		Scar_ReserveService sr = new Scar_ReserveService();
		sr.addScar_Reserve(meb_no, scar_no, st_no, sr_time);
		/* 重新回到拍賣頁面 */
//		response.sendRedirect(request.getContextPath()+"/scar/scarAuctionAll");
		out.write("success");
	}
	
	

}
