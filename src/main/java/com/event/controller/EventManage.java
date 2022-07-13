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

@WebServlet("/event/eventManage")
public class EventManage extends HttpServlet {
	EventInformationService event = new EventInformationService();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 查詢所有Event
		List<EventInformationVO> list = event.getAll();
		// 封裝到req
		req.setAttribute("events", list);
		req.getRequestDispatcher("/back_end/event/EventManage.jsp").forward(req, res);
		return;
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
