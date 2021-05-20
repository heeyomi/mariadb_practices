package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");


			// 2. connect
			String url = "jdbc:mysql://192.168.254.41:3307/employees";
			conn = DriverManager.getConnection(url, "hr", "hr");

			// 3. statement 생성
			stmt = conn.createStatement();
			
			//4. SQL문을 실행
			String sql = "select emp_no, date_format(birth_date, '%Y-%m-%d'), last_name from employees where first_name like 'pat%'";
			rs = stmt.executeQuery(sql);
			
			//5. 결과 가져오기
			while (rs.next()) {
				Long empNo = rs.getLong(1);
				String birthDate = rs.getString(2);
				String lastName = rs.getString(3);
				System.out.println(empNo + ":" + birthDate + ":" + lastName);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
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

	}
}
