package controllers.admin;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import constants.CommonConstant;
import dao.CatDAO;
import dao.SongDAO;
import models.Category;
import models.Song;
import utils.AuthUtil;
import utils.FileUtil;

@MultipartConfig
public class AdminAddSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddSongController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		CatDAO catDao = new CatDAO();
		request.setAttribute("catList", catDao.findAll());
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		SongDAO songDAO = new SongDAO();
		// lẤT thông tin các file 
		String name = request.getParameter("name");
		int catId = Integer.parseInt(request.getParameter("cat_id"));
		String description = request.getParameter("preview");
		String detail = request.getParameter("detail");
		
		Part filePart = request.getPart("picture");
		String filename = FileUtil.rename(filePart.getSubmittedFileName());
		System.out.println(filename);
		// upload file lên server
		// D :/bsong
		if (!"".equals(filename)) {
			String dirPath = request.getServletContext().getRealPath("") + CommonConstant.DIR_UPLOAD;
			File createDir = new File(dirPath);
			if (!createDir.exists()) {
				createDir.mkdir();
				
				// mkdir : tạo 1 thư mục
				// mkdirs : tạo nhiều thư mục
			}
			String filePath = dirPath + File.separator + filename;
			filePart.write(filePath);
			System.out.println(filePath);
		}
		Song song =  new Song(0, name, description, detail, null , filename, 0, new Category(catId, ""));
		// Tiến hành INSERT DATABASE
		
		if (songDAO.add(song) > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/song/index?msg="+CommonConstant.SUCCESS);
			return;
		} else {
			response.sendRedirect(request.getContextPath()+"/admin/song/index?msg="+CommonConstant.ERROR);
			return;
		}
	}

}
