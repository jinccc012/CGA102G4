package com.bidding.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidding.model.BiddingService;
import com.bidding.model.BiddingVO;

@WebServlet("/bid/bidShow")
public class BidShow extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		BiddingService service = new BiddingService();
		List<BiddingVO> bidlist = service.getAllBid();
		req.getSession().setAttribute("bidlist", bidlist);
		String url ="/back_end/bid/showAllBid.jsp";
		req.getRequestDispatcher(url).forward(req, res);

	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Map<String, String[]> map = req.getParameterMap();
		BiddingService service = new BiddingService();
		List<BiddingVO> bidlist = service.getAll(map);
		req.getSession().setAttribute("bidlist", bidlist);
		String url ="/back_end/bid/showAllBid.jsp";
		req.getRequestDispatcher(url).forward(req, res);
	}
}
