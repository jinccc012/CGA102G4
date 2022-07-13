package com.linenotify.model;

public interface LineNotifyDAO {
	
	void insert(LineNotifyVO lineVO);
	
	void update(LineNotifyVO lineVO);
	
	LineNotifyVO getOne(String mebNo);
}
