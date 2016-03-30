package model;

import java.util.ArrayList;

public class Student extends User {
	private ArrayList<Vak> volgtVakken = new ArrayList<Vak>();
	private int studentNummer;
	
	public Student(int studentNr, String vrNaam, String tVoeg, String achterN)	{
		super(vrNaam, tVoeg, achterN);
		studentNummer = studentNr;
	}
	
	public boolean equals(Object obj)	{
		boolean isGelijk = false;
		if(obj instanceof Student)	{
			Student andereStudent = (Student) obj;
				
			if(	this.voornaam.equals(andereStudent.voornaam) &&
				this.tussenvoegsel.equals(andereStudent.tussenvoegsel) &&
				this.achternaam.equals(andereStudent.achternaam) &&
				this.studentNummer == andereStudent.studentNummer)	{
				isGelijk = true;
			}	
		}
		
		return isGelijk;
	}
	
	public int getStudentNummer()	{
		return studentNummer;
	}
	
	public ArrayList<Vak> getVakken()	{
		return volgtVakken;
	}
	
	public void voegVakToe(Vak nweVak)	{
		if(!volgtVakken.contains(nweVak))	{
			volgtVakken.add(nweVak);
		}
	}
	
	public void verwijderLes(Vak exVak)	{
		if(volgtVakken.contains(exVak))	{
			volgtVakken.remove(exVak);
		}
	}
	
	public String toString()	{
		return voornaam + " " + tussenvoegsel + " " + achternaam + " met studentnummer : " + studentNummer;
	}
	
	
}
