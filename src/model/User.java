package model;

public abstract class User {
	protected String gebruikersnaam;
	private String wachtwoord;
	protected String voornaam;
	protected String tussenvoegsel;
	protected String achternaam;

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
		return gebruikersnaam;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getTussenvoegsel() {
		return tussenvoegsel;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public String getVolledigeNaam() {
		return voornaam + " " + tussenvoegsel + " " + achternaam;
	}

	public void setWachtwoord(String ww) {
		wachtwoord = ww;
	}

	public void setVoornaam(String vrNaam) {
		voornaam = vrNaam;
	}

	public void setTussenvoegsel(String tVoeg) {
		tussenvoegsel = tVoeg;
	}

	public void setAchternaam(String aNaam) {
		achternaam = aNaam;
	}
	
	public void maakGebruikersnaam() {
		gebruikersnaam = this.voornaam + this.tussenvoegsel + this.achternaam;
	}

	public boolean controleerWachtwoord(String ww) {
		boolean gelijk = false;
		
		if (wachtwoord.equals(ww)) {
			gelijk = true;
		}
		
		return gelijk;
	}

	@Override
	public String toString() {
		return "Dit is een abstracte User";
	}
}
