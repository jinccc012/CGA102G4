package com.qa.model;

import java.util.List;

public interface QADAO {
	public boolean insert(QAVO qaVO);

	public boolean update(QAVO qaVO);

	public boolean delet(Integer qa_no);

	public QAVO findByPrimaryKey(String qa_no);

	public List<QAVO> getAll();

}
