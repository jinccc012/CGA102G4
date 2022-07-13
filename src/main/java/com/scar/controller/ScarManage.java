package com.scar.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scar.model.ScarService;
import com.scar.model.ScarVO;

@WebServlet("/scar/scarManage")
public class ScarManage extends HttpServlet{
	private ScarService service = new ScarService();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<ScarVO> list = service.getAll();
		req.getSession().setAttribute("scarList", list);
		req.getRequestDispatcher("/back_end/scar/ScarManage.jsp").forward(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String scar_brand = req.getParameter("scar_brand");
		String st_no = req.getParameter("st_no");
		String scar_status = req.getParameter("scar_status");
		String scar_startime = req.getParameter("scar_startime");
		String scar_endtime = req.getParameter("scar_endtime");
		
		Map<String, String[]> map = req.getParameterMap();
		List<ScarVO> list = service.getAll(map);
		req.setAttribute("scar_brand",scar_brand);
		req.setAttribute("st_no",st_no);
		req.setAttribute("scar_status",scar_status);
		req.setAttribute("scar_startime",scar_startime);
		req.setAttribute("scar_endtime",scar_endtime);
		

		req.getSession().setAttribute("scarList", list);
		req.getRequestDispatcher("/back_end/scar/ScarManage.jsp").forward(req, res);
		return;

	}


}
