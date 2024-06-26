package org.unibl.etf.ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.unibl.etf.ip.dto.Nalog;
import org.unibl.etf.ip.dto.Poruka;
import org.unibl.etf.ip.dto.Savjetnik;

public class SavjetnikDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ALL_ACCOUNTS = "SELECT * FROM savjetnik JOIN nalog on nalog_idnalog=idnalog";
	private static final String SQL_SELECT_ALL_MESSAGES = "SELECT * FROM savjetnik JOIN poruka on id=savjetnik_id WHERE(id = ?)"; 
	private static final String SQL_SELECT_ADVISOR_BY_USERNAME_AND_PASS = "SELECT * FROM savjetnik JOIN nalog on nalog_idnalog=idnalog WHERE (korisnicko_ime = ? AND lozinka = ?)";
	
	public static ArrayList<Poruka> getAllMessages(Integer id) {
		
		ArrayList<Poruka> res = new ArrayList<Poruka>();
		
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		
		 try {
			connection = connectionPool.checkOut();
			
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL_MESSAGES, false, values);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				
				res.add(new Poruka(rs.getInt("idporuka"), rs.getString("sadrzaj"), rs.getString("naslov"), rs.getBoolean("procitana"),KorisnikDAO.getUserById(rs.getInt("korisnik_id"))));
				System.out.println("--------------" + rs.getString("naslov"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return res;
		
		
		
	}
	
	public static ArrayList<Poruka> getMessageByContent(String content, Integer id){
		
		ArrayList<Poruka> res = new ArrayList<Poruka>();
		
		for(Poruka p : getAllMessages(id)) {
			if(p.getSadrzaj().contains(content)) {
				res.add(p);	
			}
		}
		
		return res;
		
	}
	
	public static ArrayList<Savjetnik> getAllAdvisors(){
		ArrayList<Savjetnik> res = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		
		try {
			connection = connectionPool.checkOut();
			
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL_ACCOUNTS, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				res.add(new Savjetnik(rs.getInt("id"), rs.getString("ime"),rs.getString("prezime"), rs.getString("br_telefona"),rs.getString("adresa"), rs.getString("email"), NalogDAO.getAccountById(rs.getInt("nalog_idnalog"))));
				
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return res;
		
		
	}
	
	public static boolean areCredentialsValid(String username, String pass) {
		
		
		for(Savjetnik s : getAllAdvisors()) {
			if(s.getNalog().getKorisnickoIme().equals(username) && s.getNalog().getLozinka().equals(pass)) { 
				System.out.println("validni kredencijali");
				return true;
			}
		}
		
		return false;
	}
	
	
	public static Savjetnik getAdvisorByUsernameAndPassword(String usern, String pass) {
		
		Savjetnik res = null;
		
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_SELECT_ADVISOR_BY_USERNAME_AND_PASS, false, values);
			pstmt.setString(1, usern);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery(); 
			if (rs.next()){
				res = new Savjetnik(rs.getInt("id"), rs.getString("ime"), rs.getString("prezime"), rs.getString("email"),rs.getString("br_telefona"), rs.getString("adresa"), NalogDAO.getAccountById(rs.getInt("nalog_idnalog")));				
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
