package com.successful_bid.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.successful_bid.model.Successful_BidService;
import com.successful_bid.model.Successful_BidVO;

@WebServlet("/sbid/sbidManage")
public class SuccessfulBidManage extends HttpServlet {
	private Successful_BidService sb = new Successful_BidService();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// List
		List<Successful_BidVO> list = sb.getAll();
		// 封裝到request
		req.setAttribute("sblist", list);
		// forward
		req.getRequestDispatcher("/back_end/sbid/SbManage.jsp").forward(req, res);

	}

}
