package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class RoosterBlok {
	private LocalDate beginDatum;
	private LocalDate eindDatum;
	private String periode;
	private ArrayList<Klas> alleKlassen = new ArrayList<Klas>();
	private ArrayList<Vak> alleVakken = new ArrayList<Vak>();
	private ArrayList<Presentie> presentieLijst = new ArrayList<Presentie>();
	
	public LocalDate getBeginDatum()	{
		return beginDatum;
	}
	
	public LocalDate getEindDatum()	{
		return eindDatum;
	}
	
	public String getPeriode()	{
		return periode;
	}
	
	public void setBeginDatum(LocalDate bDat)	{
		beginDatum = bDat;
	}
	
	public void setEindDatum(LocalDate eDat)	{
		eindDatum = eDat;
	}
	
	public void setPeriode(String per)	{
		periode = per;
	}
	
	public Klas zoekKlas(String kC)	{
		Klas return_klas = null;
		for(Klas k: alleKlassen)	{
			if(k.getKlasCode().equals(kC))	{
				return_klas = k;
			}
		}
		return return_klas;
	}
	
	public Vak zoekVak(String vC)	{
		Vak return_vak = null;
		for(Vak v: alleVakken)	{
			if(v.getVakCode().equals(vC))	{
				return_vak = v;
			}
		}
		return return_vak;
	}
}
