package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberDAOImpl;

@WebServlet("/mailverify")
public class MailVerify extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		MemberDAOImpl memberDAOImpl = new MemberDAOImpl();
		HttpSession session = req.getSession();
		ServletContext context = getServletContext();
		String acc = req.getParameter("acc");
		String num = req.getParameter("num");
		System.out.println(acc + "" +num);
//		ServletContext context = getServletContext();
		@SuppressWarnings("unchecked")
		Map<String, String> n = (HashMap<String, String>)context.getAttribute(acc);
		if(n == null) {
			req.setAttribute("check", "false");
			req.setAttribute("mess", "驗證信件已過期");
			req.getRequestDispatcher("/front_end/member/mailcheck.jsp").forward(req, resp);
			return;
		}
		Integer obj = Integer.parseInt(n.get("int"));
		if(obj == Integer.parseInt(num)) {
			boolean updatepassword = memberDAOImpl.updatepassword(n.get("id"), n.get("password"));
			if(updatepassword) {
				req.setAttribute("check", "true");
				context.removeAttribute(acc);
				req.getRequestDispatcher("/front_end/member/mailcheck.jsp").forward(req, resp);
			}else {
				req.setAttribute("check", "false");
				req.setAttribute("mess", "驗證失敗");
				context.removeAttribute(acc);
				req.getRequestDispatcher("/front_end/member/mailcheck.jsp").forward(req, resp);
			}
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
