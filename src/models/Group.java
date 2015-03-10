package models;

import interfaces.Selectable;

import java.util.ArrayList;

public class Group implements Selectable {

	Kalender calendar;
	ArrayList<User> members;
	ArrayList<Group> subGroups; // ikke i klassediagrammet i exercise 5
	Group master_group;
	String name;
	int id;
	
	

	public Group(Kalender kalender, String gruppeNavn) {
		members = new ArrayList<User>();
		subGroups = new ArrayList<Group>();
		this.calendar = kalender;
		if (!isValidName(gruppeNavn)) {
			throw new IllegalArgumentException("Illegal gruppeNavn");
		}
		this.name = gruppeNavn;
	}
	
	public int getId() {
		return id;
	}

	private boolean isValidName(String name) {
		return (name != null && !name.equals(""));
	}

	public ArrayList<User> getMembers() { // Denne metoden skal ogs�� v��re i GruppeController som getMedlemmer(), skal den ogs�� v��re her da?
		return members;
	}

	public ArrayList<Group> getSubgrupper() {
		return subGroups;
	}
	
	public Group getMasterGruppe() {
		return master_group;
	}

	public Kalender getKalender() { 
		return calendar;
	}

	public String getName() { 
		return name;
	}
	
	public void setMasterGruppe(Group gruppe) {
		this.master_group = gruppe;
	}

	public void addMedlem(User user) {
		if (members.contains(user)) {
			throw new IllegalStateException("User already in group");
		}
		members.add(user);
	}

	public void removeMedlem(User user) {
		if (!members.contains(user)) {
			throw new IllegalStateException("User not in group");
		}
		members.remove(user);
	}

	public void setKalender(Kalender kalender) { 
		this.calendar = kalender;
	}

	public void setGruppeNavn(String gruppeNavn) { 
		if (!isValidName(gruppeNavn)) {
			throw new IllegalArgumentException("Illegal gruppeNavn");
		}
		this.name = gruppeNavn;
	}

	public void addSubGruppe(Group gruppe) { // metode ikke i klassediagram
		if (subGroups.contains(gruppe)) {
			throw new IllegalStateException("Group already a subgroup");
		}
		subGroups.add(gruppe);
	}

	public void removeSubGruppe(Group gruppe) { // metode ikke i klassediagram
		if (!subGroups.contains(gruppe)) {
			throw new IllegalStateException("Group not a subgroup");
		}
		subGroups.remove(gruppe);
	}
	
}
