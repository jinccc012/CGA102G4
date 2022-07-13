package com.rcarorder.model;

import java.sql.Timestamp;

import com.rcar.model.RcarService;
import com.rcar.model.RcarVO;

//租車訂單
public class RcarOrderVO {
//	RCARO_NO	租車訂單編號	INT
//	MEB_NO	會員編號	VARCHAR
//	LEVEL_NO	車輛等級	VARCHAR	
//	MODEL_NO	租賃車車種	VARCHAR
//	RCAR_NO	租賃車輛編號	VARCHAR
//	RCARO_PPICKTIME	預計取車時間	TIMESTAMP
//	RCARO_PPRETTIME	預計還車時間	TIMESTAMP
//	RCARO_RPICKTIME	實際取車時間	TIMESTAMP
//	RCARO_RRETTIME	實際還車時間	TIMESTAMP
//	RCARO_PICKUPLOC	取車地點(門市編號)	VARCHAR
//	RCARO_RETURNLOC	還車地點(門市編號)	VARCHAR
//	RCARO_RETURNLOC_ACTUAL	實際還車地點(門市編號)	VARCHAR
//	RCARO_TIME	訂單成立時間	TIMESTAMP
//	RCARO_PAY	出車金額	INT
//	RCARO_EXTRA_PAY	額外收費	INT
//	RCARO_EXTRA_PAY_STATUS	額外收費狀態	TINYINT
//	CONSUME_POINT	使用會員點數	INT
//	EARN_POINT	獲得會員點數	INT
//	EVENT_NO	活動編號	INT
//	RCARO_STATUS	訂單狀態	TINYINT
//	RCARO_NOTE	訂單備註	VARCHAR
//	LESSEE_NAME	承租人姓名(駕駛人)	VARCHAR
	
	
	
	private Integer rcaro_no;
	private String meb_no;
	private String level_no;
	private String model_no;
	private String rcar_no;
	private Timestamp rcaro_ppicktime;
	private Timestamp rcaro_pprettime;
	private Timestamp rcaro_rpicktime;
	private Timestamp rcaro_rrettime;
	private String rcaro_pickuploc;
	private String rcaro_returnloc;
	private String rcaro_returnloc_actual;
	private Timestamp rcaro_time;
	private Integer rcaro_pay;
	private Integer rcaro_extra_pay;
	private Integer rcaro_extra_pay_status;
	private Integer consume_point;
	private Integer earn_point;
	private Integer event_no;
	private Integer rcaro_status;
	private String rcaro_note;
	private String lessee_name;
	
	
	
	
	public RcarOrderVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RcarOrderVO(Integer rcaro_no, String meb_no, String level_no, String model_no, String rcar_no,
			Timestamp rcaro_ppicktime, Timestamp rcaro_pprettime, Timestamp rcaro_rpicktime, Timestamp rcaro_rrettime,
			String rcaro_pickuploc, String rcaro_returnloc, String rcaro_returnloc_actual, Timestamp rcaro_time,
			Integer rcaro_pay, Integer rcaro_extra_pay, Integer rcaro_extra_pay_status, Integer consume_point,
			Integer earn_point, Integer event_no, Integer rcaro_status, String rcaro_note, String lessee_name) {
		super();
		this.rcaro_no = rcaro_no;
		this.meb_no = meb_no;
		this.level_no = level_no;
		this.model_no = model_no;
		this.rcar_no = rcar_no;
		this.rcaro_ppicktime = rcaro_ppicktime;
		this.rcaro_pprettime = rcaro_pprettime;
		this.rcaro_rpicktime = rcaro_rpicktime;
		this.rcaro_rrettime = rcaro_rrettime;
		this.rcaro_pickuploc = rcaro_pickuploc;
		this.rcaro_returnloc = rcaro_returnloc;
		this.rcaro_returnloc_actual = rcaro_returnloc_actual;
		this.rcaro_time = rcaro_time;
		this.rcaro_pay = rcaro_pay;
		this.rcaro_extra_pay = rcaro_extra_pay;
		this.rcaro_extra_pay_status = rcaro_extra_pay_status;
		this.consume_point = consume_point;
		this.earn_point = earn_point;
		this.event_no = event_no;
		this.rcaro_status = rcaro_status;
		this.rcaro_note = rcaro_note;
		this.lessee_name = lessee_name;
	}









	public String getLevel_no() {
		return level_no;
	}


	public void setLevel_no(String level_no) {
		this.level_no = level_no;
	}


	public Integer getRcaro_no() {
		return rcaro_no;
	}




	public void setRcaro_no(Integer rcaro_no) {
		this.rcaro_no = rcaro_no;
	}




	public String getMeb_no() {
		return meb_no;
	}




	public void setMeb_no(String meb_no) {
		this.meb_no = meb_no;
	}




	public String getModel_no() {
		return model_no;
	}




	public void setModel_no(String model_no) {
		this.model_no = model_no;
	}




	public String getRcar_no() {
		return rcar_no;
	}




