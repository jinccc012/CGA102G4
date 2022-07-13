package com.store.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/storeaccess")
public class StoreAccess extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String status = req.getParameter("status");
		
		StoreService service = new StoreService();
		if("updata".equals(status)) {
			String json = req.getParameter("json");
			StoreVO vo = new StoreVO();
			Gson gson = new Gson();
			vo = gson.fromJson(json, vo.getClass());
			
			boolean update = service.update(vo);
			System.out.println(update);
			if(update) {
				resp.getWriter().write("true");
			}else {
				resp.getWriter().write("false");
			}
		}
		
		if("insert".equals(status)) {
			String json = req.getParameter("json");
			StoreVO vo = new StoreVO();
			Gson gson = new Gson();
			vo = gson.fromJson(json, vo.getClass());
			
			boolean update = service.insert(vo);
			System.out.println(update);
			if(update) {
				resp.getWriter().write("true");
			}else {
				resp.getWriter().write("false");
			}
		}
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
