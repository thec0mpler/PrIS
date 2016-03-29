package model;

import java.util.ArrayList;

public class Klas {
	private String klasCode;
	private ArrayList<Student> heeftStudenten = new ArrayList<Student>();
	
	public Klas(String kCode)	{
		klasCode = kCode;
	}
	
	public String getKlasCode()	{
		return klasCode;
	}
	
	public void setKlasCode(String kCode)	{
		klasCode = kCode;
	}
	
	public boolean equals(Object obj)	{
		boolean gelijk = false;
		return gelijk;
	}
	
	public void voegStudentToe(Student nwSt)	{
		if(!heeftStudenten.contains(nwSt))	{
			heeftStudenten.add(nwSt);
		}
	}
	
	public void verwijderStudent(Student exSt)	{
		if(heeftStudenten.contains(exSt))	{
			heeftStudenten.remove(exSt);
		}
	}
	
	public ArrayList<Student> getStudenten()	{
		return heeftStudenten;
	}
	
	public Student zoekStudent(Student St)	{
		Student gezochteStudent = null;
		for(Student student: heeftStudenten)	{
			if(student.equals(St))	{
				gezochteStudent = St;
			}
		}
		return gezochteStudent;
	}
	
	public String toString()	{
		return "Klas met klascode " + klasCode + ".";
	}
}
