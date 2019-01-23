package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDao extends BaseDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ppst;

	public ResultSet query() {
		try {
			if (conn == null)
				conn = this.openDBConnection();
			String sql = "select * from cars";
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

	public boolean insert(String p1, String p2, String p3, String p4, String p5) {
		try {
			if (conn == null)
				conn = this.openDBConnection();
			String sql = "insert into cars(type,location,price,numCars,numAvail) " + "values(?,?,?,?,?)";
			if(ppst == null)
				ppst = conn.prepareStatement(sql);
			ppst.setString(1, p1);
			ppst.setString(2, p2);
			ppst.setInt(3, Integer.valueOf(p3));
			ppst.setInt(4, Integer.valueOf(p4));
			ppst.setInt(5, Integer.valueOf(p5));
			ppst.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(int id) {
		try {
			if(conn==null)
				conn = this.openDBConnection();
			String sql = "update cars set numAvail = ? WHERE ID = ? ";
			String sql2 = "select numAvail from cars where id = " + id;
			ppst = conn.prepareStatement(sql2);
			ResultSet temp = ppst.executeQuery();
			temp.next();
			int avail = temp.getInt("numAvail");
			ppst = conn.prepareStatement(sql);
			ppst.setInt(1, avail-1);
			ppst.setInt(2, id);
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
