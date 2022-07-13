package com.successful_bid.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scar.model.ScarService;
import com.successful_bid.model.Successful_BidService;
import com.successful_bid.model.Successful_BidVO;

@WebServlet("/sbupdatepaying")
public class SuccessfulBidPayingUpdate extends HttpServlet{

	private Successful_BidService sb = new Successful_BidService();

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		String sb_no = req.getParameter("sb_no");
		String meb_no = req.getParameter("meb_no");
		String sb_non_paying = req.getParameter("sb_non_paying");

		if ("0".equals(sb_non_paying)) {
			System.out.println("不可更改付款狀態");
			out.println("不可更改付款狀態");
			out.close();
			return;
		}
		
		//車輛為結標狀態才可 變付款
		ScarService scarservic = new ScarService();
		Successful_BidVO sbNo = sb.getOneSbNo(Integer.valueOf(sb_no));
		if(sbNo.getScarVO().getScar_status()!= 2) {
			out.println("車輛不可再更改付款");
			out.close();
			return;
		}
		
		System.out.println(sb_no + meb_no + sb_non_paying);
		sb.updateBySbpayState(Integer.valueOf(sb_no), meb_no, Integer.valueOf(sb_non_paying));
		out.println(200);
		out.close();

	}

}
