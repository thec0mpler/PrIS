package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Les {
	private LocalDate beginDatum;
	private LocalDate eindDatum;
	private int lesNummer;
	private ArrayList<Student> aanwezigeDeelnemers = new ArrayList<Student>();
	
	public Les(LocalDate bD, LocalDate eD, int lN)	{
		beginDatum = bD;
		eindDatum = eD;
		lesNummer = lN;
	}
	
	public String toString()	{
		return "Les " + lesNummer + " met begindatum " + beginDatum + " en einddatum " + eindDatum;
	}
	
}
