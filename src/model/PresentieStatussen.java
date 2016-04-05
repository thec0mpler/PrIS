package model;

public enum PresentieStatussen {
	AANWEZIG(0), GEOORLOOFDAFWEZIG(1), AFWEZIG(2), ONBEKEND(999);
	
	private final int value;
	
	PresentieStatussen(int value) {
		this.value = value;
	}
	
	public int getInt() {
		return value;
	}
}
