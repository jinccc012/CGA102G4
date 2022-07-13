package filter;

import java.io.FileFilter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberVO;



public class LoginChickFilter  implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		
		MemberVO account = (MemberVO)session.getAttribute("account");
		if(account != null) {
			resp.sendRedirect(req.getContextPath() + "/index");		
		}else {
			// 設定網頁的到期時間，一旦過期則必須到服務器上重新調用
						resp.setDateHeader("Expires", -1);
//						
//						// Cache-Control 指定請求和響應應遵循的緩存機制 no-cache指示請求或響應消息是不能緩存的
						resp.setHeader("Cache-Control", "no-cache");
//						
						// 用於設定禁止瀏覽器從本地緩存中調用頁面內容，設定後一旦離開頁面就無法從Cache中再調出
						resp.setHeader("Pragma", "no-cache");
						chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
	
}
