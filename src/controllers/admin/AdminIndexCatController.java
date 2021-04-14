package controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CatDAO;
import models.Category;
import models.Song;
import utils.AuthUtil;
import utils.DBConnectionUtil;
import utils.DefineUtil;

public class AdminIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexCatController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		CatDAO catDao = new CatDAO();
		// Tim kiem
		String cname = "";
		if (request.getParameter("cname") != null) {
			cname = request.getParameter("cname");
		}
		Category cat = new Category(cname);
		List<Category> listSearch = catDao.findAllByIdAndNameOrderByNewsId(cat);
		
		// phan trang
		int numberofCats = catDao.numberOfCats();
		int numberofPages = (int) Math.ceil((float) numberofCats/DefineUtil.NUMBER_CAT_PAGE);
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
		}
		if (currentPage > numberofPages || currentPage < 1) {
			currentPage = 1;
		}
		int offset = (currentPage - 1) * DefineUtil.NUMBER_CAT_PAGE;
		List<Category> listCat = catDao.getItemsPagination(offset);
		if (!"".equals(cname)) {
			listCat = listSearch;
		}
		
		request.setAttribute("numberofCats", numberofCats);
		request.setAttribute("listCat", listCat);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("numberofPages", numberofPages);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
