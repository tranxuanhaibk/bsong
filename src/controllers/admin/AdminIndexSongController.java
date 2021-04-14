package controllers.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CatDAO;
import dao.SongDAO;
import models.Category;
import models.Song;
import utils.AuthUtil;
import utils.DefineUtil;

public class AdminIndexSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexSongController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}
		// Truong tim kiem
		SongDAO songDao = new SongDAO();
		CatDAO catDao = new CatDAO();
		String sname = "";
		int scat = 0;
		if ((request.getParameter("sname")) != null) {
			// nguoi dung co nhap => muon tim kiem
			sname = request.getParameter("sname");
		}
		if ((request.getParameter("scat")) != null) {
			scat = Integer.parseInt(request.getParameter("scat"));
		}
		if (scat > 0) {
			// nguoi dung muon tim kiem danh muc
			scat = Integer.parseInt(request.getParameter("scat"));
		}
		Song song = new Song(sname, new Category(scat));
		List<Song> listSearch = songDao.findAllByIdAndNameOrderByNewsId(song);
		List<Category> listCat = catDao.findAll();
		
		// Phan trang
		
		int numberOfSongs = songDao.numberOfSongs();
		int numberOfPages = (int) Math.ceil((float) numberOfSongs/DefineUtil.NUMBER_PER_PAGE);
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
		}
		if (currentPage > numberOfPages || currentPage < 1) {
			currentPage = 1;
		}
		int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
		
		List<Song> songList = songDao.getItemsPagination(offset);
		if (scat > 0 || !"".equals(sname)) {
			songList = listSearch;
		}
		
		request.setAttribute("scat", scat);
		request.setAttribute("numberOfSongs", numberOfSongs);
		request.setAttribute("songList", songList);
		request.setAttribute("listCat", listCat);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("numberOfPages", numberOfPages);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
