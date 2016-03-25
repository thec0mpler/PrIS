package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Roosterblok {
	private LocalDate beginDatum;
	private LocalDate eindDatum;
	private char periode;
	private ArrayList<Vak> blokHeeftVakken = new ArrayList<Vak>();
	private ArrayList<Klas> deKlassen = new ArrayList<Klas>();
	private ArrayList<Presentie> allePresentieLijsten = new ArrayList<Presentie>();
	private ArrayList<Docent> heeftDocenten = new ArrayList<Docent>();
	
	public Roosterblok(LocalDate bD, LocalDate eD, char per)	{
		beginDatum = bD;
		eindDatum = eD;
		periode = per;
	}
	
	public LocalDate getBeginDatum()	{
		return beginDatum;
	}
	
	public LocalDate getEindDatum()	{
		return eindDatum;
	}
	
	public char getPeriode()	{
		return periode;
	}
	
	public void setBeginDatum(LocalDate bDat)	{
		beginDatum = bDat;
	}
	
	public void setEindDatum(LocalDate eDat)	{
		eindDatum = eDat;
	}
	
	public void setPeriode(char per)	{
		periode = per;
	}
	
	public void voegVakToe(Vak vk)	{
		if(!blokHeeftVakken.contains(vk)){
			blokHeeftVakken.add(vk);
		}
	}
	
	public void verwijderVak(Vak exVk)	{
		if(blokHeeftVakken.contains(exVk))	{
			blokHeeftVakken.remove(exVk);
		}
	}
	
	public Klas zoekKlas(String kC)	{
		Klas gezochteKlas = null;
		for(Klas k: deKlassen)	{
			if(k.getKlasCode().equals(kC))	{
				gezochteKlas = k;
			}
		}
		return gezochteKlas;
	}
	
	public Vak zoekVak(String vC)	{
		Vak gezochteVak = null;
		for(Vak v: blokHeeftVakken)	{
			if(v.getVakCode().equals(vC))	{
				gezochteVak = v;
			}
		}
		return gezochteVak;
	}
	
	public ArrayList<Docent> getDocenten()	{
		return heeftDocenten;
	}
	
	public String toString()	{
		return "Roosterblok met begindatum " + beginDatum + " en einddatum " + eindDatum + " is periode " + periode;
	}
}
