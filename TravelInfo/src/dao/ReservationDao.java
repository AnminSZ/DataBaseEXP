package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDao extends BaseDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ppst;
	FlightDao flightDAO = new FlightDao();
	private CarDao carDAO = new CarDao();
	private HotelDao hotelDAO = new HotelDao();

	public ResultSet query() {
		try {
			if (conn == null)
				conn = this.openDBConnection();
			String sql = "SELECT\r\n" + "	reservations.custName,\r\n" + "	type. NAME AS typename,\r\n"
					+ "	flights.flightNum AS resvKey,\r\n" + "	reservations.resvKey as rid\r\n" + "FROM\r\n"
					+ "	reservations,\r\n" + "	type,\r\n" + "	flights\r\n" + "WHERE\r\n"
					+ "	reservations.resvType = type.id\r\n" + "AND type.id = 1\r\n"
					+ "AND reservations.resvKey = flights.id\r\n" + "UNION ALL\r\n" + "SELECT\r\n"
					+ "	reservations.custName,\r\n" + "	type. NAME AS typename,\r\n" + "	cars.type AS resvKey,\r\n"
					+ "reservations.resvKey as rid\r\n" + "FROM\r\n" + "	reservations,\r\n" + "	type,\r\n"
					+ "	cars\r\n" + "WHERE\r\n" + "	reservations.resvType = type.id\r\n" + "AND type.id = 2\r\n"
					+ "AND reservations.resvKey = cars.id\r\n" + "UNION ALL\r\n" + "SELECT\r\n"
					+ "	reservations.custName,\r\n" + "	type. NAME AS typename,\r\n" + "	hotels.name AS resvKey,\r\n"
					+ "reservations.resvKey as rid\r\n" + "FROM\r\n" + "	reservations,\r\n" + "	type,\r\n"
					+ "	hotels\r\n" + "WHERE\r\n" + "	reservations.resvType = type.id\r\n" + "AND type.id = 3\r\n"
					+ "AND reservations.resvKey = hotels.id";
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

	public boolean insert(String p1, int p2, int p3) {
		try {
			if (conn == null)
				conn = this.openDBConnection();
			String sql = "insert into reservations(custName,resvType,resvKey) " + "values(?,?,?)";
			if (ppst == null)
				ppst = conn.prepareStatement(sql);
			ppst.setString(1, p1);
			ppst.setInt(2, p2);
			ppst.setInt(3, p3);
			ppst.executeUpdate();
			if(p2==1)
				flightDAO.update(p3);
			else if(p2==2)
				carDAO.update(p3);
			else if(p2==3)
				hotelDAO.update(p3);
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
