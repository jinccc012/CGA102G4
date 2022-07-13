package com.scar.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scar.model.ScarService;
import com.scar.model.ScarVO;

@WebServlet("/scar/scarimg")
public class ScarImgServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/jpeg");
		ScarService scarService = new ScarService();
		String scar_no = request.getParameter("scar_no");
		byte[] imageData = null;
		if(scar_no !=null && scar_no.trim().length() !=0) {
			ScarVO scarVO = scarService.getOneScarOnAuctionInRedis(scar_no);
			imageData = scarVO.getScar_photo();
		}
		response.getOutputStream().write(imageData);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	
}
