package com.employee.model;
import java.util.List;


public interface EmployeeDAO {
	//新增
	boolean insert(EmployeeVO EmployeeVO);
	//修改
	boolean update(EmployeeVO EmployeeVO);
	//預覽ALL
	List<EmployeeVO> getAll();
	//取得個人員工資料
	EmployeeVO emp(String mail);
	
	//取得個人員工資料
	EmployeeVO getByFK(String emp_no);
	
	//取的分店有哪些
	List<EmployeeVO> getAllByST_no(String st_no);
		//登入驗證
	EmployeeVO emplogin(String username, String password);
	// 員工自行更改資料
	int empUpdata(EmployeeVO EmployeeVO);
}
