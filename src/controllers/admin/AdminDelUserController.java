package controllers.admin;

import java.io.IOException;
import java.util.List;

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

public class AdminDelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDelUserController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}
		UserDAO userDao = new UserDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		User user = userDao.getItem(id);
		if ("admin".equals(user.getUsername())) {
			// không được phép
			response.sendRedirect(request.getContextPath() + "/admin/user/index?err=6");
			return;
		} else {
			// Được phép xóa
			if ("admin".equals(userInfo.getUsername())) {
				if (userDao.delete(id) > 0) {
					// Thêm thành công
					response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=" + CommonConstant.SUCCESS);
					return;
				} else {
					// Thất bại
					response.sendRedirect(request.getContentType() + "/admin/user/index?msg=" + CommonConstant.ERROR);
				}
			} else {
				// Không được phép
				response.sendRedirect(request.getContextPath() + "/admin/user/index?err=6");
				return;
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
