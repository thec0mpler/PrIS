package model;

import java.time.LocalDate;

public class Roosterblok {
	private char periode;
	private LocalDate begindatum;
	private LocalDate einddatum;

	public Roosterblok(char periode, LocalDate begindatum, LocalDate einddatum) {
		this.periode = periode;
		this.begindatum = begindatum;
		this.einddatum = einddatum;
	}

	public char getPeriode() {
		return this.periode;
	}

	public LocalDate getBegindatum() {
		return this.begindatum;
	}

	public LocalDate getEinddatum() {
		return this.einddatum;
	}

	public void setPeriode(char periode) {
		this.periode = periode;
	}

	public void setBegindatum(LocalDate begindatum) {
		this.begindatum = begindatum;
	}

	public void setEinddatum(LocalDate einddatum) {
		this.einddatum = einddatum;
	}

	@Override
	public boolean equals(Object obj) {
		boolean isGelijk = false;

		if (obj instanceof Roosterblok) {
			Roosterblok anderRoosterblok = (Roosterblok) obj;

			if (this.periode == anderRoosterblok.periode && this.begindatum.equals(anderRoosterblok.begindatum)
					&& this.einddatum.equals(anderRoosterblok.einddatum)) {
				isGelijk = true;
			}
		}

		return isGelijk;
	}

	@Override
	public String toString() {
		return "[" + this.getClass() + "\n" + "\tperiode: " + this.periode + "\n" + "\tbegindatum: " + this.begindatum
				+ "\n" + "\teinddatum: " + this.einddatum + "\n" + "]";
	}
}
