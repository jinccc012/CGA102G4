package com.lineserver.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carmodel.model.CarModelService;
import com.lineserver.model.LineServerService;


@WebServlet("/Member/LineQRServelet")
public class LineQRServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LineQRServelet() {
        super();
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		byte[] imges = LineServerService.getQRCode();
		
		res.setContentType("image/jpeg");
		ServletOutputStream out = res.getOutputStream();
		out.write(imges);
		out.flush();
		out.close();
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
