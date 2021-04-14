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
import models.Category;
import utils.AuthUtil;

public class AdminDelCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDelCatController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		CatDAO catDao = new CatDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		if(catDao.delete(id) > 0) {
			// Thêm thành công
			response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg="+CommonConstant.SUCCESS);
			return;
		} else {
			// Thất bại
			response.sendRedirect(request.getContentType()+"/admin/cat/index?msg="+CommonConstant.ERROR);
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
