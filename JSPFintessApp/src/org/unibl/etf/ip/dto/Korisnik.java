package org.unibl.etf.ip.dto;

public class Korisnik {

	private String JMBG;
	private String Ime;
	private String Prezimme;
	private String Email;
	private String BrojTelefona;
	private String Adresa;
	private Nalog nalog;

	public Korisnik(){
		
	}
	public Korisnik(String jMBG, String ime, String prezimme, String email, String brojTelefona, String adresa) {
		super();
		JMBG = jMBG;
		Ime = ime;
		Prezimme = prezimme;
		Email = email;
		BrojTelefona = brojTelefona;
		Adresa = adresa;
	}
	
	

	public Korisnik(String jMBG, String ime, String prezimme, String email, String brojTelefona, String adresa,
			Nalog nalogg) {
		super();
		JMBG = jMBG;
		Ime = ime;
		Prezimme = prezimme;
		Email = email;
		BrojTelefona = brojTelefona;
		Adresa = adresa;
		nalog = nalogg;
	}



	public Nalog getNalog() {
		return nalog;
	}
	public void setNalog(Nalog nalog) {
		this.nalog = nalog;
	}
	public String getJMBG() {
		return JMBG;
	}

	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}

	public String getIme() {
		return Ime;
	}

	public void setIme(String ime) {
		Ime = ime;
	}

	public String getPrezimme() {
		return Prezimme;
	}

	public void setPrezimme(String prezimme) {
		Prezimme = prezimme;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getBrojTelefona() {
		return BrojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		BrojTelefona = brojTelefona;
	}

	public String getAdresa() {
		return Adresa;
	}

	public void setAdresa(String adresa) {
		Adresa = adresa;
	}



}
