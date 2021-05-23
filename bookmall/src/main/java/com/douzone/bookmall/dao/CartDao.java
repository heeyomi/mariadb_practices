package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.CartVo;

public class CartDao {


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


	public static boolean insertCart(int num, int quality, int tempMemNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = getConnection();
			String sql = "insert into cart values(?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, quality);
			pstmt.setInt(2, num);
			pstmt.setInt(3, tempMemNo);

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

	public List<CartVo> findAll() {
		List<CartVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select b.name, c.title, a.quantity, (c.price*a.quantity) as price from cart a, member b, book c where a.memberNo = b.no and a.bookNo = c.id";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString(1);
				String title = rs.getString(2);
				int quantity = rs.getInt(3);
				int price = rs.getInt(4);

				CartVo vo = new CartVo();
				vo.setName(name);
				vo.setTitle(title);
				vo.setCount(quantity);
				vo.setPrice(price);

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


	public static boolean deleteCart(int tempMemNo, int bookNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = getConnection();
			String sql = "delete from cart where memberNo = ? and bookno = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, tempMemNo);
			pstmt.setInt(2, bookNo);
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

}
