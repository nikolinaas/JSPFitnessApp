package org.unibl.etf.ip.dto;

import java.util.ArrayList;

public class Savjetnik {

	private Integer id;
	private String ime;
	private String prezime;
	private String brtelefona;
	private String adresa;
	private String email;
	private Nalog nalog;
	private ArrayList<Poruka> poruke;

	public Savjetnik() {

	}

	public Savjetnik(Integer id, String ime, String prezime, String brtelefona, String adresa, String email,
			Nalog nalog) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.brtelefona = brtelefona;
		this.adresa = adresa;
		this.email = email;
		this.nalog = nalog;
	}

	public Savjetnik(String ime, String prezime, String brtelefona, String adresa, String email,
			Nalog nalog) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.brtelefona = brtelefona;
		this.adresa = adresa;
		this.email = email;
		this.nalog = nalog;
	}
	
	public ArrayList<Poruka> getPoruke() {
		return poruke;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPoruke(ArrayList<Poruka> poruke) {
		this.poruke = poruke;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getBrtelefona() {
		return brtelefona;
	}

	public void setBrtelefona(String brtelefona) {
		this.brtelefona = brtelefona;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Nalog getNalog() {
		return nalog;
	}

	public void setNalog(Nalog nalog) {
		this.nalog = nalog;
	}

}
