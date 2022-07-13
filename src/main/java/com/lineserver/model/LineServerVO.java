package com.lineserver.model;

public class LineServerVO {
	String ngrokURL;
	byte[] qrCode;
	
	public String getNgrokURL() {
		return ngrokURL;
	}
	public void setNgrokURL(String ngrokURL) {
		this.ngrokURL = ngrokURL;
	}
	public byte[] getQrCode() {
		return qrCode;
	}
	public void setQrCode(byte[] qrCode) {
		this.qrCode = qrCode;
	}
	
	
}
