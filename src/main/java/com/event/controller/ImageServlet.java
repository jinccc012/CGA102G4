package com.event.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event.model.EventInformationService;
import com.event.model.EventInformationVO;

@WebServlet("/images")
public class ImageServlet extends HttpServlet {
	EventInformationService eventService = new EventInformationService();
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		//event
		String event_no = req.getParameter("event_no");
		EventInformationVO isExist= eventService.findByOne(Integer.valueOf(event_no));
		byte[] imageData = isExist.getEvent_photo();
		//member	
		//scar
		res.setContentType("image/jpeg");
		res.getOutputStream().write(imageData);
		res.getOutputStream().flush();
		res.getOutputStream().close();
	}
	
}
