package controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;

import models.User;
import utils.AuthUtil;
import utils.StringUtil;

public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public AdminEditUserController() {
		super();
		userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?err=2");
			
			return;
		}
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if ("admin".equals(userDAO.getItem(userInfo.getId()).getUsername()) || (id == userInfo.getId())) {
			// Có quyền
			User item = userDAO.getItem(id);
			if (item != null) {
				request.setAttribute("user", item);
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() +"/admin/user/index?err=2");
				return;
			}
			
		} else {
			// Không có quyền
			response.sendRedirect(request.getContextPath() +"/admin/user/index?err=4");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?err=2");
			return;
		}
		
		// Lấy lại dữ liệu cũ
		User item = userDAO.getItem(id);
		if (item == null) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?err=2");
			return;
		}
		// Get DATA
		String password = request.getParameter("password");
		if ("".equals(password)) {
			password = item.getPassword();
		} else {
			password = StringUtil.md5(password);
		}
		String fullname = request.getParameter("fullname");
		// Làm thêm VALIDATE DỮ LIỆU ĐẦU VÀO
		// Dữ liệu mới
		item.setPassword(password);
		item.setFullname(fullname);
		
		// Thêm
		if (userDAO.editItem(item) > 0) {
			// Thành công
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=2");
			
		} else {
			// Thất bại
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp?err=1");
			rd.forward(request, response);
			
			return;
		}
	
		
	} 

}
