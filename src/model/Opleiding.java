package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Opleiding {
	private String naam;
	private ArrayList<Docent> deDocenten = new ArrayList<Docent>();;
	private ArrayList<Student> deStudenten = new ArrayList<Student>();;
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
	 * @throws IOException 
	 * 
	 */
	public Opleiding() throws IOException {
		
		String line = null;
		BufferedReader br = null;
		String[] ingelezenStudent;
		Klas SIE_V1A = new Klas("SIE_V1A");
		
		int studentenNummer = 0;
		String vNaam = null;
		String tVoeg = null;
		String aNaam = null;
		int currentPlace = 0;
		
		try {
			br = new BufferedReader(new FileReader("data/Klassen/SIE_V1A.txt"), 16);
			while((line=br.readLine())!=null)	{
			
				ingelezenStudent = line.split(",");
				for(String s : ingelezenStudent)	{
					currentPlace++;
					if(currentPlace == 1)	{
						studentenNummer = Integer.parseInt(s);
					}	else if	(currentPlace == 2)	{
						aNaam = s;
					}	else if	(currentPlace == 3)	{
						tVoeg = s;
					}	else if (currentPlace == 4)	{
						vNaam = s;
						currentPlace = 0;
						Student s1 = new Student(studentenNummer, vNaam, tVoeg, aNaam);
						s1.setWachtwoord("geheim");
						s1.maakGebruikersnaam();
						SIE_V1A.voegStudentToe(s1);
						deStudenten.add(s1);
					}
				}
			}
		} catch(IOException ex)	{
			ex.printStackTrace();
		} finally {
			br.close();
		}
		
		/*
		Docent d1 = new Docent("Wim", "de", "Groot");
		d1.setWachtwoord("geheim");
		d1.maakGebruikersnaam();
		
		Docent d2 = new Docent("Hans", "", "Anders");
		d2.setWachtwoord("geheim");
		d2.maakGebruikersnaam();
		
		Docent d3 = new Docent("Jan", "", "alleman");
		d3.setWachtwoord("geheim");
		d3.maakGebruikersnaam();
		
		deDocenten.add(d1);
		deDocenten.add(d2);
		deDocenten.add(d3);
		
		Vak v1 = new Vak("TCIF-V1AUI-15", "Analyse en User Interfaces");
		Vak v2 = new Vak("TICT-V1GP-15", "Group Project");
		Vak v3 = new Vak("TICT-V1OODC-15", "Object Oriented Design & Construction");
		
		LocalDateTime beginTijd = LocalDateTime.now();
		LocalDateTime eindTijd = LocalDateTime.now().plusHours(2);
		
		Les l1 = new Les(beginTijd, eindTijd);
		
		v1.voegLesToe(l1);
		v2.voegLesToe(l1);
		v3.voegLesToe(l1);
		
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
		s1.voegVakToe(v1);
		s1.voegVakToe(v2);
		s1.voegVakToe(v3);
		
		Student s2 = new Student(101, "Frans", "", "Bauer");
		s2.setWachtwoord("geheim");
		s2.maakGebruikersnaam();
		s2.voegVakToe(v1);
		s2.voegVakToe(v2);
		s2.voegVakToe(v3);
		
		Student s3 = new Student(102, "Daphne", "", "Deckers");
		s3.setWachtwoord("geheim");
		s3.maakGebruikersnaam();
		s3.voegVakToe(v1);
		s3.voegVakToe(v2);
		s3.voegVakToe(v3);
		
		Student s4 = new Student(103, "Jeroen", "", "Dijsselbloem");
		s4.setWachtwoord("geheim");
		s4.maakGebruikersnaam();
		s4.voegVakToe(v1);
		s4.voegVakToe(v2);
		s4.voegVakToe(v3);
		
		Student s5 = new Student(104, "Jelle", "", "Wiersma");
		s5.setWachtwoord("geheim");
		s5.maakGebruikersnaam();
		s5.voegVakToe(v1);
		s5.voegVakToe(v2);
		
		deStudenten.add(s1);
		deStudenten.add(s2);
		deStudenten.add(s3);
		deStudenten.add(s4);
		deStudenten.add(s5);
		
		Klas k1 = new Klas("SIE-V1X");
		klassen.add(k1);
		
		k1.voegStudentToe(s1);
		k1.voegStudentToe(s2);
		k1.voegStudentToe(s3);
		k1.voegStudentToe(s4);
		k1.voegStudentToe(s5);
		
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
	
	public ArrayList<Docent> getDeDocenten()	{
		return deDocenten;
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
	
	public void setNaam(String nm)	{
		naam = nm;
	}
	
	public Klas getKlasBijStudent(Student student)	{
		Klas klas = null;
		
		ArrayList<Student> studentenUitKlas;
		outerloop:
		for(Klas k : klassen)	{
			studentenUitKlas = k.getStudenten();
			for(Student s : studentenUitKlas)	{
				if(s.equals(student))	{
					klas = k;
					break outerloop;
				}
			}
		}
		return klas;
	}
	
	public String getNaam()	{
		return naam;
	}
	
	public void voegKlasToe(Klas nieuwKlas)	{
		if(!klassen.contains(nieuwKlas))	{
			klassen.add(nieuwKlas);
		}
	}
	
	public void verwijderKlas(Klas exKlas)	{
		if(klassen.contains(exKlas))	{
			klassen.remove(exKlas);
		}
	}
	
	public ArrayList<Klas> getKlassen()	{
		return klassen;
	}
	
	public void voegRoosterblokToe(Roosterblok rB)	{
		if(!roosterBlokken.contains(rB))	{
			roosterBlokken.add(rB);
		}
	}
	
	public void verwijderRoosterblok(Roosterblok rB)	{
		if(roosterBlokken.contains(rB))	{
			roosterBlokken.remove(rB);
		}
	}
	
	public ArrayList<Student> getStudentenVanKlas(String klasCode) {
		ArrayList<Student> resultaat = new ArrayList<Student>();
		
		for(Klas k : klassen)	{
			if(klasCode.equals(k.getKlasCode()))	{
				resultaat = k.getStudenten();
			}
		}
		
		return resultaat;
		
	}
}
