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
		
		Vak v1 = new Vak("TCIF-V1AUI-15", "Analyse en User Interfaces");
		Vak v2 = new Vak("TICT-V1GP-15", "Group Project");
		Vak v3 = new Vak("TICT-V1OODC-15", "Object Oriented Design & Construction");
		
		d1.voegVakToe(v1);
		d1.voegVakToe(v2);
		d1.voegVakToe(v3);
		
		d2.voegVakToe(v1);
		d2.voegVakToe(v2);
		d2.voegVakToe(v3);

		d3.voegVakToe(v1);
		d3.voegVakToe(v2);
		d3.voegVakToe(v3);
	
		
		Student s1 = new Student(100, "Roel", "van", "Velzen");
		s1.setWachtwoord("geheim");
		s1.maakGebruikersnaam();
		s1.voegLesToe(v1);
		s1.voegLesToe(v2);
		s1.voegLesToe(v3);
		
		Student s2 = new Student(101, "Frans", "", "Bauer");
		s2.setWachtwoord("geheim");
		s2.maakGebruikersnaam();
		s2.voegLesToe(v1);
		s2.voegLesToe(v2);
		s2.voegLesToe(v3);
		
		Student s3 = new Student(102, "Daphne", "", "Dekkers");
		s3.setWachtwoord("geheim");
		s3.maakGebruikersnaam();
		s3.voegLesToe(v1);
		s3.voegLesToe(v2);
		s3.voegLesToe(v3);
		
		Student s4 = new Student(103, "Jeroen", "", "Dijsselbloem");
		s4.setWachtwoord("geheim");
		s4.maakGebruikersnaam();
		s4.voegLesToe(v1);
		s4.voegLesToe(v2);
		s4.voegLesToe(v3);
		
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
