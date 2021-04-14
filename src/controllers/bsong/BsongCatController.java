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


public class BsongCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BsongCatController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		int currentPage = 1;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() +"/error");
			return;
		}
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		
		CatDAO catDao = new CatDAO();
		Category cat = catDao.getItem(id);
		if (cat == null) {
			
			response.sendRedirect(request.getContextPath() +"/error");
			return;
		}
		SongDAO songDao = new SongDAO();
	
		int numberOfItems = songDao.numberOfItems(id); 
		int numberOfPages = (int) Math.ceil((float) numberOfItems/DefineUtil.NUMBER_PER_PAGE);
		if (currentPage > numberOfPages || currentPage < 1) {
			currentPage = 1;
		}
		int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE; 
		List<Song> songs = songDao.getItemByCatPagination(offset,id); 
		
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("numberOfItems", numberOfItems);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("songs", songs);
		request.setAttribute("cat", cat);
		RequestDispatcher rd = request.getRequestDispatcher("/views/bsong/cat.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
