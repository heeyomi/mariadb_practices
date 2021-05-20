package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDriverTest {

	public static void main(String[] args) {
		Connection conn = null;

		try {
			// 1. JDBC Driver 로딩
			Class.forName("driver.MyDriver");


			// 2. connect
			String url = "jdbc:mydb://192.168.254.41:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. 연결성공
			System.out.println("ok:" + conn	);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
