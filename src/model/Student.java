package model;

import java.util.ArrayList;

public class Student extends User {
	private int nummer = 0;
	private Klas klas;
	private ArrayList<Vak> vakken = new ArrayList<Vak>();

	public Student(int nummer, String voornaam, String tussenvoegsel, String achternaam) {
		super(voornaam, tussenvoegsel, achternaam);

		this.nummer = nummer;
	}

	public int getStudentnummer() {
		return this.nummer;
	}
	
	public Klas getKlas() {
		return this.klas;
	}

	public ArrayList<Vak> getVakken() {
		return this.vakken;
	}
	
	public void setKlas(Klas klas) {
		this.klas = klas;
	}

	public void voegVakToe(Vak vak) {
		if (!this.vakken.contains(vak)) {
			this.vakken.add(vak);
		}
	}

	public void verwijderVak(Vak vak) {
		if (this.vakken.contains(vak)) {
			this.vakken.remove(vak);
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean isGelijk = false;
		
		if (obj instanceof Student) {
			Student andereStudent = (Student) obj;

			if (this.voornaam.equals(andereStudent.voornaam)
					&& this.tussenvoegsel.equals(andereStudent.tussenvoegsel)
					&& this.achternaam.equals(andereStudent.achternaam)
					&& this.nummer == andereStudent.nummer) {
				isGelijk = true;
			}
		}

		return isGelijk;
	}

	@Override
	public String toString() {
		return "[" + this.getClass() + "\n"
				+ "\tnummer: " + this.nummer + "\n"
				+ "\tvoornaam:" + this.voornaam + "\n"
				+ "\ttussenvoegsel: " + this.tussenvoegsel + "\n"
				+ "\tachternaam: " + this.achternaam + "\n"
				+"]";
	}

}
