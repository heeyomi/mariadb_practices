package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.BookVo;

public class BookDao {
	public Boolean insert(BookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = getConnection();
			String sql ="insert into book values(null,  ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getStatus());
			pstmt.setLong(3, vo.getAuthorNo());
			
			int count = pstmt.executeUpdate();
			result =  count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("error:" + e);
			}
		}

		return result;
	}

	public void update(Long no, String string) {
		List<BookVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "update book set status = ? where book.no = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, string);
			pstmt.setLong(2, no);


			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				String status = rs.getString(1);
				Long bookNo = rs.getLong(2);

				BookVo vo = new BookVo();
				vo.setNo(bookNo);
				vo.setStatus(status);
				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("error:" + e);
			}
		}
	}

	public List<BookVo> findAll() {
		List<BookVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select * from book, author where book.author_no = author.no";
			pstmt = conn.prepareStatement(sql);


			rs = pstmt.executeQuery();
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String status = rs.getString(3);
				String authorName = rs.getString(6);

				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setStatus(status);
				vo.setAuthorName(authorName);
				
				result.add(vo);
				
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("error:" + e);
			}
		}

		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.254.41:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		}

		return conn;

	}
}
