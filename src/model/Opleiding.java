package model;

import java.util.ArrayList;

import org.eclipse.jetty.server.Authentication.User;

public class Opleiding {
	private String naam;
	private int opleidingsNummer;
	private ArrayList<User> deelnemers = new ArrayList<User>();
	private ArrayList<Klas> alleKlassen = new ArrayList<Klas>();
	private ArrayList<RoosterBlok> alleBlokken = new ArrayList<RoosterBlok>();
	
	public Opleiding(String nm, int opNr)	{
		naam = nm;
		opleidingsNummer = opNr;
	}
	
	public String getNaam()	{
		return naam;
	}
	
	public int getOpleidingsNummer() {
		return opleidingsNummer;
	}
	
	public void setNaam(String nm)	{
		naam = nm;
	}
	
	public void setOpleidingsNummer(int opNr)	{
		opleidingsNummer = opNr;
	}
	
	public String toString()	{
		return "Opleiding " + naam + " met opleidingsnummer " + opleidingsNummer;
	}
}
