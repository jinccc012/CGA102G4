package com.linenotify.model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LineSendMessageService {
	private static LineNotifyService  notifySVC = new LineNotifyService();
	
	// 如果會員有註冊，回傳token；否則，回傳一個字串叫"UnRegistered"
	private static String isRegisteredMember(String mebNo) {
		String token = "UnRegistered";
		
		LineNotifyVO notifyVO = notifySVC.getOne(mebNo);
		if(notifyVO != null) {
			token = notifyVO.getLineToken();
		}
		
		return token;
	}
	
	// 回傳-1 -> 此會員沒註冊
	// 回傳200 ->成功發訊息
	// 回傳400 -> BAD REQUEST
	public static int notifyMember(String mebNo, String message) throws Exception {
		
		if("UnRegistered".equals(isRegisteredMember(mebNo))) {
			return -1;
		}
		
		String notifyAPI = new String("https://notify-api.line.me/api/notify"); 
		
		String param = 	"message=" + URLEncoder.encode(message,"UTF-8");
		
		String token = isRegisteredMember(mebNo);
		
		// 建立連線
		URL url = new URL(notifyAPI);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		// 設定引數
		httpConn.setDoOutput(true); // 需要輸出
		httpConn.setDoInput(true); // 需要輸入
		httpConn.setUseCaches(false); // 不允許快取
		httpConn.setRequestMethod("POST"); // 設定POST方式連線
		// 設定請求屬性
		httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		httpConn.setRequestProperty("Authorization", "Bearer " + token);// 維持長連線
		httpConn.setRequestProperty("Charset", "UTF-8");
		// 連線,也可以不用明文connect，使用下面的httpConn.getOutputStream()會自動connect
		httpConn.connect();

		// 建立輸入流，向指向的URL傳入引數
		DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
		dos.writeBytes(param);
		dos.flush();
		dos.close();

		// 獲得響應狀態
		int resultCode = httpConn.getResponseCode();
		System.out.println(resultCode);// added
		
		return resultCode;
	}
}
