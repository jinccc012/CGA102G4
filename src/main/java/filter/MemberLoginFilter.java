package filter;

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

		//會員登入

public class MemberLoginFilter  implements Filter{
	

	
	//init 初始化
	public void init (FilterConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		//取得session
		HttpSession session = req.getSession();
		
		//重session判斷此帳號是否登入過
		Object username = session.getAttribute("account");
		if(username == null && !req.getRequestURI().equals("/CGA102G4/front_end/member/Register.html") 
				&& !req.getRequestURI().equals("/CGA102G4/front_end/member/ForgotPassword.html") 
				&& !req.getRequestURI().equals("/CGA102G4/front_end/member/mailcheck.jsp")
				&& !req.getRequestURI().equals("/CGA102G4/front_end/member/ContactList.jsp")) { //沒登入過
			System.out.println(req.getRequestURI()); 
			session.setAttribute("location", req.getRequestURI()); //會記錄你從哪個網頁近來
			resp.sendRedirect(req.getContextPath() + "/front_end/Login/Login.html"); //導到登入畫面
			return;
		}else { //登入過直接通過
			chain.doFilter(request, response);
		}
		
	}
	
}
