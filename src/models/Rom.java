package models;

public class Rom {
	private String romNavn;
	private int romNr;
	private int maxAntall;

	public Rom(String romNavn, int romNr, int maxAntall) {

		if (!isValidRomNr(romNr)) {
			throw new IllegalArgumentException("Illegal romNr");
		}
		this.romNr = romNr;
		
		if (!isValidName(romNavn)) {
			throw new IllegalArgumentException("Illegal romNavn");
		}
		this.romNavn = romNavn;

		if (!isValidMaxAntall(maxAntall)) {
			throw new IllegalArgumentException("Max antall must be positive");
		}
		this.maxAntall = maxAntall;
	}

	private boolean isValidRomNr(int nr) {
		return nr > 0;
	}

	private boolean isValidMaxAntall(int nr) {
		return nr > 0;
	}

	private boolean isValidName(String name) {
		return (name != null && !name.equals(""));
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
