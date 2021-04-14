package controllers.bsong;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SongDAO;
import models.Song;


public class BsongDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BsongDetailController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/error");
			return;
		}
		SongDAO songDao = new SongDAO();
		
		Song song = songDao.getItem(id);
		if (song == null) {
			response.sendRedirect(request.getContextPath()+"/error");
			return;
		}
		// Tăng view
		HttpSession session = request.getSession();
		String hasVisited =  (String) session.getAttribute("hasVisited: "+ id);
		
		if (hasVisited == null) {
			session.setAttribute("hasVisited: "+ id, "yes");
			session.setMaxInactiveInterval(3600);
			// increase page view
			songDao.increaseView(id);
		}
		
		request.setAttribute("song", song);
		List<Song> relatedSongs =  songDao.getRelatedItems(song,5);
		request.setAttribute("relatedSongs", relatedSongs);
		RequestDispatcher rd = request.getRequestDispatcher("/views/bsong/detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
