package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.BookVo;
import com.douzone.bookmall.vo.OrderVo;

public class OrderDao {
	
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
	
	public static boolean insertOrder(int memberNo, int price, String address) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = getConnection();
			String sql = "insert into orders values(null, (select num from (select ifnull( max(ordrNo)+1 ,\"1\") as num from orders where memberNo = ?) a), (select concat(name, \"/\", email) from member where no = ?), ?, ?, (select no from member where no = ?))";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, memberNo);
			pstmt.setInt(3, price);
			System.out.println(address);
			pstmt.setString(4, address);
			pstmt.setInt(5, memberNo);
			
			
			int count = pstmt.executeUpdate();
			result = count == 1;
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
	
	
	public static List<OrderVo> findAll() {
		List<OrderVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from orders";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int orderNo = rs.getInt(2);
				String orderer = rs.getString(3);
				int price = rs.getInt(4);
				String address = rs.getString(5);
				
				OrderVo vo = new OrderVo();
				vo.setOrderNo(orderNo);
				vo.setOrderer(orderer);
				vo.setPayPrice(price);
				vo.setShipDestination(address);
				
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
	
	public static List<OrderVo> findBookAll() {
		List<OrderVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from order_book;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int orderBookNo = rs.getInt(1);
				String title = rs.getString(2);
				int quantity = rs.getInt(3);
				int bookNo = rs.getInt(4);
				int orderNo = rs.getInt(5);
				
				OrderVo vo = new OrderVo();
				vo.setOrderBookNo(orderBookNo);
				vo.setTitle(title);
				vo.setQuantity(quantity);
				vo.setBookNo(bookNo);
				vo.setOrderNo(orderNo);
				
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
	
	public static OrderVo findByNo(int no) {
		OrderVo result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from orders where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				OrderVo vo = new OrderVo();
				no = rs.getInt(1);
				
				int orderNo = rs.getInt(2);
				String orderer = rs.getString(3);
				int price = rs.getInt(4);
				String address = rs.getString(5);
				
				vo.setOrderNo(orderNo);
				vo.setOrderer(orderer);
				vo.setPayPrice(price);
				vo.setShipDestination(address);
				
				result = vo;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
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
				System.out.println("error : " + e);
			}
		}
		return result;
	}
	
	public static boolean insertOrderBook(List<BookVo> list) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = getConnection();
			for (BookVo bookVo : list) {
				String sql = "insert into order_book values(null, ?, ?, ?, (select no from orders where no = (select max(no) from orders a)))";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, bookVo.getTitle());
				pstmt.setInt(2, bookVo.getPrice());
				pstmt.setInt(3, bookVo.getBookNo());
				
				int count = pstmt.executeUpdate();
				result = count == 1;
				
			}
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
}
