package controllers.auth;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SongDAO;
import dao.UserDAO;
import models.Song;
import models.User;
import utils.StringUtil;


public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/auth/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = StringUtil.md5(request.getParameter("password"));
		UserDAO userDao = new UserDAO();
		User userInfo = userDao.findUserByUsername(username, password);
		if (userInfo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", userInfo);
			response.sendRedirect(request.getContextPath()+"/admin");
			return;
		} else {
			response.sendRedirect(request.getContextPath()+"/auth/login?err=1");
			return;
		}
	}

}
