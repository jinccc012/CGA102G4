package com.lineserver.model;

public class LineServerService {
	private static LineServerDAO  dao = new LineServerDAOImpl();;
	
	
	public static String getLinePathInfo(){
		return dao.getOne().getNgrokURL();
	}
	
	public static byte[] getQRCode() {
		return dao.getOne().getQrCode();
	}
}
