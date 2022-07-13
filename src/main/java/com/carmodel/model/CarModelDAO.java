package com.carmodel.model;


import java.util.List;

public interface CarModelDAO {
	
	//新增
	boolean insert(CarModelVO carModelVO);
	//修改
	boolean update(CarModelVO carModelVO);
	//預覽ALL
	List<CarModelVO> getAll();
	//預覽Img
	byte[] getImage(String model_no);
	
	//increase 
	List<String> getAllModelNo();
	
	List<String> getAllModelName();
	
	List<String> getModelNOByNAme(String scar_model);
	
}
