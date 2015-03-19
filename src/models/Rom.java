package models;

import utlils.Console;
import interfaces.Selectable;

public class Rom implements Selectable {
	private String name;
	private int id;
	private int kapasitet;
	
	public Rom(String romNavn, int romNr, int maxAntall) {

		if (!isValidRomNr(romNr)) {
			throw new IllegalArgumentException("Illegal romNr");
		}
		this.id = romNr;
		
		if (!isValidName(romNavn)) {
			throw new IllegalArgumentException("Illegal romNavn");
		}
		this.name = romNavn;

		if (!isValidMaxAntall(maxAntall)) {
			throw new IllegalArgumentException("Max antall must be positive");
		}
		this.kapasitet = maxAntall;
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
		return kapasitet;
	}

	public String getName() {
		return Console.matchLength(name, 25) + " | kapasitet: " + kapasitet;
	}

	public int getRomNr() {
		return id;
	}
	
	@Override
	public String toString() {
		return name + " " + id + " [" + kapasitet + "]";
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getSmallName() {
		return name + " [" + kapasitet + "]";
	}

}
