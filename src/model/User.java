package model;

public abstract class User {
	protected String voornaam;
	protected String tussenvoegsel;
	protected String achternaam;
	protected String gebruikersnaam = voornaam + " " ;
	private String wachtwoord;
	
	public User(String volledigeNaam)	{
		String gesplitst[] = volledigeNaam.split(" ");
		
		if (gesplitst.length == 2) {
			this.voornaam  = gesplitst[0];
			this.achternaam = gesplitst[1];
		} else if (gesplitst.length == 3) {
			this.voornaam  = gesplitst[0];
			this.tussenvoegsel = gesplitst[1];
			this.achternaam = gesplitst[2];
		}
		
		this.maakGebruikersnaam();
	}
	
	public User(String vNaam, String tVoeg, String achterN){
		voornaam = vNaam;
		tussenvoegsel = tVoeg;
		achternaam = achterN;
		
		this.maakGebruikersnaam();
	}
	
	public String getVoornaam()	{
		return voornaam;
	}
	
	public String getTussenvoegsel()	{
		return tussenvoegsel;
	}
	
	public String getAchternaam()	{
		return achternaam;
	}
	
	public String getVolledigeNaam()	{
		return voornaam + " " + tussenvoegsel + " " + achternaam;
	}
	
	public void setWachtwoord(String ww)	{
		wachtwoord = ww;
	}
	
	public void setVoornaam(String vrNaam)	{
		voornaam = vrNaam;
	}
	
	public void setTussenvoegsel(String tVoeg)	{
		tussenvoegsel = tVoeg;
	}
	
	public void setAchternaam(String aNaam)	{
		achternaam = aNaam;
	}
	
	public void maakGebruikersnaam()	{
		gebruikersnaam = voornaam + tussenvoegsel + achternaam;
	}
	
	public String getGebruikersNaam()	{
		return gebruikersnaam;
	}
	
	public boolean controleerWachtwoord(String ww)	{
		boolean gelijk = false;
		if(wachtwoord.equals(ww))	{
			gelijk = true;
		}
		return gelijk;
	}
	
	public String toString()	{
		return "Dit is een abstracte User";
	}
}
