package com.bidding.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidding.model.BiddingService;
import com.bidding.model.BiddingVO;
import com.google.gson.Gson;

@WebServlet("/bidListbyscarno")
public class BidListByScarNo extends HttpServlet {
	BiddingService service = new BiddingService();

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String scar_no = req.getParameter("scar_no");
		List<BiddingVO> list = service.getOneBid(scar_no);
		req.setAttribute("bidlist", list);	
		Gson g = new Gson();
		String bidlist = g.toJson(list);
		System.out.println(bidlist);
		 res.setContentType("text/html; charset=utf-8");
		 PrintWriter out = res.getWriter();
		 out.print(bidlist);
		 out.close();

	}
}
