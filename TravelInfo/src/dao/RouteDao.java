package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteDao extends BaseDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ppst;

	public ResultSet query(String name) {
		try {
			if (conn == null)
				conn = this.openDBConnection();
			String sql = "SELECT fromCity , arivCity FROM flights "
					+ "WHERE "
					+ "	id IN "
					+ "(SELECT reservations.resvKey from reservations "
					+ "where reservations.custName = ?)";
			// 使结果集可以更新
			ppst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE);
			ppst.setString(1, name);
			rs = ppst.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
