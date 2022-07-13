package com.event.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event.model.EventInformationService;
import com.event.model.EventInformationVO;

@WebServlet("/event/eventShowList")
public class EventShowList extends HttpServlet {
	EventInformationService event = new EventInformationService();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		List<EventInformationVO> list = event.getAll();
		// 封裝到req
		req.setAttribute("events", list);
		// 重定向
		req.getRequestDispatcher("/front_end/event/EventShowList.jsp").forward(req, res);
	}

}
