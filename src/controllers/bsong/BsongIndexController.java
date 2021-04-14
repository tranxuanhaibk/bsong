package controllers.bsong;

import java.io.IOException;
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
import utils.DefineUtil;


public class BsongIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BsongIndexController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getServletContext().getRealPath(""));
		SongDAO songDao = new SongDAO();
		CatDAO catDao = new CatDAO();
		List<Category> catList = catDao.findAll();
		// Tìm kiếm
		String sname = "";
		int scat = 0;
		if (request.getParameter("sname") != null) {
			sname = request.getParameter("sname");
		}
		if (request.getParameter("scat") != null) {
			scat =  Integer.parseInt(request.getParameter("scat"));
		} else {
			scat = 0;
		}
		Song song = new Song(sname, new Category(scat));
		List<Song> listSearch = songDao.findAllByIdAndNameOrderByNewsId(song);
		// Phân Trang
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		int numberOfItems = songDao.numberOfSongs();
		int numberOfPages = (int) Math.ceil((float) numberOfItems/DefineUtil.NUMBER_PER_PAGE);
		if (currentPage > numberOfPages || currentPage < 1) {
			currentPage = 1;
		}
		int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
		
		List<Song> songList = songDao.getItemsPagination(offset);
		if (!"".equals(sname) || scat > 0 ) {
			songList = listSearch;
		}
		
		request.setAttribute("scat", scat);
		request.setAttribute("catList", catList);
		request.setAttribute("songList", songList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("numberOfPages", numberOfPages);
		RequestDispatcher rd = request.getRequestDispatcher("/views/bsong/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
