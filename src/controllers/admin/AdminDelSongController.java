package controllers.admin;

import java.io.IOException;



import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.CommonConstant;

import dao.SongDAO;
import utils.AuthUtil;



public class AdminDelSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDelSongController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		SongDAO songDao = new SongDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		if(songDao.delete(id) > 0) {
			// Thêm thành công
			response.sendRedirect(request.getContextPath()+"/admin/song/index?msg="+CommonConstant.SUCCESS);
			return;
		} else {
			// Thất bại
			response.sendRedirect(request.getContentType()+"/admin/song/index?msg="+CommonConstant.ERROR);
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
