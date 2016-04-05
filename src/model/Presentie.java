package model;

public class Presentie {
	private PresentieStatussen aanwezig = PresentieStatussen.ONBEKEND;
	private boolean afgemeld;
	private String afgemeldReden = "";
	private Les les;
	private Student student;
	
	public Presentie(Student student, Les les) {
		this.student = student;
		this.les = les;
	}
	
	public PresentieStatussen getAanwezig() {
		return this.aanwezig;
	}
	
	public String getAanwezigString() {
		String s = "";
		
		switch (this.aanwezig) {
		case ONBEKEND:
			s = "Onbekend"; break;
		case AANWEZIG:
			s = "Aanwezig"; break;
		case GEOORLOOFDAFWEZIG:
			s = "Geoorloofd afwezig"; break;
		case AFWEZIG:
			s = "Afwezig"; break;
		}
		
		return s;
	}
	
	public int getAanwezigInt() {
		return this.aanwezig.getInt();
	}
	
	public boolean getAfgemeld() {
		return this.afgemeld;
	}
	
	public String getAfgemeldString() {
		if (this.afgemeld) {
			return "Afgemeld";
		}
		
		return "Niet afgemeld";
	}
	
	public String getAfgemeldReden() {
		return this.afgemeldReden;
	}
	
	public Les getLes() {
		return this.les;
	}
	
	public Student getStudent() {
		return this.student;
	}
	
	public void setAanwezig(PresentieStatussen status) {
		this.aanwezig = status;
	}
	
	public void setAfgemeld(boolean status, String reden) {
		this.afgemeld = status;
		this.afgemeldReden = reden;
	}
	
	public void setAfgemeldReden(String reden) {
		this.afgemeldReden = reden;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean isGelijk = false;
		
		if (obj instanceof Presentie) {
			Presentie anderePresentie = (Presentie) obj;
			
			if (this.les.equals(anderePresentie.les)
					&& this.student.equals(anderePresentie.student)) {
				isGelijk = true;
			}
		}
		
		return isGelijk;
	}
	
	@Override
	public String toString() {
		return "[" + this.getClass() + "\n"
				+ "\taanwezig: " + this.aanwezig + "\n"
				+ "\tafgemeld: " + this.afgemeld + "\n"
				+ "\tles: " + this.les + "\n"
				+ "\tstudent" + this.student + "\n"
				+ "]";
	}
}
