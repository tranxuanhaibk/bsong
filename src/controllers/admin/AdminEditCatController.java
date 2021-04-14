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
import dao.UserDAO;
import models.Category;
import models.User;
import utils.AuthUtil;
import utils.StringUtil;

public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	public AdminEditCatController() {
		super();
		
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
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		CatDAO catDao = new CatDAO();
		Category cat = catDao.getItem(id);
		request.setAttribute("cat", cat);
		request.setAttribute("idCat", id);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		// Lấy lại dữ liệu cũ
		CatDAO catDao = new CatDAO();
		Category cat = catDao.getItem(id);
		
		if (cat == null) {
			
			response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg="+CommonConstant.ERROR);
		}
		String name = request.getParameter("name");
		if (catDao.editItem(id, name) > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg="+CommonConstant.SUCCESS);
		} else {
			response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg="+CommonConstant.ERROR);
		}
	
		
	} 

}
