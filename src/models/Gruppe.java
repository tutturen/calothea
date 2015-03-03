package models;

import java.util.ArrayList;

public class Gruppe {

	Kalender kalender;
	ArrayList<User> gruppeMedlemmer;
	ArrayList<Gruppe> subGrupper; // ikke i klassediagrammet i exercise 5
	String gruppeNavn;

	public Gruppe(Kalender kalender, String gruppeNavn) {
		gruppeMedlemmer = new ArrayList<User>();
		subGrupper = new ArrayList<Gruppe>();
		this.kalender = kalender;
		if (!isValidName(gruppeNavn)) {
			throw new IllegalArgumentException("Illegal gruppeNavn");
		}
		this.gruppeNavn = gruppeNavn;

	}

	private boolean isValidName(String name) {
		return (name != null && !name.equals(""));
	}

	public ArrayList<User> getGruppeMedlemmer() { // Denne metoden skal også være i GruppeController som getMedlemmer(), skal den også være her da?
		return gruppeMedlemmer;
	}

	public ArrayList<Gruppe> getSubgrupper() {
		return subGrupper;
	}

	public Kalender getKalender() { 
		return kalender;
	}

	public String getGruppeNavn() { 
		return gruppeNavn;
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
		this.kalender = kalender;
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
