package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Les {
	private Roosterblok roosterblok;
	private Vak vak;
	private Klas klas;
	private Docent docent;
	private LocalDateTime begintijd;
	private LocalDateTime eindtijd;
	private String lokaal;
	private ArrayList<Presentie> presenties;

	public Les(Vak vak, Klas klas, LocalDateTime begintijd, LocalDateTime eindtijd, String lokaal) {
		this.vak = vak;
		this.klas = klas;
		this.begintijd = begintijd;
		this.eindtijd = eindtijd;
		this.lokaal = lokaal;
		this.presenties = new ArrayList<Presentie>();
	}
	
	public Roosterblok getRoosterblok() {
		return this.roosterblok;
	}
	
	public Vak getVak() {
		return this.vak;
	}
	
	public Klas getKlas() {
		return this.klas;
	}
	
	public Docent getDocent() {
		return this.docent;
	}

	public LocalDateTime getBegintijd() {
		return this.begintijd;
	}
	
	public LocalDateTime getEindtijd() {
		return this.eindtijd;
	}
	
	public String getLokaal() {
		return this.lokaal;
	}
	
	public ArrayList<Presentie> getPresenties() {
		return this.presenties;
	}
	
	public Presentie getPresentieVanStudent(Student student) {		
		for (Presentie presentie : this.presenties) {
			if (presentie.getStudent().equals(student)) {
				return presentie;
			}
		}
		
		Presentie presentie = new Presentie(student, this);
		this.voegPresentieToe(presentie);
		
		return presentie;
	}
	
	public void wijzigPresentieAanwezig(Student student, PresentieStatussen status) {
		Presentie presentie = this.getPresentieVanStudent(student);
		
		if (presentie == null) {
			presentie = new Presentie(student, this);
			this.voegPresentieToe(presentie);
		}
		
		presentie.setAanwezig(status);
	}
	
	public void wijzigPresentieAfmelden(Student student, boolean status, String reden) {
		Presentie presentie = this.getPresentieVanStudent(student);
		
		if (presentie == null) {
			presentie = new Presentie(student, this);
			this.voegPresentieToe(presentie);
		}
		
		presentie.setAfgemeld(status, reden);
	}
	
	public void voegPresentieToe(Presentie presentie) {
		if (!this.presenties.contains(presentie)) {			
			this.presenties.add(presentie);
		}
	}
	
	public void setRoosterblok(Roosterblok roosterblok) {
		this.roosterblok = roosterblok;
	}
	
	public void setVak(Vak vak) {
		this.vak = vak;
	}
	
	public void setKlas(Klas klas) {
		this.klas = klas;
	}
	
	public void setDocent(Docent docent) {
		this.docent = docent;
	}
	
	public void setBegintijd(LocalDateTime begintijd) {
		this.begintijd = begintijd;
	}

	public void setEindtijd(LocalDateTime eindtijd) {
		this.eindtijd = eindtijd;
	}
	
	public void setLokaal(String lokaal) {
		this.lokaal = lokaal;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean isGelijk = false;

		if (obj instanceof Les) {
			Les andereLes = (Les) obj;

			if (this.roosterblok.equals(andereLes.roosterblok)
					&& this.vak.equals(andereLes.vak)
					&& this.begintijd.equals(andereLes.begintijd)
					&& this.eindtijd.equals(andereLes.eindtijd)
					&& this.lokaal.equals(andereLes.lokaal)) {
				isGelijk = true;
			}
		}

		return isGelijk;
	}

	@Override
	public String toString() {
		return "[" + this.getClass() + ":\n"
				+ "\tvak: " + this.vak + "\n"
				+ "\tbegintijd: " + this.begintijd + "\n"
				+ "\teindtijd: " + this.eindtijd + "\n"
				+ "\tlokaal: " + this.lokaal + "\n"
				+ "]";
	}
}
