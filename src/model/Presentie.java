package model;

import java.util.ArrayList;

public class Presentie {
	private boolean aanwezig;
	private ArrayList<Student> deStudent = new ArrayList<Student>();
	
	public boolean getAanwezig()	{
		return aanwezig;
	}
	
	public void wijzigAanwezigheid(User user)	{
		// TO DO
	}
	
	public String toString()	{
		return "" + aanwezig;
	}
}
