package com.rcarorder.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.carmodel.model.CarModelVO;
import com.carrentable.model.CarRentableService;
import com.rcar.model.RcarVO;

import utils.ReDay;

public class RcarOrderService {

	private RcarOrderDAO dao;
	private CarRentableService rentableSVC;

	public RcarOrderService() {
		dao = new RcarOrderDAOImpl();
		rentableSVC = new CarRentableService();
	}

//	public List<ReDay> getallday(List<RcarVO> rcar_no, int month){
//		return dao.getallday(rcar_no, month);
//	}

	public List<RcarOrderVO> getMemberOrders(String meb_no) {
		return dao.getAll(meb_no);
	}
	
	public List<RcarOrderVO> getMemberOrdersDesc(String meb_no) {
		return dao.getAllDesc(meb_no);
	}

	public RcarOrderVO getMemberOrderDetail(int rcaro_no) {
		return dao.getOne(rcaro_no);
	}
	
	public void cancelOneOrder(int rcaro_no) {
		dao.cancel(rcaro_no);
	}
	public List<ReDay> getallday(List<RcarVO> rcar_no, LocalDate date){
		return dao.getallday(rcar_no, date);
	}

	public int insert(RcarOrderVO rcarOrderVO) {
		return dao.insert(rcarOrderVO);
	}

	public void update(CarModelVO carModelVO) {
	}

	public void update(RcarOrderVO orderVO) {
		dao.update(orderVO);
	}

	public List<RcarOrderVO> getAll() {
		return dao.getAll();
	}
	
	public List<RcarOrderVO> getAll(int month) {
		return dao.getAll(month);
	}
	
	
	public List<ReDay> getOthercar(String store, LocalDate Date) { // 查詢 非本站車輛
		List<RcarOrderVO> list = dao.getOthercar(store, Date);//取得時間內所有訂單車輛
		List<ReDay> reDay = new ArrayList<>();
		vo:
		for(RcarOrderVO vo : list) {
			ReDay day = new ReDay();
			LocalDate startTime = vo.getRcaro_ppicktime().toLocalDateTime().toLocalDate();
			LocalDate endTime = vo.getRcaro_pprettime().toLocalDateTime().toLocalDate();
			RcarVO getrcarvo = vo.getrcarvo();
			if(vo.getRcar_no() == null) {
				continue;
			}
			if(getrcarvo.getSt_no().equals(store)) { //過濾本門市車輛
				continue;
			}else if((Date.getDayOfMonth() > endTime.getDayOfMonth()) && !getrcarvo.getRcar_loc().equals(store) && endTime.getMonthValue() == Date.getMonthValue()) {
				//要是 查歸還地 本站 且還車時間 < now 就會判斷 他目前所在地是不是 本站 不是會消失在外站表
				continue;
			}
			for(ReDay re : reDay) {
				if(re.getCar_no().equals(vo.getRcar_no())) {
					// 檢查一台車有多筆訂單 去除重複新增
					re.otherStoreCar(startTime.getDayOfMonth(), endTime.getDayOfMonth(), endTime.getMonthValue(), startTime.getMonthValue(), vo.getRcaro_pickuploc(), vo.getRcaro_returnloc());
					continue vo;
				}
			}
			day.setCar_no(vo.getRcar_no());
			day.setCar_model(vo.getModel_no());
			day.setStore(getrcarvo.getSt_no());
			day.setStatus(getrcarvo.getRcar_status());
			day.otherStoreCar(startTime.getDayOfMonth(), endTime.getDayOfMonth(), endTime.getMonthValue(), startTime.getMonthValue(), vo.getRcaro_pickuploc(), vo.getRcaro_returnloc());
			reDay.add(day);
		}
		return reDay;
	}

	public boolean hasCar(String st_no, String model_no,Timestamp orderStartTime,Timestamp orderReturnTime) {
		return rentableSVC.hasCar(st_no,model_no,orderStartTime,orderReturnTime);
	}
	
	public List<RcarOrderVO> getbetween_order(String rcar_no, LocalDate Date) {
		return dao.getbetween_order(rcar_no, Date);
	}
	public List<RcarOrderVO> getByCompositeQuery(Map<String,String[]> paramMap){
		return dao.getByCompositeQuery(paramMap);
		}

public List<RcarOrderVO> getByOrderStatus(int status){
		return dao.getByOrderStatus(status);
	}
}
