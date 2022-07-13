package com.rcar.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public interface RcarDAO {

	// 新增
	boolean insert(RcarVO RcarVO);

	// 修改
	boolean update(RcarVO RcarVO);
	
	//調度出車 
	void outCar(Connection ct,RcarVO RcarVO) throws SQLException;
	
	//調度還車
	void inCar(Connection ct,RcarVO RcarVO) throws SQLException;

	// 預覽門市全車輛
	List<RcarVO> getSt_noAll(String st_no);
	
	// 預覽全車輛
	List<RcarVO> getAll();
	
	// 預覽外站車輛
	List<RcarVO> getOtherStoreCar(String st_no);
	
	// 取得特定車輛資訊
	RcarVO getCar(String rc_no);
}
