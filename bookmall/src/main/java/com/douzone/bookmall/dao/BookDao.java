package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.BookVo;

public class BookDao {

	public boolean insert(BookVo vo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = getConnection();
			String sql = "insert into book values(null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setInt(3, vo.getBookNo());
			int count = pstmt.executeUpdate();
			result =  count == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
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

	public List<BookVo> findAll() {
		List<BookVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select a.id , a.title, a.price, b.category from book a, category b where a.no = b.no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bookNo = rs.getInt(1);
				String title = rs.getString(2);
				int price = rs.getInt(3);
				String category = rs.getString(4);
				
				BookVo vo = new BookVo();
				vo.setBookNo(bookNo);
				vo.setTitle(title);
				vo.setPrice(price);
				vo.setCategory(category);
				
				result.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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

	public static BookVo findByBookNo(int bookNo) {
		BookVo result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select a.id , a.title, a.price, b.category from book a, category b where a.no = b.no and a.id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookNo);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				bookNo = rs.getInt(1);
				String title = rs.getString(2);
				int price = rs.getInt(3);
				String category = rs.getString(4);
				
				BookVo vo = new BookVo();
				vo.setBookNo(bookNo);
				vo.setTitle(title);
				vo.setPrice(price);
				vo.setCategory(category);
				
				result = vo;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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
	
	
	private static Connection getConnection() throws Exception {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3307/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		}
		return conn;
	}

}
