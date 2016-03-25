package model;

import java.util.ArrayList;

public class Docent extends User {
	private ArrayList<Vak> heeftVakken = new ArrayList<Vak>();
	
	public Docent(String vrNaam, String tVoeg, String achterN){
		super(vrNaam, tVoeg, achterN);
	}
	
	public boolean equals(Object obj)	{
		boolean gelijk = false;	// NOG NIET AF
		return gelijk;
	}
	
	public void voegLesToe(Vak nweVak)	{
		if(!heeftVakken.contains(nweVak))	{
			heeftVakken.add(nweVak);
		}
	}
	
	public void verwijderLes(Vak exVak)	{
		if(heeftVakken.contains(exVak))	{
			heeftVakken.remove(exVak);
		}
	}
	
	public String toString()	{
		return "" + voornaam + " " + tussenvoegsel + " " + achternaam;
	}
	
}
