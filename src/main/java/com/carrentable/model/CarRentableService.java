package com.carrentable.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class CarRentableService {
	CarRentableDAO dao;

//	private HashMap<String, TreeMap<Timestamp, Integer>> tpeOccupied;
//	private HashMap<String, TreeMap<Timestamp, Integer>> tcOccupied;
//	private HashMap<String, TreeMap<Timestamp, Integer>> khOccupied;

	public CarRentableService() {
		dao = new CarRentableDAOImpl();
	}

	public void insert(CarRentableVO rentableVO) {
		dao.insert(rentableVO);
	}

	public void delete(String st_no, String model_no, Timestamp reserve_time) {
		dao.delete(st_no, model_no, reserve_time);
	}

	public boolean hasCar(String st_no, String model_no, Timestamp orderStartTime, Timestamp orderReturnTime) {
		List<CarRentableVO> byOrderInfo = 
				this.getByOrderInfo(st_no, model_no, orderStartTime, orderReturnTime);

//		boolean noCar = byOrderInfo.stream()
//									.anyMatch(rentableVO -> rentableVO.getRest_cars() <= 2);
//		
//		boolean hasCar  = (noCar == true)? false : true;
//		
//		return hasCar;
		
		// 0706 為了進度 先改為一律可租
		return true;
	}
	

	public void update(CarRentableVO rentableVO) {
		dao.update(rentableVO);
	}

	public List<CarRentableVO> getByPK(String st_no, String model_no, Timestamp reserve_time) {
		return dao.getByPK(st_no, model_no, reserve_time);
	}

	public List<CarRentableVO> getAll() {
		return dao.getAll();
	}

	public List<CarRentableVO> getByOrderInfo(String st_no, String model_no, Timestamp startTime, Timestamp endTime) {

		return dao.getByOrderInfo(st_no, model_no, startTime, endTime);
	}

//	private void carRentableMapInit() {
//		
//		
//	}
}
