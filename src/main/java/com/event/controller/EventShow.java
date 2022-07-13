package com.event.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event.model.EventInformationService;
import com.event.model.EventInformationVO;

@WebServlet("/event/eventShow")
public class EventShow extends HttpServlet {
	EventInformationService eventService = new EventInformationService();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String event_no = req.getParameter("event_no");
		EventInformationVO isExist = eventService.findByOne(Integer.valueOf(event_no));
		if (isExist != null) {
			req.setAttribute("eventObject", isExist);
			req.getRequestDispatcher("/front_end/event/EventShow.jsp").forward(req, res);

		} else {
			req.setAttribute("message", "不存在!!!!");
			// 重定向 首頁
			String url = "/front_end/index/index.jsp";
			req.getRequestDispatcher(url).forward(req, res);
		}

	}
}
