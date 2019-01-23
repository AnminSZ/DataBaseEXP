package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersDao extends BaseDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ppst;

	public ResultSet query() {
		try {
			if (conn == null)
				conn = this.openDBConnection();
			String sql = "select * from customers";
			// 使结果集可以更新
			ppst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = ppst.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public boolean insert(String p1) {
		try {
			if (conn == null)
				conn = this.openDBConnection();
			String sql = "insert into customers(custName) " + "values(?)";
			if(ppst == null)
				ppst = conn.prepareStatement(sql);
			ppst.setString(1, p1);
			ppst.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return false;
	}

	@Override
	public boolean closeDBConnection() {
		super.closeDBConnection();
		try {
			if (rs != null)
				rs.close();
			if (ppst != null)
				ppst.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
