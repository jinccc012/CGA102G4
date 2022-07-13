package utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.lineserver.model.LineServerService;


public class GetLineToken {
	
	
	
	public static String getLineToken(String code) throws Exception{
		Gson g = new Gson();
		String responseStr = callOauthGetResponse(code);
//		System.out.println(responseStr);
		TokenBean bean = g.fromJson(responseStr, TokenBean.class);
//		bean.print();
		return bean.access_token;
	}
	//  
	public static String callOauthGetResponse(String code) throws Exception {
		String urlPath = new String("https://notify-bot.line.me/oauth/token"); // https://notify-bot.line.me/oauth/token
		new LineServerService();
		// String urlPath = new
		// String("http://localhost:8080/Test1/HelloWorld?name=丁丁".getBytes("UTF-8"));
		// String param="name=" + URLEncoder.encode("張小豬","UTF-8");
//		String param = 	"grant_type=authorization_code" + 
//						"&redirect_uri=" + ngrokURL + "/CGA102G4_Maven/Member/LineServlet" +
//						"&client_id=" + LineUrlClass.CLIENT_ID + 
//						"&client_secret=" + LineUrlClass.CLIENT_SECRET +
//						"&code=" + code;
		// 只能寫死
		String ngrok = LineServerService.getLinePathInfo();
		String param = 	"grant_type=authorization_code" + 
				"&redirect_uri=" + ngrok + "/CGA102G4/Member/LineServlet" +
				"&client_id=" + "1a91jJLbmTeHcelyC6NF8H" + 
				"&client_secret=" + "AqVhcowcpzHNpBEr7UUROwVtFD52OYNWhzILT2JG7rE" +
				"&code=" + code;
		
		System.out.println(param);
		
		// 建立連線
		URL url = new URL(urlPath);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		// 設定引數
		httpConn.setDoOutput(true); // 需要輸出
		httpConn.setDoInput(true); // 需要輸入
		httpConn.setUseCaches(false); // 不允許快取
		httpConn.setRequestMethod("POST"); // 設定POST方式連線
		// 設定請求屬性
		httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		httpConn.setRequestProperty("Connection", "Keep-Alive");// 維持長連線
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

		if (HttpURLConnection.HTTP_OK == resultCode) {
			StringBuffer sb = new StringBuffer();
			String readLine = new String();
			BufferedReader responseReader = new BufferedReader(
					new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			while ((readLine = responseReader.readLine()) != null) {
				sb.append(readLine).append("\n");
			}
			responseReader.close();
//        System.out.println(sb.toString());
			return sb.toString();
		} 
		return "ERROR! NO RESPOSE STRING";
	}
}

class TokenBean {
	public String status;
	public String message;
	public String access_token;

	public void print() {
		System.out.println("列印回應物件-------------------------------");
		System.out.println("status: " + status);
		System.out.println("message: " + message);
		System.out.println("access_token: " + access_token);
		System.out.println("----------------------------------------");
	}
}
