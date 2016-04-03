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

	public boolean getAanwezig() {
		return aanwezig;
	}

	public boolean getAfgemeld() {
		return afgemeld;
	}
	
	public Les getLes() {
		return this.les;
	}
	
	public Student getStudent() {
		return this.student;
	}
	
	public void setAanwezig(boolean status) {
		aanwezig = status;
	}

	public void setAfgemeld(boolean status) {
		afgemeld = status;
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