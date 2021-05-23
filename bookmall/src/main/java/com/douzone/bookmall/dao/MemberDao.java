package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.MemberVo;

public class MemberDao {

	public boolean insert(MemberVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = getConnection();
			String sql = "insert into member values(null,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPhoneNumber());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getPassword());

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
				System.out.println("error : " + e);
			}
		}
		return result;
	}

	public List<MemberVo> findAll() {
		List<MemberVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select * from member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MemberVo vo = new MemberVo();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				String email = rs.getString(4);
				String password = rs.getString(5);
				
				vo.setMemNo(id);
				vo.setName(name);
				vo.setPhoneNumber(phone);
				vo.setEmail(email);
				vo.setPassword(password);
				
				result.add(vo);
			}
			
		} catch (SQLException e) {
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

	public static MemberVo findById(int id) {
		MemberVo result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select * from member where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MemberVo vo = new MemberVo();
				id = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				String email = rs.getString(4);
				String password = rs.getString(5);
				
				vo.setMemNo(id);
				vo.setName(name);
				vo.setPhoneNumber(phone);
				vo.setEmail(email);
				vo.setPassword(password);
				
				result = vo;
			}
			
		} catch (SQLException e) {
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
	
	public static boolean deleteMember(int memNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = getConnection();
			String sql = "delete from member where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
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
				System.out.println("error : " + e);
			}
		}
		return result;
	}
	
	private static Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3307/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이더 로딩 실패 : "+ e);
		}

		return conn;
	}




}
