package controllers.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
public class AdminEditSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditSongController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		SongDAO songDAO = new SongDAO();
		CatDAO catDao = new CatDAO();
		int id = Integer.parseInt(request.getParameter("id"));
		Song song  = songDAO.getItem(id);
		if (song == null) {
			response.sendRedirect(request.getContextPath() + "/admin/song/add?err=1");
			return;
		}
		
		// get category list
		List<Category> cat = catDao.findAll();
		request.setAttribute("song", song);
		request.setAttribute("cat", cat);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		SongDAO songDAO = new SongDAO();
		// lẤT thông tin các file 
		int catid = Integer.parseInt(request.getParameter("cat_id"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		String name = request.getParameter("name");
		String description = request.getParameter("preview");
		String detail = request.getParameter("detail");
		
		// upload
		Part filePart = request.getPart("picture");
		// get dữ liệu cũ
		Song song  = songDAO.getItem(id);
		if (song == null) {
			response.sendRedirect(request.getContextPath() + "/admin/song/add?err=1");
			return;
		} 
		// lấy tên file từ part
		
		String filename = FileUtil.rename(filePart.getSubmittedFileName());
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
			// Xóa file cũ
			String oldFilePath = dirPath + File.separator + song.getPicture();
			File oldFile = new File(oldFilePath);
			if (oldFile.exists()) {
				oldFile.delete();
			}
			// Ghi file mới
			String filePath = dirPath + File.separator + filename;
			filePart.write(filePath);
		} else {
			filename = song.getPicture();
		}
		Song item =  new Song(id, name, description, detail, null , filename, 0, new Category(catid, ""));
		// Tiến hành INSERT DATABASE
		
		if (songDAO.editItem(item) > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/song/index?msg="+CommonConstant.SUCCESS);
			return;
		} else {
			request.setAttribute("objSong", song);
			response.sendRedirect(request.getContextPath()+"/admin/song/index?msg="+CommonConstant.ERROR);
			return;
		}
	}

}
