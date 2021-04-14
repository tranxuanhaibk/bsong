package controllers.bsong;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContactDAO;
import models.Contact;


public class BsongContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BsongContactController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher rd = request.getRequestDispatcher("/views/bsong/contact.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String message = request.getParameter("message");
		// LÀM THÊM : VALIDATE DỮ LIỆU ĐẦU VÀO
		Contact item = new Contact(0, name, email, website, message);
		ContactDAO contactDao = new ContactDAO();
		if (contactDao.addItem(item) > 0) {
			// Thêm thành công
			response.sendRedirect(request.getContextPath() + "/contact?msg=1");
			return;
		} else {
			// Thất bại
			RequestDispatcher rd = request.getRequestDispatcher("/views/bsong/contact.jsp?err=1");
			rd.forward(request, response);
		}
	}

}
