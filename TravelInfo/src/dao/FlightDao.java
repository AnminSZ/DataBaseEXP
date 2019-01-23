package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightDao extends BaseDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ppst;

	public ResultSet query() {
		try {
			if(conn==null)
				conn = this.openDBConnection();
			String sql = "select * from flights";
			//使结果集可以更新
			
			ppst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = ppst.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public boolean insert(String p1, String p2, String p3, String p4, String p5, String p6) {
		try {
			if(conn==null)
				conn = this.openDBConnection();
			String sql = "insert into flights(flightNum,price,numSeats,numAvail,fromCity,arivCity) "
					+ "values(?,?,?,?,?,?)";
			if(ppst == null)
				ppst = conn.prepareStatement(sql);
			ppst.setString(1, p1);
			ppst.setInt(2, Integer.valueOf(p2));
			ppst.setInt(3, Integer.valueOf(p3));
			ppst.setInt(4, Integer.valueOf(p4));
			ppst.setString(5, p5);
			ppst.setString(6, p6);
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
			String sql = "update flights set numAvail = ? WHERE ID = ? ";
			String sql2 = "select numAvail from flights where id = " + id;
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
