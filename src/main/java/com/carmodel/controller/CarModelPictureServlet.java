package com.carmodel.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carmodel.model.CarModelService;


@WebServlet("/CarModel/CarModelPictureServlet")
public class CarModelPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CarModelPictureServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String id = req.getParameter("car_model");
		
		byte[] imges = new CarModelService().getImges(id);
		
		
		res.setContentType("image/jpeg");
		ServletOutputStream out = res.getOutputStream();
		out.write(imges);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
