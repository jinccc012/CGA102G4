package com.qa.model;

import java.util.List;

public class QAService {
	private QADAO dao;
	
	public QAService() {
		dao =new QADAOImp();		
	}
	
	public boolean delet(Integer qa_no) {
		return dao.delet(qa_no);
	}
	
	public boolean insert(QAVO qavo) {
		return dao.insert(qavo);
	}
	
	public boolean update(QAVO qavo) {
		return dao.update(qavo);
	}
	
	public List<QAVO> getAll(){
		return dao.getAll();
	}
	
	public QAVO findByPrimaryKey(String qa_no) {
		return dao.findByPrimaryKey(qa_no);
	}
	
	

}
