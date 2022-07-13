package com.employee.model;

import java.util.List;

public class EmployeeService {
	private EmployeeDAO dao;
	
	public EmployeeService() {
		dao = new EmployeeDAOImpl();
	}
	
	public boolean insert(EmployeeVO EmployeeVO) {
		return dao.insert(EmployeeVO);
	}
	
	public boolean update(EmployeeVO EmployeeVO) {
		return dao.update(EmployeeVO);
	}
	
	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}
	
	public EmployeeVO emp(String mail) {
		return dao.emp(mail);
	}
	
public EmployeeVO emplogin(String username, String password) {
		return dao.emplogin(username, password);
	}	
	public EmployeeVO empByPk(String emp_no) {
		return dao.getByFK(emp_no);
	}
	public int empUpdata(EmployeeVO EmployeeVO) {
		return dao.empUpdata(EmployeeVO);
	}
	
	public List<EmployeeVO> getAllByST(String st_no){
		return dao.getAllByST_no(st_no);
	}
	
}
