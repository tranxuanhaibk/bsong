package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Category;
import models.Song;
import utils.DBConnectionUtil;
import utils.DefineUtil;

public class SongDAO {
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	private Connection conn;

	public List<Song> findAll() {
		List<Song> songList = new ArrayList<Song>();
		conn = DBConnectionUtil.getConnection();
		final String SQL = "SELECT s.*, c.name AS catName FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id ORDER BY s.id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SQL);
			while (rs.next()) {
				Song songlist = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), new Category(rs.getInt("cat_id"), rs.getString("catName")));
				songList.add(songlist);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, st, rs);

		}
		return songList;
	}
	public List<Song> findAllByIdAndNameOrderByNewsId(Song song) {
		List<Song> songList = new ArrayList<Song>();
		conn = DBConnectionUtil.getConnection();
		String SQL = "SELECT s.*, c.name AS catName FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id WHERE 1 ";
		if (!"".equals(song.getName())) {
			SQL += "AND s.name LIKE ? ";
		}
		if (song.getCat().getId() > 0) {
			SQL += "AND s.cat_id = ? ";
		}
		SQL += "ORDER BY s.id DESC";
		try {
			pst = conn.prepareStatement(SQL);
			if (song.getCat().getId() > 0 && !"".equals(song.getName())) {
				pst.setString(1, "%"+song.getName()+"%");
				pst.setInt(2, song.getCat().getId());
			} else {
				if (!"".equals(song.getName()) && song.getCat().getId() == 0){
					pst.setString(1, "%"+song.getName()+"%");
				} else if ("".equals(song.getName()) && song.getCat().getId() > 0) {
					pst.setInt(1, song.getCat().getId());
					
				}
				
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				Song songlist = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), new Category(rs.getInt("cat_id"), rs.getString("catName")));
				songList.add(songlist);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);

		}
		return songList;
	}

	public int add(Song song) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String SQL = "INSERT INTO songs (name, preview_text, detail_text, picture, cat_id) VALUES(?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(SQL);
			pst.setString(1, song.getName());
			pst.setString(2, song.getDescription());
			pst.setString(3, song.getDetail());
			pst.setString(4, song.getPicture());
			pst.setInt(5, song.getCat().getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst);
			DBConnectionUtil.close(conn);
		}

		return result;

	}

	public Song getItem(int id) {
		Song item = null;
		conn = DBConnectionUtil.getConnection();
		final String SQL = "SELECT id, name, preview_text, detail_text, date_create, picture, counter, cat_id FROM songs WHERE id = ?";
		try {
			pst = conn.prepareStatement(SQL);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				item = new Song(id, rs.getString("name"), rs.getString("preview_text"), rs.getString("detail_text"),
						rs.getTimestamp("date_create"), rs.getString("picture"), rs.getInt("counter"),
						new Category(rs.getInt("cat_id"), null));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(rs);
			DBConnectionUtil.close(pst);
			DBConnectionUtil.close(conn);
		}
		return item;
	}

	public int editItem(Song item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String SQL = "UPDATE songs SET name = ?, preview_text = ?, detail_text = ?, picture = ?, cat_id = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(SQL);
			pst.setString(1, item.getName());
			pst.setString(2, item.getDescription());
			pst.setString(3, item.getDetail());
			pst.setString(4, item.getPicture());
			pst.setInt(5, item.getCat().getId());
			pst.setInt(6, item.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst);
			DBConnectionUtil.close(conn);
		}

		return result;

	}

	public List<Song> findAll(int number) {
		List<Song> songList = new ArrayList<Song>();
		conn = DBConnectionUtil.getConnection();
		final String SQL = "SELECT s.*, c.name AS catName FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id ORDER BY s.id DESC LIMIT ?";
		try {
			pst = conn.prepareStatement(SQL);
			pst.setInt(1, number);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song songlist = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), new Category(rs.getInt("cat_id"), rs.getString("catName")));
				songList.add(songlist);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);

		}
		return songList;
	}

	public int delete(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String SQL = "DELETE FROM songs WHERE id = ?";
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

	public List<Song> getItemByCat(int catId) {
		List<Song> songList = new ArrayList<Song>();
		conn = DBConnectionUtil.getConnection();
		final String SQL = "SELECT s.*, c.name AS catName FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id WHERE cat_id = ? ORDER BY s.id DESC";
		try {
			pst = conn.prepareStatement(SQL);
			pst.setInt(1, catId);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song songlist = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), new Category(rs.getInt("cat_id"), rs.getString("catName")));
				songList.add(songlist);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);

		}
		return songList;

	}

	public List<Song> getRelatedItems(Song song, int number) {
		List<Song> songList = new ArrayList<Song>();
		conn = DBConnectionUtil.getConnection();
		final String SQL = "SELECT s.*, c.name AS catName FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id WHERE cat_id = ? && s.id != ? ORDER BY s.id DESC LIMIT ?";
		try {
			pst = conn.prepareStatement(SQL);
			pst.setInt(1, song.getCat().getId());
			pst.setInt(2, song.getId());
			pst.setInt(3, number);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song songlist = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), new Category(rs.getInt("cat_id"), rs.getString("catName")));
				songList.add(songlist);
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);

		}
		
		return songList;
	}

	public void increaseView(int id) {

		conn = DBConnectionUtil.getConnection();
		String SQL = "UPDATE songs SET counter = counter + 1 WHERE id = ?";
		try {
			pst = conn.prepareStatement(SQL);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(pst);
			DBConnectionUtil.close(conn);
		}

	}

	public int numberOfSongs() {

		conn = DBConnectionUtil.getConnection();
		final String SQL = "SELECT COUNT(*) AS count FROM songs";
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

	public List<Song> getItemsPagination(int offset) {
		List<Song> songList = new ArrayList<Song>();
		conn = DBConnectionUtil.getConnection();
		final String SQL = "SELECT s.*, c.name AS catName FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id ORDER BY s.id DESC LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(SQL);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song songlist = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), new Category(rs.getInt("cat_id"), rs.getString("catName")));
				songList.add(songlist);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);

		}
		return songList;
	}

	public int numberOfItems(int catId) {
		conn = DBConnectionUtil.getConnection();
		final String SQL = "SELECT COUNT(*) AS count FROM songs WHERE cat_id =?";
		try {
			pst = conn.prepareStatement(SQL);
			pst.setInt(1, catId);
			rs = pst.executeQuery();
			if (rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);

		}
		return 0;
	}

	public List<Song> getItemByCatPagination(int offset, int catId) {
		List<Song> songList = new ArrayList<Song>();
		conn = DBConnectionUtil.getConnection();
		final String SQL = "SELECT s.*, c.name AS catName FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id WHERE cat_id = ? ORDER BY s.id DESC LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(SQL);
			pst.setInt(1, catId);
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song songlist = new Song(rs.getInt("id"), rs.getString("name"), rs.getString("preview_text"),
						rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"),
						rs.getInt("counter"), new Category(rs.getInt("cat_id"), rs.getString("catName")));
				songList.add(songlist);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionUtil.close(conn, pst, rs);

		}
		return songList;
	}

}
