package com.store.model;
import java.util.List;

public interface StoreDAO {
	
	//新增
	void insert( StoreVO StoreVO);
	//修改
	void update(StoreVO StoreVO);
	//預覽ALL
	List<StoreVO> getAll();
	
}
