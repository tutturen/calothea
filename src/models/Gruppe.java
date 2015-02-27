package models;

import java.util.ArrayList;

public class Gruppe {

	Kalender gruppeKalender;
	ArrayList<User> gruppeMedlemmer;
	ArrayList<Gruppe> subGrupper; // ikke i klassediagrammet i exercise 5
	String gruppeNavn;

	public Gruppe(Kalender gruppeKalender, String gruppeNavn) {
		gruppeMedlemmer = new ArrayList<User>();
		subGrupper = new ArrayList<Gruppe>();
		this.gruppeKalender = gruppeKalender;
		if (!isValidName(gruppeNavn)) {
			throw new IllegalArgumentException("Illegal gruppeNavn");
		}
		this.gruppeNavn = gruppeNavn;

	}

	private boolean isValidName(String name) {
		return (name != null && !name.equals(""));
	}

	public ArrayList<User> getGruppeMedlemmer() {
		return gruppeMedlemmer;
	}

	public ArrayList<Gruppe> getSubgrupper() {
		return subGrupper;
	}

	public Kalender getGruppeKalender() { 
		return gruppeKalender;
	}

	public String getGruppeNavn() { 
		return gruppeNavn;
	}

	public Kalender getKalender() {
		return gruppeKalender;

	}

	public ArrayList<User> getMedlemmer() {
		return gruppeMedlemmer;

	}

	public void addMedlem(User user) {
		if (gruppeMedlemmer.contains(user)) {
			throw new IllegalStateException("User already in group");
		}
		gruppeMedlemmer.add(user);
	}

	public void removeMedlem(User user) {
		if (!gruppeMedlemmer.contains(user)) {
			throw new IllegalStateException("User not in group");
		}
		gruppeMedlemmer.remove(user);
	}

	public void setKalender(Kalender kalender) { // byttet ut addKalender og removeKalender med en setKalender da du uansett bare kan ha en
		gruppeKalender = kalender;
	}

	public void setGruppeNavn(String gruppeNavn) { 
		if (!isValidName(gruppeNavn)) {
			throw new IllegalArgumentException("Illegal gruppeNavn");
		}
		this.gruppeNavn = gruppeNavn;
	}

	public void addSubGruppe(Gruppe gruppe) { // metode ikke i klassediagram
		if (subGrupper.contains(gruppe)) {
			throw new IllegalStateException("Group already a subgroup");
		}
		subGrupper.add(gruppe);
	}

	public void removeSubGruppe(Gruppe gruppe) { // metode ikke i klassediagram
		if (!subGrupper.contains(gruppe)) {
			throw new IllegalStateException("Group not a subgroup");
		}
		subGrupper.remove(gruppe);
	}
	
}
