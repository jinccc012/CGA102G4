package com.car_dispatch_record.model;

import java.sql.Timestamp;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;

public class Car_Dispatch_RecordVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Integer dr_no;
	private String dr_apply_st;
	private String dr_approve_st;
	private String dr_apply_emp;
	private String dr_approve_emp;
	private String rcar_no;
	private Timestamp dr_start_time;
	private Timestamp dr_end_time;
	private Timestamp dr_start_time_actual;
	private Timestamp dr_end_time_actual;
	private Byte dr_approve;
	private Byte dr_cancel;
	private Integer miles_before;
	private Integer miles_after;

	public Car_Dispatch_RecordVO() {

	}
	
	



	public Car_Dispatch_RecordVO(Integer dr_no, String dr_apply_st, String dr_approve_st, String dr_apply_emp,
			String dr_approve_emp, String rcar_no, Timestamp dr_start_time, Timestamp dr_end_time,
			Timestamp dr_start_time_actual, Timestamp dr_end_time_actual, Byte dr_approve, Byte dr_cancel,
			Integer miles_before, Integer miles_after) {
		super();
		this.dr_no = dr_no;
		this.dr_apply_st = dr_apply_st;
		this.dr_approve_st = dr_approve_st;
		this.dr_apply_emp = dr_apply_emp;
		this.dr_approve_emp = dr_approve_emp;
		this.rcar_no = rcar_no;
		this.dr_start_time = dr_start_time;
		this.dr_end_time = dr_end_time;
		this.dr_start_time_actual = dr_start_time_actual;
		this.dr_end_time_actual = dr_end_time_actual;
		this.dr_approve = dr_approve;
		this.dr_cancel = dr_cancel;
		this.miles_before = miles_before;
		this.miles_after = miles_after;
	}





	public String getDr_apply_st() {
		return dr_apply_st;
	}





	public void setDr_apply_st(String dr_apply_st) {
		this.dr_apply_st = dr_apply_st;
	}





	public String getDr_approve_st() {
		return dr_approve_st;
	}





	public void setDr_approve_st(String dr_approve_st) {
		this.dr_approve_st = dr_approve_st;
	}





	public Integer getDr_no() {
		return dr_no;
	}

	public void setDr_no(Integer dr_no) {
		this.dr_no = dr_no;
	}

	public String getDr_apply_emp() {
		return dr_apply_emp;
	}

	public void setDr_apply_emp(String dr_apply_emp) {
		this.dr_apply_emp = dr_apply_emp;
	}

	public String getDr_approve_emp() {
		return dr_approve_emp;
	}

	public void setDr_approve_emp(String dr_approve_emp) {
		this.dr_approve_emp = dr_approve_emp;
	}

	public String getRcar_no() {
		return rcar_no;
	}

	public void setRcar_no(String rcar_no) {
		this.rcar_no = rcar_no;
	}

	public Timestamp getDr_start_time() {
		return dr_start_time;
	}

	public void setDr_start_time(Timestamp dr_start_time) {
		this.dr_start_time = dr_start_time;
	}

	public Timestamp getDr_end_time() {
		return dr_end_time;
	}

	public void setDr_end_time(Timestamp dr_end_time) {
		this.dr_end_time = dr_end_time;
	}

	public Timestamp getDr_start_time_actual() {
		return dr_start_time_actual;
	}

	public void setDr_start_time_actual(Timestamp dr_start_time_actual) {
		this.dr_start_time_actual = dr_start_time_actual;
	}

	public Timestamp getDr_end_time_actual() {
		return dr_end_time_actual;
	}

	public void setDr_end_time_actual(Timestamp dr_end_time_actual) {
		this.dr_end_time_actual = dr_end_time_actual;
	}

	public Byte getDr_approve() {
		return dr_approve;
	}

	public void setDr_approve(Byte dr_approve) {
		this.dr_approve = dr_approve;
	}

	public Byte getDr_cancel() {
		return dr_cancel;
	}

	public void setDr_cancel(Byte dr_cancel) {
		this.dr_cancel = dr_cancel;
	}

	public Integer getMiles_before() {
		return miles_before;
	}

	public void setMiles_before(Integer miles_before) {
		this.miles_before = miles_before;
	}

	public Integer getMiles_after() {
		return miles_after;
	}

	public void setMiles_after(Integer miles_after) {
		this.miles_after = miles_after;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	//取得申請方 員工資料
	public EmployeeVO getapplyEmp() {
		EmployeeService service = new EmployeeService();
		return service.emp(dr_apply_emp);
	}
	
	//取得被申請方 員工資料
	public EmployeeVO getapproveEmp() {
		EmployeeService service = new EmployeeService();
		return service.emp(dr_approve_emp);
	}





	@Override
	public String toString() {
		return "Car_Dispatch_RecordVO [dr_no=" + dr_no + ", dr_apply_st=" + dr_apply_st + ", dr_approve_st="
				+ dr_approve_st + ", dr_apply_emp=" + dr_apply_emp + ", dr_approve_emp=" + dr_approve_emp + ", rcar_no="
				+ rcar_no + ", dr_start_time=" + dr_start_time + ", dr_end_time=" + dr_end_time
				+ ", dr_start_time_actual=" + dr_start_time_actual + ", dr_end_time_actual=" + dr_end_time_actual
				+ ", dr_approve=" + dr_approve + ", dr_cancel=" + dr_cancel + ", miles_before=" + miles_before
				+ ", miles_after=" + miles_after + "]";
	}
	
	


}
