package model;

import java.util.ArrayList;

public class Klas {
	private String code;
	
	public Klas(String code)	{
		this.code = code;
	}
	
	public String getKlasCode()	{
		return code;
	}
	
	public void setKlasCode(String code)	{
		this.code = code;
	}
	
	@Override
	public boolean equals(Object obj)	{
		boolean gelijk = false;
		
		if (obj instanceof Klas) {
			Klas andereKlas = (Klas) obj;
			
			if (this.code.equals(andereKlas.code)) {
				gelijk = true;
			}
		}
		
		return gelijk;
	}
	
	@Override
	public String toString()	{
		return "[" + this.getClass() + "\n"
				+ "\tcode: " + this.code + "\n"
				+ "]";
	}
}
