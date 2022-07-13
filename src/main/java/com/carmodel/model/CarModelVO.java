package com.carmodel.model;


import java.io.Serializable;

// 車型
public class CarModelVO implements Serializable {
	
	/*	
		MODEL_NO    車型編號	VARCHAR
		LEVEL_NO	級距編號	CHAR
		MODEL_NAME	車型名稱	VARCHAR
		MODEL_CC	排氣量	SMALLINT
		MODEL_PRICE	定價(元/天)	SMALLINT
		MODEL_OIL	燃油	 VARCHAR
		MODEL_SEATS	乘坐人數	TINYINT
		MODEL_BAGGAGE	行李件數	VARCHAR
		CAR_INFO	車型簡介	VARCHAR
		CAR_PHOTO	圖片	MEDIUMBLOB
	*/
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String model_no;
	private String level_no;
	private String model_name;
	private Integer model_cc;
	private Integer model_price;
	private String model_oil;
	private Integer model_seats;
	private String model_baggage;
	private String car_info;
	private byte[] car_photo;
	
	public CarModelVO() {
		super();
	}
	
	public CarModelVO(String model_no, String level_no, String model_name, Integer model_cc, Integer model_price,
			String model_oil, Integer model_seats, String model_baggage, String car_info, byte[] car_photo) {
		super();
		this.model_no = model_no;
		this.level_no = level_no;
		this.model_name = model_name;
		this.model_cc = model_cc;
		this.model_price = model_price;
		this.model_oil = model_oil;
		this.model_seats = model_seats;
		this.model_baggage = model_baggage;
		this.car_info = car_info;
		this.car_photo = car_photo;
	}
	
	public String getModel_no() {
		return model_no;
	}
	
	public void setModel_no(String model_no) {
		this.model_no = model_no;
	}
	
	public String getLevel_no() {
		return level_no;
	}
	
	public void setLevel_no(String level_no) {
		this.level_no = level_no;
	}
	
	public String getModel_name() {
		return model_name;
	}
	
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	
	public Integer getModel_cc() {
		return model_cc;
	}
	
	public void setModel_cc(Integer model_cc) {
		this.model_cc = model_cc;
	}
	
	public Integer getModel_price() {
		return model_price;
	}
	
	public void setModel_price(Integer model_price) {
		this.model_price = model_price;
	}
	
	public String getModel_oil() {
		return model_oil;
	}
	
	public void setModel_oil(String model_oil) {
		this.model_oil = model_oil;
	}
	
	public Integer getModel_seats() {
		return model_seats;
	}
	
	public void setModel_seats(Integer model_seats) {
		this.model_seats = model_seats;
	}
	
	public String getModel_baggage() {
		return model_baggage;
	}
	
	public void setModel_baggage(String model_baggage) {
		this.model_baggage = model_baggage;
	}
	
	public String getCar_info() {
		return car_info;
	}
	
	public void setCar_info(String car_info) {
		this.car_info = car_info;
	}
	
	public byte[] getCar_photo() {
		return car_photo;
	}
	
	public void setCar_photo(byte[] car_photo) {
		this.car_photo = car_photo;
	}
	
	@Override
	public String toString() {
		return "carModelVO [model_no=" + model_no + ", level_no=" + level_no + ", model_name=" + model_name
				+ ", model_cc=" + model_cc + ", model_price=" + model_price + ", model_oil=" + model_oil
				+ ", model_stats=" + model_seats + ", model_baggage=" + model_baggage + ", car_info=" + car_info
			    + "]";
	}

}
