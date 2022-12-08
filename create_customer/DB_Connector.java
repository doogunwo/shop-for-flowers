package create_customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connector {
	public Connection con = null;
	String url = "jdbc:oracle:thin:@113.198.233.128:1521:XE";
	String id = "C##DOO";
	String pw = "1234";
	
	public DB_Connector() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // oracle jdbc 드라이버 적재
			System.out.println("드라이버 적재 성공");
		}catch(ClassNotFoundException e) {
			System.out.println("No Driver.");
		}
	}
	
	public void DB_Connect() {
		try {
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("Connection Ok");
		}catch(SQLException e) {
			System.out.println("Connection Fail");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}