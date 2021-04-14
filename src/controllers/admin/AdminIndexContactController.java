package controllers.admin;

import java.io.IOException;
import java.util.ArrayList;
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
import models.Contact;
import utils.AuthUtil;
import utils.DefineUtil;


public class AdminIndexContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactDAO contactDAO;

    public AdminIndexContactController() {
        super();
       contactDAO = new ContactDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		// Tìm kiếm
		String namecontact = "";
		if (request.getParameter("namecontact") != null) {
			namecontact = request.getParameter("namecontact");
		}
		Contact name = new Contact(namecontact);
		ArrayList<Contact> listSearch = contactDAO.fillAllByName(name);
		// phan trang
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
		}
		int numberofContacts = contactDAO.numberOfContacts();
		int numberofPages = (int) Math.ceil((float) numberofContacts/DefineUtil.NUMBER_CONTACT_PAGE);
		if (currentPage > numberofPages || currentPage < 1) {
			currentPage = 1;
		}
		int offset = (currentPage - 1) * DefineUtil.NUMBER_CONTACT_PAGE;
		
		List<Contact> listContact = contactDAO.getItemsPagination(offset);
		if (!"".equals(namecontact)) {
			listContact = listSearch;
		}
		request.setAttribute("listContact", listContact);
		request.setAttribute("numberofContacts", numberofContacts);
		request.setAttribute("numberofPages", numberofPages);
		request.setAttribute("currentPage", currentPage);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/contact/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
	}

}
