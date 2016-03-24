package model;

public abstract class User {

	protected String naam;
	private String wachtwoord;
	protected String emailAdres;
	
	//constructor
	
	public String getNaam() {
		return naam;
	}
	
	public String getEmailAdres() {
		return emailAdres;
	}
	
	public void setNaam(String nm) {
		naam = nm;
	}
	
	public void setWachtwoord(String ww) {
		wachtwoord = ww;
	}
	
	public void setEmailAdres(String email)	{
		emailAdres = email;
	}
	
	public boolean controleerWachtwoord(String ww) {
		return ww.equals(wachtwoord);
	}
	
	public String toString()	{
		return naam + " heeft emailadres: " + emailAdres; 
	}
	
}
