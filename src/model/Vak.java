package model;

import java.util.ArrayList;

public class Vak {
	private String vakNaam;
	private String vakCode;
	private ArrayList<Les> alleLessen = new ArrayList<Les>();
	
	public Vak(String vakC)	{
		vakCode = vakC;
	}
	
	public Vak(String vakC, String vkNaam)	{
		vakCode = vakC;
		vakNaam = vkNaam;
	}
	
	public String getVakNaam()	{
		return vakNaam;
	}
	
	public String getVakCode()	{
		return vakCode;
	}
	
	public void setVakNaam(String vkNaam)	{
		vakNaam = vkNaam;
	}
	
	public void setVakCode(String vCode)	{
		vakCode = vCode;
	}
	
	public boolean equals(Object obj)	{
		boolean isGelijk = false;
		if(obj instanceof Vak)	{
			Vak anderVak = (Vak) obj;
				
			if(	this.vakNaam.equals(anderVak.vakNaam) &&
				this.vakCode == anderVak.vakCode)	{
				isGelijk = true;
			}	
		}
		
		return isGelijk;
	}
	
	public void voegLesToe(Les nweLs)	{
		if(!alleLessen.contains(nweLs))	{
			alleLessen.add(nweLs);
		}
	}
	
	public void verwijderLes(Les exLs)	{
		if(alleLessen.contains(exLs))	{
			alleLessen.remove(exLs);
		}
	}
	
	public Les zoekLes(Les zLes)	{
		Les gezochteLes = null;
		for(Les l: alleLessen)	{
			if(l.equals(zLes))	{
				gezochteLes = l;
			}
		}
		return gezochteLes;
	}
}