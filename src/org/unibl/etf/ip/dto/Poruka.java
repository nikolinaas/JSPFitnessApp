package org.unibl.etf.ip.dto;

public class Poruka {

	private int id;
	private String sadrzaj;
	private boolean procitana;
	private Savjetnik savjetnik;
	private Korisnik korisnik;
	
	public Poruka() {
		
	}
	
	public Poruka(int id, String sadrzaj, boolean procitana, Savjetnik savjetnik) {
		super();
		this.id = id;
		this.sadrzaj = sadrzaj;
		this.procitana = procitana;
		this.savjetnik = savjetnik;
	}
	
	
	public Poruka(int id, String sadrzaj, boolean procitana, Korisnik korisnik) {
		super();
		this.id = id;
		this.sadrzaj = sadrzaj;
		this.procitana = procitana;
		this.korisnik = korisnik;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSadrzaj() {
		return sadrzaj;
	}
	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}
	public boolean isProcitana() {
		return procitana;
	}
	public void setProcitana(boolean procitana) {
		this.procitana = procitana;
	}
	public Savjetnik getSavjetnik() {
		return savjetnik;
	}
	public void setSavjetnik(Savjetnik savjetnik) {
		this.savjetnik = savjetnik;
	}
}
