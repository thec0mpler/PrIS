package model;

import java.util.ArrayList;

public class Klas {
	private String klasCode;
	private ArrayList<Leerling> leerlingen = new ArrayList<Leerling>();
	
	public Klas(String kC) {
		klasCode = kC;
	}
	
	public String getKlasCode() {
		return klasCode;
	}
	
	public void setKlasCode(String kc)	{
		klasCode = kc;
	}
	
	public boolean equals(Object obj)	{
		boolean zelfde = false;
		
		if(obj instanceof Klas){
			Klas andereLeerling = (Klas) obj;
			if(this.klasCode.equals(andereLeerling.klasCode)){
				zelfde = true;
			}
		}
		
		return zelfde;
	}
	
	public void voegLeerlingToe(Leerling nweLeerling)	{
		for(Leerling l: leerlingen)	{
			if(!nweLeerling.equals(l))	{
				leerlingen.add(nweLeerling);
			}
		}
	}
	
	public void verwijderLeerling(Leerling exKlasLeerling)	{
		for(Leerling l: leerlingen)	{
			if(l.equals(exKlasLeerling))	{
				leerlingen.remove(exKlasLeerling);
			}
		}
	}
	
	public String toString()	{
		String leerlingenInKlas = "Klas " + klasCode + " met leerlingen : /n";
		for(Leerling l: leerlingen)	{
			leerlingenInKlas = leerlingenInKlas + l.naam + " met studentnummer " + l.hogeschoolNummer + "/n";
		}
		return leerlingenInKlas;
	}
}
