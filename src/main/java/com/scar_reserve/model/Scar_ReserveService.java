package com.scar_reserve.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class Scar_ReserveService {

	private Scar_ReserveDAO dao;
	
	public Scar_ReserveService() {
		dao = new Scar_ReserveDAOImpl();
	}
	
	public Scar_ReserveVO addScar_Reserve(String meb_no, String scar_no, String st_no, java.sql.Timestamp sr_time) {
		
		Scar_ReserveVO scar_ReserveVO = new Scar_ReserveVO();
		
		scar_ReserveVO.setMeb_no(meb_no);
		scar_ReserveVO.setScar_no(scar_no);
		scar_ReserveVO.setSt_no(st_no);
		scar_ReserveVO.setSr_time(sr_time);
		dao.insert(scar_ReserveVO);
		
		return scar_ReserveVO;
	}
	
	public Scar_ReserveVO updateScar_Reserve(Integer sr_no, String meb_no, String scar_no, String st_no, java.sql.Timestamp sr_time) {
		
		Scar_ReserveVO scar_ReserveVO = new Scar_ReserveVO();
		
		scar_ReserveVO.setSr_no(sr_no);
		scar_ReserveVO.setMeb_no(meb_no);
		scar_ReserveVO.setScar_no(scar_no);
		scar_ReserveVO.setSt_no(st_no);
		scar_ReserveVO.setSr_time(sr_time);		dao.update(scar_ReserveVO);
		
		return scar_ReserveVO;
	}
	
	public void deleteScar_Reserve(Integer sr_no) {
		dao.delete(sr_no);
	}
	
	// 預約編號查詢
	public Scar_ReserveVO getOneScar_Reserve(Integer sr_no) { 
		return dao.findByPrimaryKey(sr_no);
	}
	
	public List<Scar_ReserveVO> getAll(){
		return dao.getAll();
	}
	
	// 日期範圍查詢
	public List<Scar_ReserveVO> getByTime(Timestamp satrt, Timestamp end) {
		return dao.getByTime(satrt, end);
	}
	
	// 複合查詢
	public List<Scar_ReserveVO> getAll(Map<String,String[]> map){
		return dao.getAll(map);
	}
	
	// 會員預約查詢
	public List<Scar_ReserveVO> getMebScarReserve(String meb_no){
		return dao.getMebScarReserve(meb_no);
	}

}
