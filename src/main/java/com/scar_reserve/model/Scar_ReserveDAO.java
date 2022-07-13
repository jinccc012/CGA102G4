package com.scar_reserve.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.scar.model.ScarVO;


public interface Scar_ReserveDAO {
	public void insert(Scar_ReserveVO Scar_ReserveVO);
	public void update(Scar_ReserveVO Scar_ReserveVO);
	public void delete(Integer sr_no);
	public Scar_ReserveVO findByPrimaryKey(Integer sr_no);
	public List<Scar_ReserveVO> getAll();
	public List<Scar_ReserveVO> getByTime(Timestamp start, Timestamp end);
	
	// 複合查詢
	public List<Scar_ReserveVO> getAll(Map<String,String[]> map);
	
	// 會員登入後可以查詢的預約
	public List<Scar_ReserveVO> getMebScarReserve(String meb_no);
}
