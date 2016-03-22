package model;

public abstract class User {

	protected String naam;
	protected int hogeschoolNummer;
	protected String adres;
	protected int telefoonNummer;
	protected String emailAdres;
	
	//constructor
	
	public String getNaam() {
		return naam;
	}
	
	public int getHogeschoolNummer() {
		return hogeschoolNummer;
	}
	
	public String adres() {
		return adres;
	}
	
	public int telefoonNummer() {
		return telefoonNummer;
	}
	
	public String emailAdres() {
		return emailAdres;
	}
	
	public void setNaam(String nm) {
		naam = nm;
	}
	
	
}
