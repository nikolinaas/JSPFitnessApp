package org.unibl.etf.ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PorukaDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_MARK_MESSAGE_AS_READ = "UPDATE poruka SET procitana = ? WHERE (idporuka = ?)";
	
	
	public static boolean markMessageAsRead(int idPoruke) {
		
		boolean res = false;
		
		Connection connection = null;
		ResultSet generatedKeys = null;
		
		try {
			connection = connectionPool.checkOut();
			
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_MARK_MESSAGE_AS_READ, true);
			  pstmt.setInt(2, idPoruke);
	          pstmt.setBoolean(1, true);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if (pstmt.getUpdateCount() > 0) {
				res = true;
			}
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return res;
	}
}
