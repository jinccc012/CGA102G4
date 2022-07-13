package com.car_dispatch_record.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.car_dispatch_record.model.Car_Dispatch_RecordService;
import com.car_dispatch_record.model.Car_Dispatch_RecordVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rcar.model.RcarService;
import com.rcar.model.RcarVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;

import controller.WebSocket;

@WebServlet("/getdispatchservlet")
public class GetDispatchServlet extends HttpServlet {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private static Map<String, String> stVo = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String monthStr = req.getParameter("month");
		String storeStr = req.getParameter("store");
		String statusStr = req.getParameter("status");
		Car_Dispatch_RecordService service = new Car_Dispatch_RecordService();
//		System.out.println(monthStr);
		WebSocket webSocket = new WebSocket();
		
		if ("selfstore".equals(statusStr)) { // 顯示自己門市車輛 調車紀錄 配車表
			LocalDate month = LocalDate.parse(monthStr);
			List<Car_Dispatch_RecordVO> list = service.getStoreMonthRecord(month, storeStr);

			Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm").create();
			String json = gson.toJson(list);
			resp.getWriter().write(json);
			return;
		}

		if ("othercar".equals(statusStr)) { // 顯示外站車輛 調車紀錄 配車表
			LocalDate month = LocalDate.parse(monthStr);
			List<Car_Dispatch_RecordVO> list = service.getOtherCarRecord(month, storeStr);
			// HttpSession session = req.getSession();
			// session.setAttribute("otherCarDispatch", list);//將區間內調度紀錄 存入session
			// 方便其他servlet存取

			Gson gson = new Gson();
			String json = gson.toJson(list);
			resp.getWriter().write(json);
			return;
		}

		if ("record".equals(statusStr)) { // 顯示自己門市車輛 調車紀錄
			LocalDate month = LocalDate.parse(monthStr);
			List<Car_Dispatch_RecordVO> list = service.getStoreMonthDR(month, storeStr);
			System.out.println("record=" + "月:" + monthStr + list);

			Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm").create();
			String json = gson.toJson(list);
			resp.getWriter().write(json);
			return;
		}

		if ("update".equals(statusStr)) { // 更新 核准狀態
			String idStr = req.getParameter("id");
			String statusValStr = req.getParameter("statusVal");
			String empStr = req.getParameter("emp");
			try {
				if (service.aprroveDispatch(Integer.parseInt(idStr), Integer.parseInt(statusValStr), empStr)) {
					resp.getWriter().write("true");
					Car_Dispatch_RecordVO vo = service.getDispatchRecord(Integer.parseInt(idStr));
					if(Integer.parseInt(statusValStr) == 1) { //同意審核
						webSocket.sendMessage(vo.getDr_apply_st(), stVo.get(vo.getDr_approve_st()) +" 核准申請! 調度單號:"+vo.getDr_no());
					}else if(Integer.parseInt(statusValStr) == 2) { //駁回
						webSocket.sendMessage(vo.getDr_apply_st(), stVo.get(vo.getDr_approve_st()) +" 駁回申請! 調度單號:"+vo.getDr_no());
					}
				} else {
					resp.getWriter().write("false");
				}
			} catch (Exception e) {
				// TODO: handle exception
				resp.getWriter().write("false");
			}
			return;
		}

