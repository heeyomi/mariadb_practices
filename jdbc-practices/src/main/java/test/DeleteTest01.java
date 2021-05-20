package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTest01 {

	public static void main(String[] args) {
		Boolean result = delete(2L);
		if (result) {
			System.out.println("success");
		} else {
			System.out.println("fail");
		}
	}

	public static boolean delete(Long no) {
		Connection conn = null;
		Statement stmt = null;
		boolean result = false;

		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");


			// 2. connect
			String url = "jdbc:mysql://192.168.254.41:3307/employees";
			conn = DriverManager.getConnection(url, "hr", "hr");

			// 3. statement 생성
			stmt = conn.createStatement();
			//4. SQL문을 실행
			String sql = "delte from dept where no = " + no;

			int count = stmt.executeUpdate(sql);
			result =  count == 1;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
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
