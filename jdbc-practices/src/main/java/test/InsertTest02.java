package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest02 {

	public static void main(String[] args) {
		insert("영업");
		insert("개발");
		insert("기획");
	}
	
	
	public static boolean insert(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");


			// 2. connect
			String url = "jdbc:mysql://192.168.254.41:3307/employees";
			conn = DriverManager.getConnection(url, "hr", "hr");


			// 3. SQL문 준비
			String sql =
					" insert" + 
				    "   into dept" + 
					" values (null, ?)";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(Binding)
			pstmt.setString(1, name);
			//5. SQL문을 실행
			int count = pstmt.executeUpdate();
			result =  count == 1;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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

}
