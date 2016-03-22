package model;

public class Student extends User {
	private String gebruikersNaam;
	private String wachtwoord;
	private Klas mijnKlas;
	
	public Student(String gbNm, String ww) {
		gebruikersNaam = gbNm;
		wachtwoord = ww;
	}
	
	public String getGebruikersNaam() {
		return gebruikersNaam;
	}
	
	public boolean controleerWachtwoord(String ww) {
		return ww.equals(wachtwoord);
	}
	
	public void setMijnKlas(Klas k) {
		mijnKlas = k;
	}
	
	public Klas getMijnKlas() {
		return mijnKlas;
	}
	
	public boolean equals(Object obj)	{
		boolean zelfde = false;
		
		if(obj instanceof Student){
			Student andereStudent = (Student) obj;
			if(this.gebruikersNaam.equals(andereStudent.gebruikersNaam)&&
			   this.wachtwoord.equals(andereStudent.wachtwoord)&&
			   this.mijnKlas == andereStudent.mijnKlas){
				zelfde = true;
			}
		}	
		return zelfde;
	}
	
	public String toString()	{
		return "Student " + gebruikersNaam + " heeft wachtwoord " + wachtwoord + " en zit in klas " + mijnKlas;
	}
}
