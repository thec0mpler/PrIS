package model;

import java.util.ArrayList;

public class Docent extends User {
	private ArrayList<Vak> heeftVakken = new ArrayList<Vak>();
	
	public Docent(String vrNaam, String tVoeg, String achterN){
		super(vrNaam, tVoeg, achterN);
	}
	
	public Docent(String volledigeNaam)	{
		super(volledigeNaam);
	}
	
//	public boolean equals(Object obj)	{
//		boolean isGelijk = false;
//		if(obj instanceof Docent)	{
//			Docent andereDocent = (Docent) obj;
//				
//			if(	this.voornaam.equals(andereDocent.voornaam) &&
//				this.tussenvoegsel.equals(andereDocent.tussenvoegsel) &&
//				this.achternaam.equals(andereDocent.achternaam)) {
//				isGelijk = true;
//			}
//		}
//		return isGelijk;
//	}	
	
	public ArrayList<Vak> getVakken()	{
		return heeftVakken;
	}
	
	public void voegVakToe(Vak nweVak)	{
		if(!heeftVakken.contains(nweVak))	{
			heeftVakken.add(nweVak);
		}
	}
	
	public void verwijderVak(Vak exVak)	{
		if(heeftVakken.contains(exVak))	{
			heeftVakken.remove(exVak);
		}
	}
	
	public String toString()	{
		return "" + voornaam + " " + tussenvoegsel + " " + achternaam;
	}
	
}
