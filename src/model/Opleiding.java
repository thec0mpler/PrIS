package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Opleiding {
	private String naam;
	private ArrayList<Docent> deDocenten = new ArrayList<Docent>();;
	private ArrayList<Student> deStudenten = new ArrayList<Student>();;
	private ArrayList<Roosterblok> roosterBlokken = new ArrayList<Roosterblok>();
	private ArrayList<Klas> klassen = new ArrayList<Klas>();
	
	private String line = null;
	private BufferedReader br = null;
	private String[] ingelezenStudent;
	
	private Klas SIE_V1A = new Klas("SIE_V1A");
	private Klas SIE_V1B = new Klas("SIE_V1B");
	private Klas SIE_V1C = new Klas("SIE_V1C");
	private Klas SIE_V1D = new Klas("SIE_V1D");
	private Klas SIE_V1E = new Klas("SIE_V1E");
	private Klas SIE_V1F = new Klas("SIE_V1F");
	
	private int studentenNummer = 0;
	private String vNaam = null;
	private String tVoeg = null;
	private String aNaam = null;
	private int currentPlace = 0;
	
	private Roosterblok blok_C = new Roosterblok(LocalDate.of(2016, 2, 2), LocalDate.of(2016, 4, 8), 'C');
	
	
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
		
		roosterBlokken.add(blok_C);
		
		klassen.add(SIE_V1A);
		klassen.add(SIE_V1B);
		klassen.add(SIE_V1C);
		klassen.add(SIE_V1D);
		klassen.add(SIE_V1E);
		klassen.add(SIE_V1F);
		
		leesStudentenIn("data/Klassen/SIE_V1A.txt", SIE_V1A);
		leesStudentenIn("data/Klassen/SIE_V1B.txt", SIE_V1B);
		leesStudentenIn("data/Klassen/SIE_V1C.txt", SIE_V1C);
		leesStudentenIn("data/Klassen/SIE_V1D.txt", SIE_V1D);
		leesStudentenIn("data/Klassen/SIE_V1E.txt", SIE_V1E);
		leesStudentenIn("data/Klassen/SIE_V1F.txt", SIE_V1F);
		
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
	
	public void leesRooster(String path, Roosterblok blok) throws IOException	{
		FileReader fis = new FileReader(path);
		BufferedReader br = new BufferedReader(fis);

		String regel = br.readLine();
		while (regel != null) {
			String[] values = regel.split(",");

			String[] datum = values[0].split("-");
			int jaar = Integer.parseInt(datum[0]);
			int maand = Integer.parseInt(datum[1]);
			int dag = Integer.parseInt(datum[2]);

			String[] begintijd = values[1].split(":");
			int begintijdUur = Integer.parseInt(begintijd[0]);
			int begintijdMinuten = Integer.parseInt(begintijd[1]);

			String[] eindtijd = values[2].split(":");
			int eindtijdUur = Integer.parseInt(eindtijd[0]);
			int eindtijdMinuten = Integer.parseInt(eindtijd[1]);

			LocalDateTime startdatum = LocalDateTime.of(LocalDate.of(jaar, maand, dag),
					LocalTime.of(begintijdUur, begintijdMinuten));
			LocalDateTime einddatum = LocalDateTime.of(LocalDate.of(jaar, maand, dag),
					LocalTime.of(eindtijdUur, eindtijdMinuten));
			String vakCode = values[3];
			String docentNaam = values[4];
			String lokaalCode = values[5];
			String klasCode = values[6];
			
			String[] docentConstructor = docentNaam.split(",");
			
			Vak vak = new Vak(vakCode);
			Docent docent = new Docent(docentNaam);
			
			this.blok_C.voegVakToe(vak);

			regel = br.readLine();
		}
	}
	
	public void leesStudentenIn(String path, Klas klas)	{
		try {
			br = new BufferedReader(new FileReader(path), 16);
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
						klas.voegStudentToe(s1);
						deStudenten.add(s1);
					}
				}
				//br.close();
			}
		} catch(IOException ex)	{
			ex.printStackTrace();
		}
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
