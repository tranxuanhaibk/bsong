package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Category;
import utils.DBConnectionUtil;
import utils.DefineUtil;

public class CatDAO {
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	private Connection conn;

	public List<Category> findAll() {
		List<Category> catList = new ArrayList<Category>();
		conn = DBConnectionUtil.getConnection();
		final String SQL = "SELECT * FROM categories ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SQL);
			while (rs.next()) {
				Category cat = new Category(rs.getInt("id"), rs.getString("name"));
				catList.add(cat);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, st, rs);

		}
		return catList;
	}
	

	public int add(Category cat) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String SQL = "INSERT INTO categories (name) VALUES(?)";
		try {
			pst = conn.prepareStatement(SQL);
			pst.setString(1, cat.getName());
			result = pst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst);
			DBConnectionUtil.close(conn);
		}

		return result;
	}

	public int delete(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String SQL = "DELETE FROM categories WHERE id = ?";
		try {
			pst = conn.prepareStatement(SQL);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst);
			DBConnectionUtil.close(conn);
		}
		return result;
	}

	public Category getItem(int id) {
		Category item = null;
		conn = DBConnectionUtil.getConnection();
		String SQL = "SELECT id, name FROM categories WHERE id = ?";
		try {
			pst = conn.prepareStatement(SQL);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				item = new Category(id, name);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);
		}
		return item;
	}

	public int editItem(int id, String name) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String SQL = "UPDATE categories SET name = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(SQL);
			pst.setString(1, name);
			pst.setInt(2, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst);
			DBConnectionUtil.close(conn);
		}
		return result;
	}
	public int numberOfCats() {
		conn = DBConnectionUtil.getConnection();
		final String SQL = "SELECT COUNT(*) AS count FROM categories";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SQL);
			if (rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, st, rs);

		}
		return 0;
	}


	public List<Category> getItemsPagination(int offset) {
		List<Category> listCats = new ArrayList<Category>();
		conn = DBConnectionUtil.getConnection();
		final String SQL = "SELECT * FROM categories ORDER BY id DESC LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(SQL);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_CAT_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Category listcats = new Category(rs.getInt("id"), rs.getString("name"));
				listCats.add(listcats);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);
		}
		return listCats;
	}


	public List<Category> findAllByIdAndNameOrderByNewsId(Category cat) {
		List<Category> catList = new ArrayList<Category>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM categories WHERE 1 ";
		if (!"".equals(cat.getName())) {
			sql += "AND name LIKE ? ";
		}
		sql += "ORDER BY id DESC";
		try {
			pst = conn.prepareStatement(sql);
			if (!"".equals(cat.getName())) {
				pst.setString(1, "%"+cat.getName()+"%");
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				Category cats = new Category(rs.getInt("id"),rs.getString("name"));
				catList.add(cats);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);
		}
		return catList;
	}

}
