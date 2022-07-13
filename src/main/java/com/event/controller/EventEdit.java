package com.event.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.event.model.EventInformationService;
import com.event.model.EventInformationVO;
import com.google.gson.Gson;

import utils.Webutils;

@WebServlet("/event/eventEdit")
@MultipartConfig
public class EventEdit extends HttpServlet {
	EventInformationService eventService = new EventInformationService();
	EventInformationVO isExist;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// 得到id 後
		String event_no = req.getParameter("event_no");
		
		isExist = eventService.findByOne(Integer.valueOf(event_no));
		// 獲取event object

//		
		java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
		String url = req.getContextPath()+"/event/eventManage"; 
		// 保存到域中
		if (isExist != null) {
			if(isExist.getEvent_start().before(now)) {
				req.getSession().setAttribute("message", "此活動已不可在編輯");
				res.sendRedirect(url);
				return;			
			}			
			req.setAttribute("eventObject", isExist);
		}
		else {
				req.getSession().setAttribute("message", "此活動已不可在編輯");
				res.sendRedirect(url);
				return;
		}
		// 請求轉發編輯頁面
		req.getRequestDispatcher("/back_end/event/EventEdit.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");
		String event_no = req.getParameter("event_no");
		isExist = eventService.findByOne(Integer.valueOf(event_no));
		EventInformationVO vo = new EventInformationVO();
		Map<String, String> Msgs = new LinkedHashMap<String, String>();

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		// event_title can't be null
		String event_title = req.getParameter("event_title");
		if (event_title == null || event_title.trim().length() == 0) {
			Msgs.put("event_titleMsg", "活動標題，請勿空白");
		}

		// event_content can't be null
		String event_content = req.getParameter("event_content");
		if (event_content == null || event_content.trim().length() == 0) {
			Msgs.put("event_contentMsg", "活動內容，請勿空白");
		}

		java.sql.Date event_start = null;
		System.out.println(req.getParameter("event_start"));
		try {
			event_start = java.sql.Date.valueOf(req.getParameter("event_start").trim());
		} catch (IllegalArgumentException e) {
			Msgs.put("event_startMsg", "請輸入活動起算時間");
		}

		java.sql.Date event_end = null;
		try {
			event_end = java.sql.Date.valueOf(req.getParameter("event_end").trim());
		} catch (IllegalArgumentException e) {
			Msgs.put("event_endMsg", "請輸入活動結束時間");
		}

		// 不得晚於今日
		if (event_start != null && event_end != null) {
			java.util.Date date = new java.util.Date();
			Date today = new Date(date.getTime());
			if (event_start.compareTo(event_end) > 0) {
				Msgs.put("event_startMsg", "活動開始時間不得晚於活動結束時間");
			}
			if(event_start.compareTo(event_end)>0) {
				Msgs.put("event_startMsg","活動開始時間不得晚於活動結束時間");
			}
		}

		// model_no can't be null
		String model_no = req.getParameter("model_no");
		System.out.println(model_no);
		if (model_no == null || model_no.trim().length() == 0) {
			Msgs.put("model_noMsg", "請選擇車型");
		}

		// event_discount can't be null
//		String event_discount = req.getParameter("event_discount");
//		if (event_discount == null || event_discount.trim().length() == 0) {
//			Msgs.put("event_discountMsg", "優惠內容，請勿空白");
//		} else {
//			if ('-' != event_discount.trim().charAt(0) && '*' != event_discount.trim().charAt(0))
//				Msgs.put("event_discountMsg", "優惠內容， 需以-或*開頭");
//		}
//		
		
		//event_discount can't be null
				String event_discount = req.getParameter("event_discount");
				if(event_discount == null || event_discount.trim().length() == 0) {
					Msgs.put("event_discountMsg","優惠內容，請勿空白");
				}else {
					if( '-'!=event_discount.trim().charAt(0) && '*'!=event_discount.trim().charAt(0))
						Msgs.put("event_discountMsg","優惠內容，需以-或*開頭");
					Integer discout = null;
					if('-'==event_discount.trim().charAt(0)) {
						try {
							System.out.println(event_discount.substring(1));
							discout = Integer.valueOf(event_discount.substring(1));
						} catch (NumberFormatException e) {
							Msgs.put("event_discountMsg", "優惠內容，請輸入正確數字格式");
						}
						
					}}
		
		
		// event_photo can't be null
		Part photo = req.getPart("event_photo");
		String photoName = photo.getSubmittedFileName();



		PrintWriter out = res.getWriter();
		// req.setAttribute("eventObject", vo);
		Gson gson = new Gson();

		String message = gson.toJson(Msgs);
		if (!Msgs.isEmpty()) {
			Msgs.forEach((u, v) -> System.out.println("key:" + u + ",value:" + v));
			// req.getRequestDispatcher("addEvent.jsp").forward(req, res);
			out.print(message);
			return;
		}
		Webutils.copyParamToBean(req, vo);
		// 處理照片 轉成byte
		if (!("".equals(photoName))) {
			InputStream in = photo.getInputStream();
			byte[] b = new byte[in.available()];
			in.read(b);
			in.close();
			vo.setEvent_photo(b);
		} else {
			// 重新輸入
			vo.setEvent_photo(isExist.getEvent_photo());
		}
		eventService.update(vo);
		req.getSession().setAttribute("message", "編輯成功!!!");
		String url = req.getContextPath()+"/event/eventManage";
		Msgs.put("redirect", url);
		String sucess = gson.toJson(Msgs);
		out.print(sucess);
		out.close();

	}

}