		if ("insert".equals(statusStr)) { // 申請調度
			String applyEmpStr = req.getParameter("applyEmp");
			String applyStStr = req.getParameter("applySt");
			String approveStStr = req.getParameter("approveSt");
			String drStartTimeStr = req.getParameter("drStartTime");

			try {
				LocalDateTime drStartTime = LocalDateTime.of(LocalDate.parse(drStartTimeStr), LocalTime.now());
				
				if(LocalDateTime.now().compareTo(drStartTime) == 1) { //後端擋下 小於(現在時間) 的申請時間
					resp.getWriter().write("false");
					return;
				}
				//LocalDateTime drStartTime = LocalDateTime.parse(drStartTimeStr);
				String drRcarStr = req.getParameter("drRcar");
				Car_Dispatch_RecordVO vo = new Car_Dispatch_RecordVO();
				vo.setDr_apply_emp(applyEmpStr);
				vo.setDr_apply_st(applyStStr);
				vo.setDr_approve_st(approveStStr);
				vo.setDr_start_time(Timestamp.valueOf(drStartTime));
				vo.setDr_end_time(Timestamp.valueOf(drStartTime.plusHours(2)));
				vo.setRcar_no(drRcarStr);
				boolean dispatch = service.applyDispatch(vo);
				if (dispatch) {
					webSocket.sendMessage(approveStStr, stVo.get(applyStStr)+" 申請一筆調度");
					resp.getWriter().write("true");
				} else {
					resp.getWriter().write("false");
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resp.getWriter().write("false");
			}
			return;
		}

		if ("applyrecord".equals(statusStr)) { // 查看自己門市申請調度 紀錄
			LocalDate month = LocalDate.parse(monthStr);
			List<Car_Dispatch_RecordVO> list = service.getApplyRecord(month, storeStr);
			System.out.println("applyrecord=" + "月:" + monthStr + list);
			Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm").create();
			String json = gson.toJson(list);
			resp.getWriter().write(json);
			return;
		}

		if ("delete".equals(statusStr)) { // 取消調度
			String idStr = req.getParameter("id");
			try {
				boolean cancelDispatch = service.cancelDispatch(Integer.parseInt(idStr));
				if (cancelDispatch) {
					resp.getWriter().write("true");
				} else {
					resp.getWriter().write("false");
				}
			} catch (Exception e) {
				resp.getWriter().write("false");
				return;
			}
			return;
		}

		if ("outcar".equals(statusStr)) { // 出車
			String idStr = req.getParameter("id");
			String rcarNoStr = req.getParameter("rcarNo");
			String rcarLocStr = req.getParameter("rcar_loc");
			Car_Dispatch_RecordVO recordVO = new Car_Dispatch_RecordVO();
			try {
				recordVO.setRcar_no(rcarNoStr);
				recordVO.setDr_no(Integer.parseInt(idStr));
				recordVO.setDr_apply_st(rcarLocStr);
				boolean carDispatch = service.carDispatch(recordVO);
				if (carDispatch) {
					Car_Dispatch_RecordVO vo = service.getDispatchRecord(Integer.parseInt(idStr));
					webSocket.sendMessage(vo.getDr_apply_st(), stVo.get(vo.getDr_approve_st())+" 出車! 調度單號:"+vo.getDr_no());
					resp.getWriter().write("true");
				} else {
					resp.getWriter().write("false");
				}
			} catch (Exception e) {
				resp.getWriter().write("false");
				return;
			}
			return;
		}

		if ("incar".equals(statusStr)) { // 還車
			String idStr = req.getParameter("id");
			String rcarNoStr = req.getParameter("rcarNo");
			String drMilesStr = req.getParameter("drMiles");
			Car_Dispatch_RecordVO recordVO = new Car_Dispatch_RecordVO();
			try {
				recordVO.setRcar_no(rcarNoStr);
				recordVO.setDr_no(Integer.parseInt(idStr));
				recordVO.setMiles_after(Integer.parseInt(drMilesStr));
				boolean dispatchReturn = service.dispatchReturn(recordVO);
				if (dispatchReturn) {
					Car_Dispatch_RecordVO vo = service.getDispatchRecord(Integer.parseInt(idStr));
					webSocket.sendMessage(vo.getDr_approve_st(), stVo.get(vo.getDr_apply_st())+" 還車! 調度單號:"+vo.getDr_no());
					resp.getWriter().write("true");
				} else {
					resp.getWriter().write("false");
				}
				return;
			} catch (Exception e) {
				resp.getWriter().write("false");
				return;
			}

			
		}

		if ("upstatus".equals(statusStr)) { // 修改 里程 狀態
			String jsonStr = req.getParameter("json");
			System.out.println(jsonStr);
			RcarVO rcarVO = new RcarVO();
			Gson gson = new Gson();
			try {
				rcarVO = gson.fromJson(jsonStr, rcarVO.getClass());
				RcarService rcarService = new RcarService();
				boolean update = rcarService.update(rcarVO);
				if(update) {
					resp.getWriter().write("true");
				}else {
					resp.getWriter().write("false");
				}
				return ;
			} catch (Exception e) {
//				e.printStackTrace();
				resp.getWriter().write("false");
				return ;
			}
			
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	public void init() throws ServletException {
//		Map<String, StoreVO> collect = stVo.stream().collect(Collectors.toMap(StoreVO::getSt_no,Function.identity())); 
		
		//取得門市中文名稱 將list 轉 Map
		stVo = new StoreService().getAll().stream().collect(Collectors.toMap(StoreVO::getSt_no,StoreVO::getSt_name));
	}
}
