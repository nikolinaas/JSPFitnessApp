package org.unibl.etf.ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.unibl.etf.ip.dto.Nalog;

public class NalogDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ACCOUNT_BY_ID = "SELECT * FROM nalog WHERE IDNALOG = ?";
	private static final String SQL_INSERT_IN_ACCOUNT = "INSERT INTO nalog (korisnicko_ime, lozinka) VALUES (?,?)";
	private static final String SQL_UPDATE_ACCOUNT = "UPDATE nalog SET korisnicko_ime = ?, lozinka = ? WHERE (idnalog = ?)";
	private static final String SQL_DELETE_ACCOUNT = "DELETE FROM nalog WHERE (idnalog = ?)";

	public static Nalog createAcc(Nalog nalog) {

		Nalog result = new Nalog();
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { nalog.getKorisnickoIme(), nalog.getLozinka() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_IN_ACCOUNT, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if (pstmt.getUpdateCount() > 0) {
				if(generatedKeys.next()) {
					result.setId(generatedKeys.getInt(1));
					result.setKorisnickoIme(nalog.getKorisnickoIme());
					result.setLozinka(nalog.getLozinka());
				}
				
			}
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
	public static Nalog getAccountById(int id) {
		Nalog res = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_SELECT_ACCOUNT_BY_ID, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				res = new Nalog(rs.getString("korisnicko_ime"), rs.getString("lozinka"));
				res.setId(rs.getInt("idnalog"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return res;
	}
	
	public static boolean editAccount(int id, Nalog nalog) {
		
		boolean res = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE_ACCOUNT, true);
			  pstmt.setInt(3, id);
	          pstmt.setString(1, nalog.getKorisnickoIme());
	          pstmt.setString(2, nalog.getLozinka());
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
	
	public static boolean deleteAccount(int id) {
		
		boolean res = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		
		try {
			connection = connectionPool.checkOut();
			
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_DELETE_ACCOUNT, true);
			  pstmt.setInt(1, id);
	      
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
