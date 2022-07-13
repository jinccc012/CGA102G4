package com.carrentable.model;
import java.sql.Timestamp;

public class CarRentableVO {
	private String st_no;
	private String model_no;
	private Timestamp reserve_time;
	private Integer total_cars;
	private Integer	rest_cars;
	private Integer foreign_cars;
	private Integer	rentable;
	
	CarRentableVO(){
		super();
	}
	
	public CarRentableVO(String st_no, String model_no, Timestamp reserve_time, Integer total_cars, Integer rest_cars,
			Integer foreign_cars, Integer rentable) {
		super();
		this.st_no = st_no;
		this.model_no = model_no;
		this.reserve_time = reserve_time;
		this.total_cars = total_cars;
		this.rest_cars = rest_cars;
		this.foreign_cars = foreign_cars;
		this.rentable = rentable;
	}

	public String getSt_no() {
		return st_no;
	}
	public void setSt_no(String st_no) {
		this.st_no = st_no;
	}
	public String getModel_no() {
		return model_no;
	}
	public void setModel_no(String model_no) {
		this.model_no = model_no;
	}
	public Timestamp getReserve_time() {
		return reserve_time;
	}
	public void setReserve_time(Timestamp reserve_time) {
		this.reserve_time = reserve_time;
	}
	public Integer getTotal_cars() {
		return total_cars;
	}
	public void setTotal_cars(Integer total_cars) {
		this.total_cars = total_cars;
	}
	public Integer getRest_cars() {
		return rest_cars;
	}
	public void setRest_cars(Integer rest_cars) {
		this.rest_cars = rest_cars;
	}
	public Integer getForeign_cars() {
		return foreign_cars;
	}
	public void setForeign_cars(Integer foreign_cars) {
		this.foreign_cars = foreign_cars;
	}
	public Integer getRentable() {
		return rentable;
	}
	public void setRentable(Integer rentable) {
		this.rentable = rentable;
	}
	
	
	@Override
	public String toString() {
		return "Car_RentableVO [st_no=" + st_no + ", model_no=" + model_no + ", reserve_time=" + reserve_time
				+ ", total_cars=" + total_cars + ", rest_cars=" + rest_cars + ", foreign_cars=" + foreign_cars
				+ ", rentable=" + rentable + "]";
	}
	
}
