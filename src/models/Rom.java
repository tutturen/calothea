package models;

public class Rom {
	String romNavn;
	int romNr;
	int maxAntall;
	
	public Rom(String romNavn, int romNr, int maxAntall) {
		this.romNavn = romNavn;
		this.romNr = romNr;
		this.maxAntall = maxAntall;
	}
	
	public int getAntall() {
		return maxAntall;
	}
	
	public String getRomnavn() {
		return romNavn;
	}
	
	public int getRomNr() {
		return romNr;
	}
	
	
}
