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
	private ArrayList<Les> lessen = new ArrayList<Les>();
	
	private ArrayList<Docent> deDocenten = new ArrayList<Docent>();
	private ArrayList<Student> deStudenten = new ArrayList<Student>();
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
	
	private Roosterblok blok_C = new Roosterblok('C', LocalDate.of(2016, 2, 2), LocalDate.of(2016, 4, 8));
	
	
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
		
		leesRooster("data/Rooster/rooster_c.csv", blok_C);
		
		leesStudentenIn("data/Klassen/SIE_V1A.txt", SIE_V1A);
		leesStudentenIn("data/Klassen/SIE_V1B.txt", SIE_V1B);
		leesStudentenIn("data/Klassen/SIE_V1C.txt", SIE_V1C);
		leesStudentenIn("data/Klassen/SIE_V1D.txt", SIE_V1D);
		leesStudentenIn("data/Klassen/SIE_V1E.txt", SIE_V1E);
		leesStudentenIn("data/Klassen/SIE_V1F.txt", SIE_V1F);
		
		System.out.println(this.deStudenten);		
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
			
			Vak vak = this.blok_C.zoekVak(vakCode);
			if (vak == null) {
				vak = new Vak(vakCode);
			}
			
			Klas klas = this.getKlas(klasCode);
			Les les = new Les(vak, startdatum, einddatum, lokaalCode);
			
			Docent docent = new Docent(docentNaam);
			docent.setWachtwoord("geheim");
			docent.voegVakToe(vak);
			deDocenten.add(docent);
			
			
		
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
						
						for(Vak v : this.blok_C.getVakken())	{
							s1.voegVakToe(v);
						}
						
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
	
	public Klas getKlas(String klasCode) {
		Klas klas = null;
		
		for (Klas k : this.klassen) {
			if (k.getKlasCode().equals(klasCode)) {
				klas = k;
				break;
			}
		}
		
		return klas;
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
