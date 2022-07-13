package com.rcar.model;

import java.util.ArrayList;
import java.util.List;

import com.rcarorder.model.RcarOrderVO;

import utils.ReDay;

public class RcarService {
	private RcarDAO dao;
	
	public RcarService() {
		dao = new RcarDAOImpl();
	}
	
	public boolean insert(RcarVO RcarVO) {
		return dao.insert(RcarVO);
	}
	
	public boolean update(RcarVO RcarVO) {
		return dao.update(RcarVO);
	}
	
	public List<RcarVO> getSt_noAll(String st_no) {
		return dao.getSt_noAll(st_no);			
	}
	
	public List<RcarVO> getAll() {
		return dao.getAll();
	}
	
	public List<ReDay> getOtherStoreCar(String st_no) { //查看外站車輛現在時間~7天 有無訂單
		List<RcarVO> otherCar = dao.getOtherStoreCar(st_no);
		List<ReDay> reDay = new ArrayList<>();
		for(RcarVO vo :otherCar) {
			List<RcarOrderVO> order = vo.getcar_order();
			if(order.size() == 0) { //無訂單 且所在地在本站 或 以調度到自己門市
				System.out.println(vo.getRcar_no()+"期間無訂單");
				ReDay re = new ReDay();
				re.setCar_no(vo.getRcar_no());
				re.setCar_model(vo.getModel_no());
				re.setStore(vo.getSt_no());
				re.setStatus(vo.getRcar_status());
				reDay.add(re);
			}
		}
		
		return reDay;
	}
	
	public RcarVO getCar(String rc_no) {
		return dao.getCar(rc_no);
	}
	
	
	//取得有 調度紀錄 "無"訂單 車輛資訊  
//	public List<ReDay> getNullOrderCar(List<Car_Dispatch_RecordVO> list) {
//		List<ReDay> reDay = new ArrayList<>();
//		list.forEach((vo)->{
//			RcarVO rcarVO = dao.getCar(vo.getRcar_no());
//			List<RcarOrderVO> getcar_order = rcarVO.getcar_order();
//			if(getcar_order.size() == 0 ) {
//				ReDay re = new ReDay();
//				re.setCar_no(rcarVO.getRcar_no());
//				re.setCar_model(rcarVO.getModel_no());
//				re.setStore(rcarVO.getSt_no());
//				re.setStatus(rcarVO.getRcar_status());
//				reDay.add(re);
//			}
//		});
//		return reDay;
//	}
}
