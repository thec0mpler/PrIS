package model;

public abstract class User {

	private String naam;
	private int hogeschoolNummer;
	private String adres;
	private int telefoonNummer;
	private String emailAdres;
	
	//constructor
	
	public String getNaam() {
		return naam;
	}
	
	public int getHogeschoolNummer() {
		return hogeschoolNummer;
	}
	
	public String getAdres() {
		return adres;
	}
	
	public int getTelefoonNummer() {
		return telefoonNummer;
	}
	
	public String getEmailAdres() {
		return emailAdres;
	}
	
	public void setNaam(String nm) {
		naam = nm;
	}
	
	public void setHogeschoolNummer(int hNr)	{
		hogeschoolNummer = hNr;
	}
	
	public void setAdres(String adr)	{
		adres = adr;
	}
	
	public void setTelefoonNummer(int tel)	{
		telefoonNummer = tel;
	}
	
	public void setEmailAdres(String email)	{
		emailAdres = email;
	}
	
	public String toString()	{
		return naam + " heeft nummer " + hogeschoolNummer + ", woont op adres " + adres + ", "
				+ "heeft telefoonnummer " + telefoonNummer + " en emailadres " + emailAdres; 
	}
	
}
