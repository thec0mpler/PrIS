package model;

public class Vak {
	private String code;
	
	public Vak(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean isGelijk = false;
		
		if (obj instanceof Vak) {
			Vak anderVak = (Vak) obj;
			
			if (this.code.equals(anderVak.code)) {
				isGelijk = true;
			}
		}
		
		return isGelijk;
	}
	
	@Override
	public String toString() {
		return "[" + this.getClass() + "\n"
				+ "\tcode: " + this.code + "\n"
				+ "]";
	}
}
