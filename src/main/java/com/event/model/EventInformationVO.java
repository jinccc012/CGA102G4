package com.event.model;

import java.sql.Date;
import java.util.Arrays;


public class EventInformationVO implements java.io.Serializable{
	private Integer event_no;
	private String  event_title;
	private String event_content;
	private Date event_start;
	private Date event_end;
	private String st_name_start;
	private String st_name_end;
	private String model_no;
	private String  event_discount;
	private byte[] event_photo;
	
	
	
	public EventInformationVO() {
		super();
	}
	public EventInformationVO(Integer event_no, String event_title, String event_content, Date event_start,
			Date event_end, String st_name_start, String st_name_end, String model_no, String event_discount,
			byte[] event_photo) {
		super();
		this.event_no = event_no;
		this.event_title = event_title;
		this.event_content = event_content;
		this.event_start = event_start;
		this.event_end = event_end;
		this.st_name_start = st_name_start;
		this.st_name_end = st_name_end;
		this.model_no = model_no;
		this.event_discount = event_discount;
		this.event_photo = event_photo;
	}
	public Integer getEvent_no() {
		return event_no;
	}
	public void setEvent_no(Integer event_no) {
		this.event_no = event_no;
	}
	public String getEvent_title() {
		return event_title;
	}
	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}
	public String getEvent_content() {
		return event_content;
	}
	public void setEvent_content(String event_content) {
		this.event_content = event_content;
	}
	public Date getEvent_start() {
		return event_start;
	}
	public void setEvent_start(Date event_start) {
		this.event_start = event_start;
	}
	public Date getEvent_end() {
		return event_end;
	}
	public void setEvent_end(Date event_end) {
		this.event_end = event_end;
	}
	public String getSt_name_start() {
		return st_name_start;
	}
	public void setSt_name_start(String st_name_start) {
		this.st_name_start = st_name_start;
	}
	public String getSt_name_end() {
		return st_name_end;
	}
	public void setSt_name_end(String st_name_end) {
		this.st_name_end = st_name_end;
	}
	public String getModel_no() {
		return model_no;
	}
	public void setModel_no(String model_no) {
		this.model_no = model_no;
	}
	public String getEvent_discount() {
		return event_discount;
	}
	public void setEvent_discount(String event_discount) {
		this.event_discount = event_discount;
	}
	public byte[] getEvent_photo() {
		return event_photo;
	}
	public void setEvent_photo(byte[] event_photo) {
		this.event_photo = event_photo;
	}
	@Override
	public String toString() {
		return "Event_InformationVO [event_no=" + event_no + ", event_title=" + event_title + ", event_content="
				+ event_content + ", event_start=" + event_start + ", event_end=" + event_end + ", st_name_start="
				+ st_name_start + ", st_name_end=" + st_name_end + ", model_no=" + model_no + ", event_discount="
				+ event_discount + ", event_photo=" + Arrays.toString(event_photo) + "]";
	}
	
	

}
