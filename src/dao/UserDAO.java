package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.tagext.TryCatchFinally;

import models.Category;
import models.User;
import utils.DBConnectionUtil;
import utils.DefineUtil;

public class UserDAO {
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	private Connection conn;

	public int addItem(User item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "INSERT INTO users(username, password, fullname) VALUES(?, ?, ?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, item.getUsername());
			pst.setString(2, item.getPassword());
			pst.setString(3, item.getFullname());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst);
			DBConnectionUtil.close(conn);
		}
		return result;

	}

	public List<User> findAll() {
		List<User> userList = new ArrayList<User>();
		conn = DBConnectionUtil.getConnection();
		final String SQL = "SELECT * FROM users ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SQL);
			while (rs.next()) {
				User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("fullname"));
				userList.add(user);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, st, rs);

		}
		return userList;

	}

	public boolean hasUser(String username) {

		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM users WHERE username = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs);
			DBConnectionUtil.close(pst);
			DBConnectionUtil.close(conn);
		}
		return false;

	}

	public User getItem(int id) {
		User item = null;
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT id, username, password, fullname FROM users WHERE id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				item = new User(id, username, password, fullname);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, st, rs);

		}
		return item;
	}

	public int editItem(User item) {

		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "UPDATE users SET password = ?, fullname = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, item.getPassword());
			pst.setString(2, item.getFullname());
			pst.setInt(3, item.getId());
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

	public int delete(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String SQL = "DELETE FROM users WHERE id = ?";
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

	public User findUserByUsername(String username, String password) {
		User user = null;
		conn = DBConnectionUtil.getConnection();
		final String SQL = "SELECT * FROM users WHERE username = ? && password = ?";
		try {
			pst = conn.prepareStatement(SQL);
			pst.setString(1, username);
			pst.setString(2, password);

			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("fullname"));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst);
			DBConnectionUtil.close(conn);

		}
		return user;
	}
	public int numberOfUsers() {

		conn = DBConnectionUtil.getConnection();
		final String SQL = "SELECT COUNT(*) AS count FROM users";
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

	public List<User> getItemsPagination(int offset) {
		List<User> listUserss = new ArrayList<User>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users ORDER BY id DESC LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_USER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				User users = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"));
				listUserss.add(users);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);
		}
		
		return listUserss;
	}

	public List<User> fillAllByUsername(User username) {
		List<User> listUser = new ArrayList<User>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM users WHERE 1 ";
		if (!"".equals(username.getUsername())) {
			sql += "AND username LIKE ? ";
		}
		sql += "ORDER BY id DESC";
		try {
			pst = conn.prepareStatement(sql);
			if (!"".equals(username.getUsername())) {
				pst.setString(1, "%"+username.getUsername()+"%");
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				User users = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"));
				listUser.add(users);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);
		}
		return listUser;
	}
}