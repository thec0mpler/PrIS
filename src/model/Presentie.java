package model;

public class Presentie {
	private boolean aanwezig;
	private boolean afgemeld;
	private Les presentieLes1;
	private Student deStudent;
	
	public Presentie(Student student, Les les)	{
		deStudent = student;
		presentieLes1 = les;
	}
	
	public boolean getAfgemeld()	{
		return afgemeld;
	}
	
	public boolean getAanwezig()	{
		return aanwezig;
	}
	
	public void setAfgemeld()	{
		afgemeld = true;
	}
	
	public void setAanwezig()	{
		aanwezig = true;
	}
	
	public String toString()	{
		String presentie = "NaN";
		if(aanwezig)	{
			presentie = "was aanwezig";
		}	else if (afgemeld)	{
			presentie = "was afwezig";
		}
		return deStudent.getVolledigeNaam() + " " + presentie + " voor de les " + presentieLes1.toString();
	}
}