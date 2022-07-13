package com.linenotify.model;

public class LineNotifyService {
	private LineNotifyDAO dao;

	public LineNotifyService() {
		dao = new LineNotifyDAOImpl();
	}
	
	public void insert(LineNotifyVO lineVO) {
		dao.insert(lineVO);
	}
	
	// 查到回傳vo，查不到回傳null
	public void update(LineNotifyVO lineVO) {
		dao.update(lineVO);
	}
	
	public LineNotifyVO getOne(String mebNo) {
		return dao.getOne(mebNo);
	}
}
