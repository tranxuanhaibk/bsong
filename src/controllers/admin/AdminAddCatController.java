package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.CommonConstant;
import dao.CatDAO;
import models.Category;
import utils.AuthUtil;


public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminAddCatController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		CatDAO catDAO = new CatDAO();
		
		String name = request.getParameter("name");
		Category cat = new Category();
		cat.setName(name);
		if(catDAO.add(cat) > 0) {
			// Thêm thành công
			response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg="+CommonConstant.SUCCESS);
			return;
		} else {
			// Thất bại
			response.sendRedirect(request.getContentType()+"/admin/cat/add?msg="+CommonConstant.ERROR);
		}
	}

}
