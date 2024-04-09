package org.unibl.etf.ip.beans;

import java.util.ArrayList;

import org.unibl.etf.ip.dao.KorisnikDAO;
import org.unibl.etf.ip.dto.Korisnik;

public class KorisnikBean {

	private Korisnik korisnik = new Korisnik();

	public KorisnikBean() {

	}

	public ArrayList<Korisnik> getAllUsers() {

		return KorisnikDAO.getAll();
	}



	public Korisnik getUserById(Integer id) {

		return KorisnikDAO.getUserById(id);

	}
	
}
