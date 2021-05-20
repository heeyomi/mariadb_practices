package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnenctionTest {

	public static void main(String[] args) {
		Connection conn = null;
		
		try {
			//1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2.
			String url = "jdbc:mysql://192.168.254.41:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. 연결 성공
			System.out.println("ok:"+conn);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+ e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("error:" + e);
			}
		}
	}

}
