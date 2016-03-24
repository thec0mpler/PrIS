package model;

public class Student extends User {
	
	private int studentNummer;
		
	public Student(String gbNm, String ww) {
		gebruikersNaam = gbNm;
		wachtwoord = ww;
	}
		
	public boolean equals(Object obj)	{
		boolean zelfde = false;
		
		if(obj instanceof Student){
			Student andereStudent = (Student) obj;
			if(this.studentNummer == andereStudent.studentNummer){
				zelfde = true;
			}
		}
	return zelfde;
	}
	
	public String toString()	{
		return "Student " + gebruikersNaam + " heeft wachtwoord " + wachtwoord + " en zit in klas " + mijnKlas;
	}
}
