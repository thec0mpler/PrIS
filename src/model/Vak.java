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
		User return_deelnemer;
		for(Deelnemer dn: deelnemers)	{
			if(dn.hogeschoolNummer == hogeschoolNummer)	{
				return_deelnemer = dn;
			}
		}
		return return_deelnemer;
	}
	
	public String toString()	{
		return "Vak " + naam + " met vakcode " + vakCode + " en omschrijving : /n" + omschrijving;
	}
	
}