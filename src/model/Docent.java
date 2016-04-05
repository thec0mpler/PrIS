package model;

import java.util.ArrayList;

public class Docent extends User {
	private ArrayList<Vak> vakken = new ArrayList<Vak>();

	public Docent(String volledigeNaam) {
		super(volledigeNaam);
	}

	public Docent(String voornaam, String tussenvoegsel, String achternaam) {
		super(voornaam, tussenvoegsel, achternaam);
	}

	public ArrayList<Vak> getVakken() {
		return this.vakken;
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
		
		if (obj instanceof Docent) {
			Docent andereDocent = (Docent) obj;
			
			if (this.voornaam.equals(andereDocent.voornaam)
					&& this.tussenvoegsel.equals(andereDocent.tussenvoegsel)
					&& this.achternaam.equals(andereDocent.achternaam)) {
				isGelijk = true;
			}
		}
		
		return isGelijk;
	}
	
	@Override
	public String toString() {
		return "[" + this.getClass() + "\n"
				+ "\tvoornaam: " + this.voornaam + "\n"
				+ "\ttussenvoegsel: " + this.tussenvoegsel + "\n"
				+ "\tachternaam: " + this.achternaam + "\n"
				+ "]";
	}
}
