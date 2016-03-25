package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Les {
	private LocalDateTime beginTijd;
	private LocalDateTime eindTijd;
	private ArrayList<Student> aanwezigeStudenten = new ArrayList<Student>();
	
	public Les(LocalDateTime bTijd, LocalDateTime eTijd)	{
		beginTijd = bTijd;
		eindTijd = eTijd;
	}
	
	public boolean equals(Object obj)	{
		boolean isGelijk = false;
		if(obj instanceof Les)	{
			Les andereLes = (Les) obj;
				
			if(	this.beginTijd == andereLes.beginTijd &&
				this.eindTijd == andereLes.eindTijd)	{
				isGelijk = true;
			}	
		}
		
		return isGelijk;
	}
	
	public ArrayList getAanwezigeStudenten()	{
		return aanwezigeStudenten;
	}
	
	public String toString()	{
		return "Les heeft begintijd " + beginTijd + " en eindtijd " + eindTijd;
	}
}
