package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnectionUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/bsong?useUnicode=true&characterEncoding=UTF-8";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	public static Connection getConnection() {
		ResultSet rs = null;
		Statement st = null;
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	}
	public static void close(Statement st) {
		if(st!=null) {
			try {
				st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	}
	public static void close(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	}
		public static void close(PreparedStatement pst) {
			if(pst!=null) {
				try {
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
		}
	}
	public static void close(Connection conn,Statement st,ResultSet rs) {
		close(rs);
		close(st);
		close(conn);
		
	}
	
	
	
}