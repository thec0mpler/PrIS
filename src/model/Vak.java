package model;

import java.util.ArrayList;
import org.eclipse.jetty.server.Authentication.User;

public class Vak {
	public String vakCode;
	public String naam;
	public String omschrijving;
	public ArrayList<User> deelnemers = new ArrayList<User>();
	public ArrayList<Les> alleLessen = new ArrayList<Les>();
	
	public Vak(String vC, String vN) {
		vakCode = vC;
		naam = vN;
	}
	
	public String getVakNaam() {
		return naam;
	}
	
	public String getOmschrijving()	{
		return omschrijving;
	}
	
	public String getVakCode() {
		return vakCode;
	}
	
	public void setNaam(String nm)	{
		naam = nm;
	}
	
	public void setOmschrijving(String om)	{
		omschrijving = om;
	}
	
	public void setVakCode(String cd)	{
		vakCode = cd;
	}
	
	public boolean equals(Object obj)	{
		boolean zelfde = false;
		
		if(obj instanceof Vak){
			Vak anderVak = (Vak) obj;
			if(this.vakCode.equals(anderVak.vakCode)&&
			   this.omschrijving.equals(anderVak.omschrijving)&&
			   this.naam.equals(anderVak.naam)){
				zelfde = true;
			}
		}	
		return zelfde;
	}
	
	public User zoekDeelnemer(int hogeschoolNummer)	{
		User return_deelnemer = null;
		for(User dn: deelnemers)	{
			if(((Student) dn).getHogeschoolNummer() == hogeschoolNummer)	{
				return_deelnemer = dn;
			}
		}
		return return_deelnemer;
	}
	
	public void voegLesToe(Les ls) {
		alleLessen.add(ls);
	}
	
	public void verwijderLes(Les exLs) {
		alleLessen.remove(exLs);
	}
	
	public Les zoekLes(int lesNummer) {
		Les return_les = null;
		for(Les l: alleLessen)	{
			if(((Les) l).getLesNummer() == lesNummer)	{
				return_les = l;
			}
		}
		return return_les;
	}
	
	public String toString()	{
		return "Vak " + naam + " met vakcode " + vakCode + " en omschrijving : /n" + omschrijving;
	}
	
}
