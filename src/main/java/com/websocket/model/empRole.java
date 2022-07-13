package com.websocket.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.Session;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;

public class empRole extends Role{
	
  public empRole(String empid,Session session) {
	  id =empid;
	  super.session =session;
	  role = "emp";
  }
  
  public static void main(String []args) {
	  Map<String, Set<Role>> group = new HashMap<String, Set<Role>>();
	  empRole emp = new empRole("004-TC",null);
	  empRole emp1 = new empRole("004-TPE",null);
	  empRole emp2 = new empRole("005-KH",null);
	  group.computeIfAbsent("emps", k -> new HashSet<Role>()).add(emp);
	  group.computeIfAbsent("emps", k -> new HashSet<Role>()).add(emp1);
	  group.computeIfAbsent("emps", k -> new HashSet<Role>()).add(emp2);
	  
	  EmployeeVO empp = new EmployeeVO();
	  empp.setEmp_no("004-TC");
	  empp.setSt_no("TC");
	  empp.setEmp_name("雷婷");
	  //針對分店發消息
	  EmployeeService empservice =new EmployeeService();
	  List<EmployeeVO> allByST = new  ArrayList<EmployeeVO> ();
	  allByST.add(empp);
		for (Map.Entry<String, Set<Role>> entry : group.entrySet()) {
			
				Set<Role> value = entry.getValue();
				for (Role v : value) {
					EmployeeVO empByPk = empservice.empByPk(v.id);
					if(allByST.contains(empByPk)) {
						
						System.out.println(empByPk.getEmp_name()+"針對發送消息");
					}
						
					//透過主見去找到分店
					
				}
			}
	  
  }

}