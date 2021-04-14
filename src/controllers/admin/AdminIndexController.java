package controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import dao.CatDAO;
import dao.SongDAO;
import dao.UserDAO;
import utils.AuthUtil;


public class AdminIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminIndexController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		SongDAO songDao = new SongDAO();
		CatDAO catDao = new CatDAO();
		UserDAO userDao = new UserDAO();
		
		int numberofCats = catDao.numberOfCats();
		int numberofSongs = songDao.numberOfSongs();
		int numberofUsers = userDao.numberOfUsers();
		
		request.setAttribute("numberofUsers", numberofUsers);
		request.setAttribute("numberofCats", numberofCats);
		request.setAttribute("numberofSongs", numberofSongs);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/home/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
