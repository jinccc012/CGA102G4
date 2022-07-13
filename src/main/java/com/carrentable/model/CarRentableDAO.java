package com.carrentable.model;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public interface CarRentableDAO {
	
	void insert(CarRentableVO rentableVO);
	void delete(String st_no, String model_no,Timestamp reserve_time);
	
	boolean hasCar(String st_no, String model_no,Timestamp orderStartTime,Timestamp orderReturnTime);
	
	void update(CarRentableVO rentableVO);
	List<CarRentableVO> getByPK(String st_no, String model_no,Timestamp reserve_time);
	List<CarRentableVO> getAll();
	List<CarRentableVO> getByOrderInfo(String st_no, String model_no,Timestamp startTime, Timestamp endTime);
	
	HashMap<String, TreeMap<Timestamp, CarRentableVO>> getAllByStore(String st_no);
}
