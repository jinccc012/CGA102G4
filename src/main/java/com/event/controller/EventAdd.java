package com.event.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
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
import com.websocket.controller.WebSocket;

import utils.Webutils;

@WebServlet("/event/eventAdd")
@MultipartConfig
public class EventAdd extends HttpServlet {
	EventInformationService service = new EventInformationService();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String url = "/back_end/event/EventAdd.jsp";
		req.getRequestDispatcher(url).forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		EventInformationVO vo = new EventInformationVO();

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		String event_title = req.getParameter("event_title");
		if (event_title == null || event_title.trim().length() == 0) {
			errorMsgs.put("event_titleMsg", "活動標題，請勿空白");
		}

		// event_content can't be null
		String event_content = req.getParameter("event_content");
		if (event_content == null || event_content.trim().length() == 0) {
			errorMsgs.put("event_contentMsg", "活動內容，請勿空白");
		}

		java.sql.Date event_start = null;
		System.out.println(req.getParameter("event_start"));
		try {
			event_start = java.sql.Date.valueOf(req.getParameter("event_start").trim());
		} catch (IllegalArgumentException e) {
			errorMsgs.put("event_startMsg", "請輸入活動起算時間");
		}

		java.sql.Date event_end = null;
		try {
			event_end = java.sql.Date.valueOf(req.getParameter("event_end").trim());
		} catch (IllegalArgumentException e) {
			errorMsgs.put("event_endMsg", "請輸入活動結束時間");
		}

		// 不得晚於今日
		if (event_start != null && event_end != null) {
			java.util.Date date = new java.util.Date();
			Date today = new Date(date.getTime());
			if (event_start.compareTo(today) < 0)
				errorMsgs.put("event_startMsg", "活動開始時間不得晚於今日");
			if (event_start.compareTo(event_end) > 0) {
				errorMsgs.put("event_startMsg", "活動開始時間不得晚於活動結束時間");
			}
		}

		// model_no can't be null
		String model_no = req.getParameter("model_no");
		System.out.println(model_no);
		if (model_no == null || model_no.trim().length() == 0) {
			errorMsgs.put("model_noMsg", "請選擇車型");
		}

		// event_discount can't be null
//		String event_discount = req.getParameter("event_discount");
//		if(event_discount == null || event_discount.trim().length() == 0) {
//			errorMsgs.put("event_discountMsg","優惠內容，請勿空白");
//		}else {
//			if( '-'!=event_discount.trim().charAt(0) && '*'!=event_discount.trim().charAt(0))
//				errorMsgs.put("event_discountMsg","優惠內容，需以-或*開頭");
//		}

		// event_discount can't be null
		String event_discount = req.getParameter("event_discount");
		if (event_discount == null || event_discount.trim().length() == 0) {
			errorMsgs.put("event_discountMsg", "優惠內容，請勿空白");
		} else {
			if ('-' != event_discount.trim().charAt(0) && '*' != event_discount.trim().charAt(0))
				errorMsgs.put("event_discountMsg", "優惠內容，需以-或*開頭");
			Integer discout = null;
			if ('-' == event_discount.trim().charAt(0)) {
				try {
					System.out.println(event_discount.substring(1));
					discout = Integer.valueOf(event_discount.substring(1));
				} catch (NumberFormatException e) {
					errorMsgs.put("event_discountMsg", "優惠內容，請輸入正確數字格式");
				}

			}
		}

		// event_photo can't be null
		Part photo = req.getPart("event_photo");
		String photoName = photo.getSubmittedFileName();
		if (photoName == null || photoName.trim().length() == 0) {
			errorMsgs.put("event_photoMsg", "活動照片， 請上傳");
		}

		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String message = gson.toJson(errorMsgs);
		if (!errorMsgs.isEmpty()) {
			errorMsgs.forEach((u, v) -> System.out.println("key:" + u + ",value:" + v));
			out.print(message);
			return;
		}
		Webutils.copyParamToBean(req, vo);

		// 照片另外處理
		if (!("".equals(photoName))) {
			InputStream in = photo.getInputStream();
			byte[] b = new byte[in.available()];
			in.read(b);
			in.close();
			vo.setEvent_photo(b);
		}
		service.insert(vo);
		req.getSession().setAttribute("message", "新增成功!!!");
		String url = req.getContextPath() + "/event/eventManage";
		WebSocket push = new WebSocket();
		ArrayList<String> groups = new ArrayList<String>();
		groups.add("members");
		groups.add("visitors");
		String pushmessage1 = "<a style=\"text-decoration:none;\" href=\"" + req.getContextPath()
				+ "/event/eventShowList\">" + vo.getEvent_title() + "活動正式上線囉!!</a>";
		System.out.println(pushmessage1);
		// String message = vo.getEvent_title()+"活動正式上線囉!!";
		push.sentMessageToGroups(groups, pushmessage1);
		errorMsgs.put("redirect", url);
		String sucess = gson.toJson(errorMsgs);
		out.print(sucess);
		out.close();

	}

}
