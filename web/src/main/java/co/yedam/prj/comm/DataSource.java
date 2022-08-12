package co.yedam.prj.comm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	private static DataSource dataSource = new DataSource(); // 인스턴스 생성
	private Connection conn;
	
	private DataSource() {//private 생성자를 만들고		
	}   
	
	public static DataSource getInstance() { // 인스턴스를 리턴한다.
		return dataSource;
	}
	
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "micol", "1234");
			                                             //@여기에 db담당 컴퓨터 아이피
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
