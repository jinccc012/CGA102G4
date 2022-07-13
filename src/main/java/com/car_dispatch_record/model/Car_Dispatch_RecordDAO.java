package com.car_dispatch_record.model;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

public interface Car_Dispatch_RecordDAO {
	public List<Car_Dispatch_RecordVO> getAll();
	public void applyDispatch(Car_Dispatch_RecordVO car_dispatch_recordVO);
	public void aprroveDispatch(int status,String emp_no,int dr_no);
	//取消調度
	public void cancelDispatch(int dr_no); 
	public void updateDispatch(Car_Dispatch_RecordVO car_dispatch_recordVO);
	////////////////////////
	
	//出車
	public boolean carDispatch(Car_Dispatch_RecordVO car_dispatch_recordVO);
	//還車
	public boolean dispatchReturn(Car_Dispatch_RecordVO car_dispatch_recordVO);
	//取得區間 紀錄
	public List<Car_Dispatch_RecordVO> betweenDispatchRecord (LocalDate date);
	//取得調車月份紀錄
	public List<Car_Dispatch_RecordVO> monthDispatchRecord (LocalDate date);
	//取得一筆調車紀錄
	public Car_Dispatch_RecordVO getDispatchRecord(int dr_no);
	
}
