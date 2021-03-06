package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;

public class Opleiding {
	private String naam;
	private ArrayList<User> users;
	private ArrayList<Les> lessen;
	
	public Opleiding(String naam) {
		this.naam = naam;
		this.users = new ArrayList<User>();
		this.lessen = new ArrayList<Les>();
		
		// Import
		try {
			this.leesStudentenIn("data/klassen/SIE_V1A.txt", "SIE_V1A");
			this.leesStudentenIn("data/klassen/SIE_V1B.txt", "SIE_V1B");
			this.leesStudentenIn("data/klassen/SIE_V1C.txt", "SIE_V1C");
			this.leesStudentenIn("data/klassen/SIE_V1D.txt", "SIE_V1D");
			this.leesStudentenIn("data/klassen/SIE_V1E.txt", "SIE_V1E");
			this.leesStudentenIn("data/klassen/SIE_V1F.txt", "SIE_V1F");
			
			this.leesRoosterIn("data/rooster/rooster_c.csv", 'C');
			
			this.leesPresentiesIn("data/presenties/presenties.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getNaam()	{
		return this.naam;
	}
	
	public ArrayList<User> getUsers() {
		return this.users;
	}
	
	public ArrayList<Student> getStudenten() {
		ArrayList<Student> studenten = new ArrayList<Student>();
		
		for (User user : this.users) {
			if (user instanceof Student) {
				studenten.add((Student) user);
			}
		}
		
		return studenten;
	}
	
	public ArrayList<Les> getLessen() {
		return this.lessen;
	}
	
	public User getUserMetGebruikersnaam(String gebruikersnaam) {
		User resultaat = null;
		
		for (User user : this.users) {
			if (user.getGebruikersNaam().equals(gebruikersnaam)) {
				resultaat = user;
				break;
			}
		}
		
		return resultaat;
	}
	
	public Student getStudentMetGebruikersnaam(String gebruikersnaam) {
		User user = this.getUserMetGebruikersnaam(gebruikersnaam);
		
		if (user instanceof Student) {
			return (Student) user;
		}
		
		return null;
	}
	
	public Docent getDocentMetGebruikersnaam(String gebruikersnaam) {
		User user = this.getUserMetGebruikersnaam(gebruikersnaam);
		
		if (user instanceof Docent) {
			return (Docent) user;
		}
		
		return null;
	}
	
	public Les getLes(LocalDateTime begintijd, String lokaal) {
		for (Les les : this.lessen) {
			if (les.getBegintijd().equals(begintijd)
					&& les.getLokaal().equals(lokaal)) {
				return les;
			}
		}
		
		return null;
	}
	
	public ArrayList<Les> getLessenVanStudent(Student student) {
		ArrayList<Les> lessen = new ArrayList<Les>();
		Klas klas = student.getKlas();
		
		for (Les les : this.lessen) {
			if (les.getKlas().equals(klas)) {
				lessen.add(les);
			}
		}
		
		return lessen;
	}
	
	public ArrayList<Les> getLessenVanDocent(Docent docent) {
		ArrayList<Les> lessen = new ArrayList<Les>();
		
		for (Les les : this.lessen) {
			if (les.getDocent().equals(docent)) {
				lessen.add(les);
			}
		}
		
		return lessen;
	}
	
	public ArrayList<Student> getStudentenVanLes(Les les) {
		ArrayList<Student> studenten = this.getStudentenVanKlas(les.getKlas());
		
		return studenten;
	}
	
	public ArrayList<Student> getStudentenVanKlas(Klas klas) {
		ArrayList<Student> studenten = new ArrayList<Student>();
		
		for (User user : this.users) {
			if (user instanceof Student) {
				Student student = (Student) user;
				
				if (student.getKlas().equals(klas)) {
					studenten.add(student);
				}
			}
		}
		
		return studenten;
	}
	
	public ArrayList<Klas> getKlassen() {
		ArrayList<Klas> klassen = new ArrayList<Klas>();
		
		for(Student student : this.getStudenten()) {
			if (!klassen.contains(student.getKlas())) {
				klassen.add(student.getKlas());
			}
		}
		
		return klassen;
	}
	
	public Klas getKlas(String klasCode) {
		Klas klas = null;
		
		for (Klas k : this.getKlassen()) {
			if (k.getCode().equals(klasCode)) {
				klas = k;
			}
		}
		
		return klas;
	}
	
	public ArrayList<Vak> getVakken() {
		ArrayList<Vak> vakken = new ArrayList<Vak>();
		
		for(Les les : this.lessen) {
			if (!vakken.contains(les.getVak())) {
				vakken.add(les.getVak());
			}
		}
		
		return vakken;
	}
	
	public Vak getVak(String vakCode) {
		Vak vak = null;
		
		for (Vak v : this.getVakken()) {
			if (v.getCode().equals(vakCode)) {
				vak = v;
			}
		}
		
		return vak;
	}
	
	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	public void voegUserToe(User user) {
		if (!this.users.contains(user)) {
			this.users.add(user);
		}
	}
	
	public void voegLesToe(Les les) {
		if (!this.lessen.contains(les)) {
			this.lessen.add(les);
		}
	}
	
	public String login(String gebruikersnaam, String wachtwoord) {
		for (User user : this.users) {
			if (user.getGebruikersNaam().equals(gebruikersnaam)) {
				if (user.controleerWachtwoord(wachtwoord)) {
					if (user instanceof Student) {
						return "student";
					} else if (user instanceof Docent) {
						return "docent";
					}
				}
			}
		}
		
		return "undefined";
	}
	
	@Override
	public String toString() {
		return "[" + this.getClass() + "\n"
				+ "\tnaam: " + this.naam + "\n"
				+ "]";
	}
	
	public void presentiesOpslaan() {		
		JsonObjectBuilder json = Json.createObjectBuilder();
		JsonArrayBuilder presenties = Json.createArrayBuilder();
		
		for (Les les : this.lessen) {
			for (Presentie presentie : les.getPresenties()) {
				presenties.add(presentie.toJson());
			}
		}
		
		json.add("presenties", presenties);
		
		// Save file
		try {
			PrintWriter out = new PrintWriter("data/presenties/presenties.txt");
			
			out.print(json.build());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public void leesStudentenIn(String path, String klasnaam) throws IOException {
		Klas klas = new Klas(klasnaam);
		
		FileReader fis = new FileReader(path);
		BufferedReader br = new BufferedReader(fis);
		
		String regel = br.readLine();
		while (regel != null) {
			String[] values = regel.split(",");
			
			int nummer = Integer.parseInt(values[0]);
			String voornaam = values[3];
			String tussenvoegsel = values[2];
			String achternaam = values[1];
			
			Student student = new Student(nummer, voornaam, tussenvoegsel, achternaam);
			student.setKlas(klas);
			
			this.voegUserToe(student);
			
			regel = br.readLine();
		}
		
		br.close();
	}
	
	public void leesRoosterIn(String path, char roosterblokNaam) throws IOException	{
		Roosterblok roosterblok = new Roosterblok('C', LocalDate.of(2016, 2, 2), LocalDate.of(2016, 4, 8));
		
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
			
			Klas klas = null;
			Klas gevondenKlas = this.getKlas(klasCode);
			if (gevondenKlas == null) {
				klas = new Klas(klasCode);
			} else {
				klas = gevondenKlas;
			}
			
			Vak vak = null;
			Vak gevondenVak = this.getVak(vakCode);
			if (gevondenVak == null) {
				vak = new Vak(vakCode);
			} else {
				vak = gevondenVak;
			}
			
			Docent docent = new Docent(docentNaam);
			
			Les les = new Les(vak, klas, startdatum, einddatum, lokaalCode);
			les.setRoosterblok(roosterblok);
			les.setDocent(docent);
			
			this.voegLesToe(les);
			docent.voegVakToe(vak);
			
			this.voegUserToe(docent);
		
			regel = br.readLine();
		}
		
		br.close();
	}
	
	public void leesPresentiesIn(String path) throws IOException {
		FileReader fis = new FileReader(path);
		JsonReader jsonReader = Json.createReader(fis);
		
		try {
			JsonObject jsonObject = jsonReader.readObject();
			JsonArray jsonPresenties = jsonObject.getJsonArray("presenties");
			
			for (JsonValue jsonPresentieValue : jsonPresenties) {
				JsonObject jsonPresentieObject = (JsonObject) jsonPresentieValue;
				
				String gebruikersnaam = jsonPresentieObject.getJsonObject("student").getString("gebruikersnaam");
				
				LocalDateTime begintijd = LocalDateTime.parse(jsonPresentieObject.getJsonObject("les").getString("begintijd"));
				String lokaal = jsonPresentieObject.getJsonObject("les").getString("lokaal");
				
				PresentieStatussen aanwezig = PresentieStatussen.valueOf(jsonPresentieObject.getString("aanwezig"));
				boolean afgemeld = jsonPresentieObject.getBoolean("afgemeld");
				String afgemeldReden = jsonPresentieObject.getString("afgemeldReden");
				
				Student student = this.getStudentMetGebruikersnaam(gebruikersnaam);
				Les les = this.getLes(begintijd, lokaal);
				
				Presentie presentie = new Presentie(student, les);
				presentie.setAanwezig(aanwezig);
				presentie.setAfgemeld(afgemeld, afgemeldReden);
				
				les.voegPresentieToe(presentie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
