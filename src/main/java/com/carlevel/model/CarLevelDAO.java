package com.carlevel.model;

import java.util.List;


public interface CarLevelDAO {
		//新增
		boolean insert(CarLevelVO CarLevelVO);
		//修改
		boolean update(CarLevelVO CarLevelVO);
		//預覽ALL
		List<CarLevelVO> getAll();
}
