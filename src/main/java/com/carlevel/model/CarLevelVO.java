package com.carlevel.model;


import java.io.Serializable;

// 車輛級距
public class CarLevelVO implements Serializable {
	
	/*
		LEVEL_NO	級距編號	VARCHAR
		LEVEL_NAME	級距名稱	VARCHAR
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String level_no;
	private String level_name;
	
	public CarLevelVO() {
		super();
		
	}

	public CarLevelVO(String level_no, String level_name) {
		super();
		this.level_no = level_no;
		this.level_name = level_name;
	}

	public String getLevel_no() {
		return level_no;
	}

	public void setLevel_no(String level_no) {
		this.level_no = level_no;
	}

	public String getLevel_name() {
		return level_name;
	}

	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}

	@Override
	public String toString() {
		return "carLevelVO [level_no=" + level_no + ", level_name=" + level_name + "]";
	}
	
}
