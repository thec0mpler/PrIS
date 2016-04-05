package model;

import java.text.Normalizer;

public abstract class User {
	protected String gebruikersnaam = "";
	private String wachtwoord = "";
	protected String voornaam = "";
	protected String tussenvoegsel = "";
	protected String achternaam = "";

	public User(String volledigeNaam) {
		String volledigeNaamGesplitst[] = volledigeNaam.split(" ");
		
		if (volledigeNaamGesplitst.length == 2) {
			this.voornaam = volledigeNaamGesplitst[0];
			this.achternaam = volledigeNaamGesplitst[1];
		} else if (volledigeNaamGesplitst.length == 3) {
			this.voornaam = volledigeNaamGesplitst[0];
			this.tussenvoegsel = volledigeNaamGesplitst[1];
			this.achternaam = volledigeNaamGesplitst[2];
		}
		
		this.maakGebruikersnaam();
	}
	
	public User(String voornaam, String tussenvoegsel, String achternaam) {
		this.voornaam = voornaam;
		this.tussenvoegsel = tussenvoegsel;
		this.achternaam = achternaam;
		
		this.maakGebruikersnaam();
	}
	
	public String getGebruikersNaam() {
		return this.gebruikersnaam;
	}
	
	public String getVoornaam() {
		return this.voornaam;
	}
	
	public String getTussenvoegsel() {
		return this.tussenvoegsel;
	}
	
	public String getAchternaam() {
		return this.achternaam;
	}
	
	public String getVolledigeNaam() {
		return this.voornaam + " " + this.tussenvoegsel + " " + this.achternaam;
	}
	
	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
	
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	
	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
	}
	
	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}
	
	public String normalizeGebruikersnaam(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\x00-\\x7F]", "");
		
		return str;
	}
	
	private void maakGebruikersnaam() {
		String gebruikersnaam = this.voornaam + this.tussenvoegsel + this.achternaam; 
		gebruikersnaam = this.normalizeGebruikersnaam(gebruikersnaam);
		
		this.gebruikersnaam = gebruikersnaam;
	}
	
	public boolean controleerWachtwoord(String wachtwoord) {
		if (this.wachtwoord.equals(wachtwoord)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "[" + this.getClass() + "]";
	}
}
