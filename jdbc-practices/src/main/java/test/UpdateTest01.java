package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest01 {
	public static void main(String[] args) {
		DeptVo vo = new DeptVo();
		vo.setNo(4L);
		vo.setName("전략기획팀");
		
		Boolean result = update(vo);
		if(result) {
			System.out.println("성공");
		}
	}
	
	public static boolean update(DeptVo vo) {
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
			String sql = "update dept set name = '" + vo.getName() +"' where no = " + vo.getNo();

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