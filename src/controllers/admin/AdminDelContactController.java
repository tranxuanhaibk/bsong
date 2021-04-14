package controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.CommonConstant;
import dao.CatDAO;
import dao.ContactDAO;
import models.Category;
import utils.AuthUtil;

public class AdminDelContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactDAO contactDAO;

	public AdminDelContactController() {
		super();
		contactDAO = new ContactDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		CatDAO catDao = new CatDAO();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/contact/index?err=1");
			return;
		}

		if (contactDAO.delItem(id) > 0) {
			// Thêm thành công
			response.sendRedirect(request.getContextPath() + "/admin/contact/index?msg=1");
			return;
		} else {
			// Thất bại
			response.sendRedirect(request.getContextPath() + "/admin/contact/index?err=1");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
