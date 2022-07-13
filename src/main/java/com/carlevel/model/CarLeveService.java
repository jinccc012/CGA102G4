package com.carlevel.model;

import java.util.List;

public class CarLeveService {
	CarLevelDAO dao;
	
	public CarLeveService() {
		dao = new CarLevelDAOImpl();
	}
	
	public boolean insert(CarLevelVO CarLevelVO) {
		return dao.insert(CarLevelVO);
	}
	
	public boolean update(CarLevelVO CarLevelVO) {
		return dao.update(CarLevelVO);
	}
	
	public List<CarLevelVO> getAll() {
		return dao.getAll();
	}
	
}
