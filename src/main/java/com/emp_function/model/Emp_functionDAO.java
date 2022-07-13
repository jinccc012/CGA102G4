package com.emp_function.model;

import java.util.List;


public interface Emp_functionDAO {

	
	// 新增
		void insert(Emp_functionVO emp_functionVO);

	// 修改
		void update(Emp_functionVO emp_functionVO);

	// 預覽全部權限功能
		List<Emp_functionVO> getAll();

		

	}
	
	

