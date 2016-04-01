package model;

public class Presentie {
	private boolean aanwezig;
	private boolean afgemeld;
	private Les les;
	private Student student;

	public Presentie(Student student, Les les) {
		this.student = student;
		this.les = les;
	}
	
	public void setAanwezig(boolean status) {
		aanwezig = status;
	}

	public void setAfgemeld(boolean status) {
		afgemeld = status;
	}

	public boolean getAanwezig() {
		return aanwezig;
	}

	public boolean getAfgemeld() {
		return afgemeld;
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