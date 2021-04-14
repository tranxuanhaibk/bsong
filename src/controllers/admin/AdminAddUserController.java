package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.CommonConstant;
import dao.CatDAO;
import dao.UserDAO;
import models.Category;
import models.User;
import utils.AuthUtil;
import utils.StringUtil;

public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public AdminAddUserController() {
		super();
		userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");

		if (!"admin".equals(userInfo.getUsername())) {
			// Không được phép
			response.sendRedirect(request.getContextPath() + "/admin/user/index?err=3");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		
		if (!"admin".equals(userInfo.getUsername())) {
			// Không được phép
			
			response.sendRedirect(request.getContextPath() + "/admin/user/index?err=3");
			return;
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		// Làm thêm : VALIDATE DỮ LIỆU ĐẦU VÀO
		if (userDAO.hasUser(username)) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp?err=2");
			rd.forward(request, response);
			return;
		}
		password = StringUtil.md5(password);

		User item = new User(0, username, password, fullname);

		if (userDAO.addItem(item) > 0) {
			// Thành công
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=1");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
