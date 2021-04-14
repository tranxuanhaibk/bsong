package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Contact;
import models.User;
import utils.DBConnectionUtil;
import utils.DefineUtil;

public class ContactDAO {
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	
	public ArrayList<Contact> getItems() {
		 ArrayList<Contact> items = new ArrayList<Contact>();
		 
		 conn = DBConnectionUtil.getConnection();
		 String query = "SELECT id, name, email, website, message FROM contacts ORDER BY id DESC";
		 try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String website = rs.getString("website");
				String message = rs.getString("message");
				
				Contact item = new Contact(id, name, email, website, message);
				items.add(item);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn,st,rs);
		}
		return items;
	}

	public int delItem(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "DELETE FROM contacts WHERE id = ?";
		
		try {
			pst = conn.prepareStatement(query);
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

	public int addItem(Contact item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "INSERT INTO contacts(name, email, website, message) VALUES(?, ?, ?, ?)";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, item.getName());
			pst.setString(2, item.getEmail());
			pst.setString(3, item.getWebsite());
			pst.setString(4, item.getMessage());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst);
			DBConnectionUtil.close(conn);
		}
		return result;
		
	}
	public int numberOfContacts() {

		conn = DBConnectionUtil.getConnection();
		final String SQL = "SELECT COUNT(*) AS count FROM contacts";
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

	public List<Contact> getItemsPagination(int offset) {
		List<Contact> listContacts = new ArrayList<Contact>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM contacts ORDER BY id DESC LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_CONTACT_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Contact contacts = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getString("message"));
				listContacts.add(contacts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);
		}
		return listContacts;
	}

	public ArrayList<Contact> fillAllByName(Contact name) {
		ArrayList<Contact> listContacts = new ArrayList<Contact>();
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM contacts WHERE 1 ";
		if (!"".equals(name.getName())) {
			sql += "AND name LIKE ? ";
		}
		sql += "ORDER BY id DESC";
		try {
			pst = conn.prepareStatement(sql);
			if (!"".equals(name.getName())) {
				pst.setString(1, "%"+name.getName()+"%");
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				Contact contacts = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getString("message"));
				listContacts.add(contacts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);
		}
		return listContacts;
	
	}
}