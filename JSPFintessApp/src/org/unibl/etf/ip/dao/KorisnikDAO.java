package org.unibl.etf.ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.unibl.etf.ip.beans.NalogBean;
import org.unibl.etf.ip.dto.Korisnik;
import org.unibl.etf.ip.dto.Nalog;

public class KorisnikDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM korisnik";
	private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM korisnik WHERE JMBG = ?";
	private static final String SQL_INSERT_IN_USER = "INSERT INTO korisnik (JMBG, ime, prezime, email, broj_telefona, adresa, nalog_idnalog) VALUES (?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE_USER = "UPDATE korisnik SET ime = ?, prezime = ?, email = ?, broj_telefona = ?, adresa = ? WHERE (JMBG = ?)";
	private static final String SQL_DELETE_USER = "DELETE FROM korisnik WHERE (JMBG = ?)";

	public static ArrayList<Korisnik> getAll(){
		
		ArrayList<Korisnik> res = new ArrayList<Korisnik>();
		
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();

			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL_USERS, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				res.add(new Korisnik(rs.getString("JMBG"), rs.getString("ime"), rs.getString("prezime"), rs.getString("email"), rs.getString("broj_telefona"), rs.getString("adresa"),NalogDAO.getAccountById(rs.getInt("nalog_idnalog")) ));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return res;
		
	}
	

	public static Korisnik getUserById(String jmbg) {
		
		Korisnik res = null;
		
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {jmbg};
		try {
			connection = connectionPool.checkOut();

			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_USER_BY_ID, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				
			res = new Korisnik(rs.getString("JMBG"), rs.getString("ime"), rs.getString("prezime"), rs.getString("email"), rs.getString("broj_telefona"), rs.getString("adresa"), NalogDAO.getAccountById(rs.getInt("nalog_idnalog"))); 
			
			}

			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return res;

	}
	


	
}
