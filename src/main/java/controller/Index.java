package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carmodel.model.CarModelService;
import com.carmodel.model.CarModelVO;

@WebServlet("/index")

public class Index extends HttpServlet {
	HttpSession session;
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
//		 response.setContentType("text/html;charset=UTF-8");
//		System.out.println("xxx");
		CarModelService carModelSvc = new CarModelService();
		session = request.getSession();
		
//		 前端取的model_no參數，進入資料庫取的圖片
		String model_no = request.getParameter("model_no");
		if (model_no != null) {
			ServletOutputStream out = response.getOutputStream();
			InputStream is = new ByteArrayInputStream(carModelSvc.getImges(model_no));
			BufferedImage bi = ImageIO.read(is);
			ImageIO.write(bi, "jpg", out);
		} else {
			List<CarModelVO> list = carModelSvc.getAll();
			request.setAttribute("carList", list);
			String url = "front_end/index/index.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	@Override
	public void destroy() {
//		session.removeAttribute("account");
	}


}
