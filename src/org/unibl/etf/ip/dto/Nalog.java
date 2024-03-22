package org.unibl.etf.ip.dto;

public class Nalog {
	private String KorisnickoIme;
	private String Lozinka;
	private int id;
	
	public Nalog() {
		
	}

	
	public Nalog(String korisnickoIme, String lozinka) {
		super();
		KorisnickoIme = korisnickoIme;
		Lozinka = lozinka;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getKorisnickoIme() {
		return KorisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		KorisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return Lozinka;
	}

	public void setLozinka(String lozinka) {
		Lozinka = lozinka;
	}
	
}
