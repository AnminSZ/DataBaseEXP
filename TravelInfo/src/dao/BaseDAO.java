package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
/*	private String driver = "com.mysql.cj.jdbc.Driver";
	// ���ݿ����·��
	private String url = "jdbc:mysql://localhost:3306/travel?serverTimezone=UTC&characterEncoding=GBK";
	private String userName = "root";
	private String userPassword = "lam1996314";*/
	private Connection conn;
	String url="jdbc:Access:///db/travel.mdb";
	public Connection openDBConnection() {
		try {
			//����JDBC-ODBC��������
			Class.forName("com.hxtt.sql.access.AccessDriver");		
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url,userName,userPassword);
			conn = DriverManager.getConnection(url,"","");
			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean closeDBConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return true;
	}
}
