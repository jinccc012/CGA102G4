package com.linenotify.model;

public class LineNotifyVO {
	private String mebNo;
	private String code;
	private String state;
	private String lineToken;
	
	
	public String getMebNo() {
		return mebNo;
	}
	public void setMebNo(String mebNo) {
		this.mebNo = mebNo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLineToken() {
		return lineToken;
	}
	public void setLineToken(String lineToken) {
		this.lineToken = lineToken;
	}
	
	@Override
	public String toString() {
		return "LineNotifyVO [mebNo=" + mebNo + ", code=" + code + ", state=" + state + ", lineToken=" + lineToken
				+ "]";
	}
	
	
}
