package controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import models.Category;
import models.User;
import utils.AuthUtil;
import utils.DefineUtil;


public class AdminIndexUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminIndexUserController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		UserDAO userDAO = new UserDAO();
		
		// Tìm Kiếm
		String nameUser = "";
		if (request.getParameter("nameuser") != null) {
			nameUser = request.getParameter("nameuser");
		}
		User username = new User(nameUser);
		List<User> listSearch = userDAO.fillAllByUsername(username);
		
		// Phân Trang
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
		}
		int numberofUsers = userDAO.numberOfUsers();
		int numberofPages = (int) Math.ceil((float) numberofUsers/DefineUtil.NUMBER_USER_PAGE);
		if (currentPage > numberofPages || currentPage < 1) {
		currentPage = 1;
		}
		int offset = (currentPage - 1) * DefineUtil.NUMBER_USER_PAGE;
		List<User> userLists = userDAO.getItemsPagination(offset);
		
		// Xét điều kiện
		if (!"".equals(nameUser)) {
			userLists = listSearch;
		}
		
		request.setAttribute("userLists", userLists);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("numberofPages", numberofPages);
		request.setAttribute("numberofUsers", numberofUsers);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
