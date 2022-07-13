package com.car_dispatch_record.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import com.rcar.model.RcarDAOImpl;
import com.rcar.model.RcarService;
import com.rcar.model.RcarVO;
import com.rcarorder.model.RcarOrderVO;

import utils.MysqlJDBC;
import utils.ReDay;

public class Car_Dispatch_RecordService {

	private Car_Dispatch_RecordDAO dao;

	

	public Car_Dispatch_RecordService() {
		dao = new Car_Dispatch_RecordDAOImpl();
	}

	public List<Car_Dispatch_RecordVO> getAll() {
		return dao.getAll();
	}

//	新增
	public boolean applyDispatch(Car_Dispatch_RecordVO car_dispatch_recordVO) {
		try {
			dao.applyDispatch(car_dispatch_recordVO);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

// 審核
	public boolean aprroveDispatch(int dr_no, int status, String emp_no) {
		try {
			dao.aprroveDispatch(status, emp_no, dr_no);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

// 取消調度申請
	public boolean cancelDispatch(int dr_no) {
		try {
			dao.cancelDispatch(dr_no);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public void updateDispatch(Car_Dispatch_RecordVO car_dispatch_recordVO) {
		dao.updateDispatch(car_dispatch_recordVO);
	}

	//////

	// 出車
	public boolean carDispatch(Car_Dispatch_RecordVO car_dispatch_recordVO) {
		return dao.carDispatch(car_dispatch_recordVO);
	}

	// 還車
	public boolean dispatchReturn(Car_Dispatch_RecordVO car_dispatch_recordVO) {
		return dao.dispatchReturn(car_dispatch_recordVO);
	}

	// 取得自己門市(被申請方) 調車紀錄 //配車表用
	public List<Car_Dispatch_RecordVO> getStoreMonthRecord(LocalDate date, String st_no) {
		List<Car_Dispatch_RecordVO> alllist = dao.monthDispatchRecord(date);
		Iterator<Car_Dispatch_RecordVO> iterator = alllist.iterator();
		while (iterator.hasNext()) {
			Car_Dispatch_RecordVO vo = iterator.next();
			if (!vo.getDr_approve_st().equals(st_no)) { // 過濾非本門市車輛
				iterator.remove();
			} else if (vo.getDr_approve() == 2 || vo.getDr_approve() == 0) { // 過濾 駁回 或 待同意 的調度
				iterator.remove();
			} else if (vo.getDr_cancel() == 1) { // 過濾 取消申請調度紀錄
				iterator.remove();
			}
//			else { //過濾外站車輛 調度回自己站點紀錄
//				RcarDAOImpl rcarDAOImpl = new RcarDAOImpl();
//				RcarVO car = rcarDAOImpl.getCar(vo.getRcar_no());
//				if(car.getSt_no().equals(st_no)) {
//					iterator.remove();
//				}
//			}
		}
		return alllist;
	}

	// 取得外站車輛 調度到自己站點紀錄 //配車表用 區間紀錄
	public List<Car_Dispatch_RecordVO> getOtherCarRecord(LocalDate date, String st_no) {
		List<Car_Dispatch_RecordVO> list = dao.betweenDispatchRecord(date);
		Iterator<Car_Dispatch_RecordVO> iterator = list.iterator();
		while (iterator.hasNext()) {
			Car_Dispatch_RecordVO vo = iterator.next();
			if (!vo.getDr_apply_st().equals(st_no)) {
				iterator.remove();
			} else if (vo.getDr_approve() == 2) { // 過濾 非同意的調度
				iterator.remove();
			} else if (vo.getDr_cancel() == 1) { // 過濾 取消申請調度紀錄
				iterator.remove();
			} else { // 過濾自己站點 車輛 調度回站 的調度紀錄
				RcarDAOImpl rcarDAOImpl = new RcarDAOImpl();
				RcarVO car = rcarDAOImpl.getCar(vo.getRcar_no());
				if (car.getSt_no().equals(st_no)) {
					iterator.remove();
				}
			}
		}
		return list;
	}

	// 取得自己門市(被申請方) 調度紀錄
	public List<Car_Dispatch_RecordVO> getStoreMonthDR(LocalDate date, String st_no) {
		List<Car_Dispatch_RecordVO> alllist = dao.monthDispatchRecord(date);
		Iterator<Car_Dispatch_RecordVO> iterator = alllist.iterator();
		while (iterator.hasNext()) {
			Car_Dispatch_RecordVO vo = iterator.next();
			if (!vo.getDr_approve_st().equals(st_no) || vo.getDr_approve() == 2 || vo.getDr_cancel() == 1) { // 過濾非本門市車輛

//				if (vo.getDr_approve_st().equals(vo.getDr_apply_st())) {
//					RcarDAOImpl rcarDAOImpl = new RcarDAOImpl();
//					RcarVO rcarVO = rcarDAOImpl.getCar(vo.getRcar_no());
//					if(rcarVO.getRcar_loc().equals(st_no)) {
//						continue;
//					}
//					iterator.remove();
//					continue;
//				}
				iterator.remove();
			}
//			else if(vo.getDr_approve_st().equals(vo.getDr_apply_st())){
//				iterator.remove();
//			}
		}
		return alllist;
	}

	// 取得自己門市(申請方) 調度申請紀錄
	public List<Car_Dispatch_RecordVO> getApplyRecord(LocalDate date, String st_no) {
		List<Car_Dispatch_RecordVO> allList = dao.monthDispatchRecord(date);
		Iterator<Car_Dispatch_RecordVO> iterator = allList.iterator();
		while (iterator.hasNext()) {
			Car_Dispatch_RecordVO vo = iterator.next();
			if (!vo.getDr_apply_st().equals(st_no) || vo.getDr_cancel() == 1) { // 過濾非本門市申請，，取消 || vo.getDr_approve() !=
																				// 0非待審核
				iterator.remove();
			}
		}
		return allList;
	}
	
	public Car_Dispatch_RecordVO getDispatchRecord(int dr_no) {
		return dao.getDispatchRecord(dr_no);
	}


}
