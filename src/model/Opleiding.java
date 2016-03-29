package model;

import java.util.ArrayList;

public class Opleiding {
	private ArrayList<Docent> deDocenten;
	private ArrayList<Student> deStudenten;
	private ArrayList<Roosterblok> roosterBlokken = new ArrayList<Roosterblok>();
	private ArrayList<Klas> klassen = new ArrayList<Klas>();
	
	/**
	 * De constructor maakt een set met standaard-data aan. Deze data
	 * moet nog vervangen worden door gegevens die uit een bestand worden
	 * ingelezen, maar dat is geen onderdeel van deze demo-applicatie!
	 * 
	 * De klasse PrIS (PresentieInformatieSysteem) heeft nu een meervoudige
	 * associatie met de klassen Docent en Student. Uiteraard kan dit nog veel
	 * verder uitgebreid en aangepast worden! 
	 * 
	 * De klasse fungeert min of meer als ingangspunt voor het domeinmodel. Op
	 * dit moment zijn de volgende methoden aanroepbaar:
	 * 
	 * String login(String gebruikersnaam, String wachtwoord)
	 * Docent getDocent(String gebruikersnaam)
	 * Student getStudent(String gebruikersnaam)
	 * ArrayList<Student> getStudentenVanKlas(String klasCode)
	 * 
	 * Methode login geeft de rol van de gebruiker die probeert in te loggen,
	 * dat kan 'student', 'docent' of 'undefined' zijn! Die informatie kan gebruikt 
	 * worden om in de Polymer-GUI te bepalen wat het volgende scherm is dat getoond 
	 * moet worden.
	 * 
	 */
	public Opleiding() {
		
		deDocenten = new ArrayList<Docent>();
		deStudenten = new ArrayList<Student>();
		
		Docent d1 = new Docent("Wim", "de", "Groot");
		d1.setWachtwoord("geheim");
		d1.maakGebruikersnaam();
		
		Docent d2 = new Docent("Hans", "", "Anders");
		d2.setWachtwoord("geheim");
		d2.maakGebruikersnaam();
		
		Docent d3 = new Docent("Jan", "", "alleman");
		d3.setWachtwoord("geheim");
		d3.maakGebruikersnaam();
		
		System.out.println("Naam van docent: " + d1.getGebruikersNaam());
		System.out.println("Naam van docent: " + d2.getGebruikersNaam());
		System.out.println("Naam van docent: " + d3.getGebruikersNaam());
		
		deDocenten.add(d1);
		deDocenten.add(d2);
		deDocenten.add(d3);
		
		
		d1.voegVakToe(new Vak("TCIF-V1AUI-15", "Analyse en User Interfaces"));
		d1.voegVakToe(new Vak("TICT-V1GP-15", "Group Project"));
		d1.voegVakToe(new Vak("TICT-V1OODC-15", "Object Oriented Design & Construction"));
		
		d2.voegVakToe(new Vak("TCIF-V1AUI-15", "Analyse en User Interfaces"));
		d2.voegVakToe(new Vak("TICT-V1GP-15", "Group Project"));
		d2.voegVakToe(new Vak("TICT-V1OODC-15", "Object Oriented Design & Construction"));
		
		d3.voegVakToe(new Vak("TCIF-V1AUI-15", "Analyse en User Interfaces"));
		d3.voegVakToe(new Vak("TICT-V1GP-15", "Group Project"));
		d3.voegVakToe(new Vak("TICT-V1OODC-15", "Object Oriented Design & Construction"));
		
		Student s1 = new Student(100, "Roel", "van", "Velzen");
		s1.setWachtwoord("geheim");
		s1.maakGebruikersnaam();
		
		Student s2 = new Student(101, "Frans", "", "Bauer");
		s2.setWachtwoord("geheim");
		s2.maakGebruikersnaam();
		
		Student s3 = new Student(102, "Daphne", "", "Dekkers");
		s3.setWachtwoord("geheim");
		s3.maakGebruikersnaam();
		
		Student s4 = new Student(103, "Jeroen", "", "Dijsselbloem");
		s4.setWachtwoord("geheim");
		s4.maakGebruikersnaam();
		
		deStudenten.add(s1);
		deStudenten.add(s2);
		deStudenten.add(s3);
		deStudenten.add(s4);
		
		Klas k1 = new Klas("SIE-V1X");
		klassen.add(k1);
		
		k1.voegStudentToe(s1);
		k1.voegStudentToe(s2);
		k1.voegStudentToe(s3);
		k1.voegStudentToe(s4);
		
		/*
		
		s1.setMijnKlas(k1);
		s2.setMijnKlas(k1);
		s3.setMijnKlas(k1);
		s4.setMijnKlas(k1);
		
		deStudenten.add(s1);
		deStudenten.add(s2);
		deStudenten.add(s3);
		deStudenten.add(s4);
		*/
	}
	
	public String login(String gebruikersnaam, String wachtwoord) {
		for (Docent d : deDocenten) {
			if (d.getGebruikersNaam().equals(gebruikersnaam)) {
				if (d.controleerWachtwoord(wachtwoord)) {
					return "docent";
				}
			}
		}
		
		for (Student s : deStudenten) {
			if (s.getGebruikersNaam().equals(gebruikersnaam)) {
				if (s.controleerWachtwoord(wachtwoord)) {
					return "student";
				}
			}
		}
		
		return "undefined";
	}
	
	public Docent getDocent(String gebruikersnaam) {
		Docent resultaat = null;
		
		for (Docent d : deDocenten) {
			if (d.getGebruikersNaam().equals(gebruikersnaam)) {
				resultaat = d;
				break;
			}
		}
		
		return resultaat;
	}
	
	public Student getStudent(String gebruikersnaam) {
		Student resultaat = null;
		
		for (Student s : deStudenten) {
			if (s.getGebruikersNaam().equals(gebruikersnaam)) {
				resultaat = s;
				break;
			}
		}
		
		return resultaat;
	}
	
	public ArrayList<Klas> getKlassen()	{
		return klassen;
	}
	
	public ArrayList<Student> getStudentenVanKlas(String klasCode) {
		ArrayList<Student> resultaat = new ArrayList<Student>();
		
		for(Klas k : klassen)	{
			if(klasCode.equals(k.getKlasCode()))	{
				resultaat = k.getStudenten();
			}
		}
		
		return resultaat;
		
		/*
		for (Student s : deStudenten) {
			if (s.getMijnKlas().getKlasCode().equals(klasCode)) {
				resultaat.add(s);
			}
		}
		
		return resultaat;
		*/
	}
}
