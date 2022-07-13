package com.rcar.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.rcarorder.model.RcarOrderDAOImpl;
import com.rcarorder.model.RcarOrderService;
import com.rcarorder.model.RcarOrderVO;

public class RcarVO implements Serializable {

//	RCAR_NO	車輛編號(車牌)	VARCHAR
//	ST_NO	門市編號(車籍)	VARCHAR
//	MODEL_NO	車型編號	VARCHAR
//	RCAR_LOC	車輛所在地	VARCHAR
//	MILES	里程	INT
//	RCAR_STATUS	車輛狀態	TINYINT
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rcar_no;
	private String st_no;
	private String model_no;
	private String rcar_loc;
	private Integer miles;
	private Integer rcar_status;
	
	public RcarVO(String rcar_no, String st_no, String model_no, String rcar_loc, Integer miles, Integer rcar_status) {
		super();
		this.rcar_no = rcar_no;
		this.st_no = st_no;
		this.model_no = model_no;
		this.rcar_loc = rcar_loc;
		this.miles = miles;
		this.rcar_status = rcar_status;
	}

	public RcarVO() {
		super();
	}

	public String getRcar_no() {
		return rcar_no;
	}

	public void setRcar_no(String rcar_no) {
		this.rcar_no = rcar_no;
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

	public String getRcar_loc() {
		return rcar_loc;
	}

	public void setRcar_loc(String rcar_loc) {
		this.rcar_loc = rcar_loc;
	}

	public Integer getMiles() {
		return miles;
	}

	public void setMiles(Integer miles) {
		this.miles = miles;
	}

	public Integer getRcar_status() {
		return rcar_status;
	}

	public void setRcar_status(Integer rcar_status) {
		this.rcar_status = rcar_status;
	}
	
	public List<RcarOrderVO> getcar_order(LocalDate Date) { //join 訂單 查看車輛是否有訂單
		RcarOrderService rcarOrderService = new RcarOrderService();
		List<RcarOrderVO> getbetween_order = rcarOrderService.getbetween_order(rcar_no, Date);
		return getbetween_order;
	}
	
	public List<RcarOrderVO> getcar_order() {
		LocalDate now = LocalDate.now();
//		RcarOrderService rcarOrderService = new RcarOrderService();
		RcarOrderDAOImpl rcarOrderDAOImpl = new RcarOrderDAOImpl();
		List<RcarOrderVO> getbetween_order = rcarOrderDAOImpl.getbetween_order(rcar_no, now);
		return getbetween_order;
	}

	@Override
	public String toString() {
		return "RcarVO [rcar_no=" + rcar_no + ", st_no=" + st_no + ", model_no=" + model_no + ", rcar_loc=" + rcar_loc
				+ ", miles=" + miles + ", rcar_status=" + rcar_status + "]";
	}
	
	
	
}
