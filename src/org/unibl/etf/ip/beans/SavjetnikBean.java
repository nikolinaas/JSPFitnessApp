package org.unibl.etf.ip.beans;

import java.util.ArrayList;
import java.util.Properties;

import javax.activation.FileDataSource;
import javax.sql.DataSource;

import org.unibl.etf.ip.dao.SavjetnikDAO;
import org.unibl.etf.ip.dto.Poruka;
import org.unibl.etf.ip.dto.Savjetnik;


public class SavjetnikBean {

	
	private Savjetnik savjetnik = new Savjetnik();
	
	
	
	public Savjetnik getSavjetnik() {
		return savjetnik;
	}

	public void setSavjetnik(Savjetnik savjetnik) {
		this.savjetnik = savjetnik;
	}

	public boolean LogIn(String username, String pass) {
		
		System.out.println("login");
		if(SavjetnikDAO.areCredentialsValid(username, pass))
		{
			savjetnik = getAdvisorByUsernameAndPass(username, pass);
			return true;
		}
	
		return false;
	}
	
	public Savjetnik getAdvisorByUsernameAndPass(String usern, String pass) {
		
	savjetnik =  SavjetnikDAO.getAdvisorByUsernameAndPassword(usern, pass);
	return savjetnik;
	
	}
	public ArrayList<Poruka> getAllMessages(Integer id){
		
		return SavjetnikDAO.getAllMessages(id);
	}
	
	public ArrayList<Poruka> getMessageByContent(String content,Integer id){
		return SavjetnikDAO.getMessageByContent(content, id);
	}
}