	public void setRcar_no(String rcar_no) {
		this.rcar_no = rcar_no;
	}




	public Timestamp getRcaro_ppicktime() {
		return rcaro_ppicktime;
	}




	public void setRcaro_ppicktime(Timestamp rcaro_ppicktime) {
		this.rcaro_ppicktime = rcaro_ppicktime;
	}




	public Timestamp getRcaro_pprettime() {
		return rcaro_pprettime;
	}




	public void setRcaro_pprettime(Timestamp rcaro_pprettime) {
		this.rcaro_pprettime = rcaro_pprettime;
	}




	public Timestamp getRcaro_rpicktime() {
		return rcaro_rpicktime;
	}




	public void setRcaro_rpicktime(Timestamp rcaro_rpicktime) {
		this.rcaro_rpicktime = rcaro_rpicktime;
	}




	public Timestamp getRcaro_rrettime() {
		return rcaro_rrettime;
	}




	public void setRcaro_rrettime(Timestamp rcaro_rrettime) {
		this.rcaro_rrettime = rcaro_rrettime;
	}




	public String getRcaro_pickuploc() {
		return rcaro_pickuploc;
	}




	public void setRcaro_pickuploc(String rcaro_pickuploc) {
		this.rcaro_pickuploc = rcaro_pickuploc;
	}




	public String getRcaro_returnloc() {
		return rcaro_returnloc;
	}




	public void setRcaro_returnloc(String rcaro_returnloc) {
		this.rcaro_returnloc = rcaro_returnloc;
	}




	public String getRcaro_returnloc_actual() {
		return rcaro_returnloc_actual;
	}




	public void setRcaro_returnloc_actual(String rcaro_returnloc_actual) {
		this.rcaro_returnloc_actual = rcaro_returnloc_actual;
	}




	public Timestamp getRcaro_time() {
		return rcaro_time;
	}




	public void setRcaro_time(Timestamp rcaro_time) {
		this.rcaro_time = rcaro_time;
	}




	public Integer getRcaro_pay() {
		return rcaro_pay;
	}




	public void setRcaro_pay(Integer rcaro_pay) {
		this.rcaro_pay = rcaro_pay;
	}




	public Integer getRcaro_extra_pay() {
		return rcaro_extra_pay;
	}




	public void setRcaro_extra_pay(Integer rcaro_extra_pay) {
		this.rcaro_extra_pay = rcaro_extra_pay;
	}




	public Integer getRcaro_extra_pay_status() {
		return rcaro_extra_pay_status;
	}




	public void setRcaro_extra_pay_status(Integer rcaro_extra_pay_status) {
		this.rcaro_extra_pay_status = rcaro_extra_pay_status;
	}




	public Integer getConsume_point() {
		return consume_point;
	}




	public void setConsume_point(Integer consume_point) {
		this.consume_point = consume_point;
	}




	public Integer getEarn_point() {
		return earn_point;
	}




	public void setEarn_point(Integer earn_point) {
		this.earn_point = earn_point;
	}




	public Integer getEvent_no() {
		return event_no;
	}




	public void setEvent_no(Integer event_no) {
		this.event_no = event_no;
	}




	public Integer getRcaro_status() {
		return rcaro_status;
	}




	public void setRcaro_status(Integer rcaro_status) {
		this.rcaro_status = rcaro_status;
	}




	public String getRcaro_note() {
		return rcaro_note;
	}




	public void setRcaro_note(String rcaro_note) {
		this.rcaro_note = rcaro_note;
	}




	public String getLessee_name() {
		return lessee_name;
	}




	public void setLessee_name(String lessee_name) {
		this.lessee_name = lessee_name;
	}
	
	public RcarVO getrcarvo() {
		RcarService service = new RcarService();
		RcarVO rcarVO = service.getCar(rcar_no);
		return rcarVO;
	}


	@Override
	public String toString() {
		return "RcarOrderVO [rcaro_no=" + rcaro_no + ", meb_no=" + meb_no + ", level_no=" + level_no + ", model_no="
				+ model_no + ", rcar_no=" + rcar_no + ", rcaro_ppicktime=" + rcaro_ppicktime + ", rcaro_pprettime="
				+ rcaro_pprettime + ", rcaro_rpicktime=" + rcaro_rpicktime + ", rcaro_rrettime=" + rcaro_rrettime
				+ ", rcaro_pickuploc=" + rcaro_pickuploc + ", rcaro_returnloc=" + rcaro_returnloc
				+ ", rcaro_returnloc_actual=" + rcaro_returnloc_actual + ", rcaro_time=" + rcaro_time + ", rcaro_pay="
				+ rcaro_pay + ", rcaro_extra_pay=" + rcaro_extra_pay + ", rcaro_extra_pay_status="
				+ rcaro_extra_pay_status + ", consume_point=" + consume_point + ", earn_point=" + earn_point
				+ ", event_no=" + event_no + ", rcaro_status=" + rcaro_status + ", rcaro_note=" + rcaro_note
				+ ", lessee_name=" + lessee_name + "]";
	}






	
	
}
