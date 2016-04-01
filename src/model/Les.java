package model;

import java.time.LocalDateTime;

public class Les {
	private Roosterblok roosterblok;
	private Vak vak;
	private LocalDateTime begintijd;
	private LocalDateTime eindtijd;
	private String lokaal;

	public Les(Vak vak, LocalDateTime begintijd, LocalDateTime eindtijd, String lokaal) {
		this.vak = vak;
		this.begintijd = begintijd;
		this.eindtijd = eindtijd;
		this.lokaal = lokaal;
	}
	
	public Roosterblok getRoosterblok() {
		return this.roosterblok;
	}
	
	public Vak getVak() {
		return this.vak;
	}

	public LocalDateTime getBeginTijd() {
		return begintijd;
	}
	
	public LocalDateTime getEindTijd() {
		return eindtijd;
	}
	
	public String getLokaal() {
		return this.lokaal;
	}
	
	public void setRoosterblok(Roosterblok roosterblok) {
		this.roosterblok = roosterblok;
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

			if (this.begintijd == andereLes.begintijd && this.eindtijd == andereLes.eindtijd) {
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
