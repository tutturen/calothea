package models;

import java.util.ArrayList;

public class Gruppe {
	
	Kalender gruppeKalender;
	ArrayList<User> gruppeMedlemmer;
	ArrayList<Gruppe> subgrupper;
	String gruppeNavn;
	
	public Gruppe(Kalender gruppeKalender, String gruppeNavn) {
		gruppeMedlemmer = new ArrayList<User>();
		subgrupper = new ArrayList<Gruppe>();
		this.gruppeKalender = gruppeKalender;
		this.gruppeNavn = gruppeNavn;
		
	}
	
	public ArrayList<User> getGruppeMedlemmer() {
		return gruppeMedlemmer;
	}
	
	public ArrayList<Gruppe> getSubgrupper() {
		return subgrupper;
	}

	public Kalender getGruppeKalender() {
		return gruppeKalender;
	}
	
	public String getGruppeNavn() {
		return gruppeNavn;
	}
	
	public Kalender getKalender() {
		
	}
	
	public ArrayList getMedlemmer() {
		
	}
	
	public void addMedlem(User nyttMedlem) {
		
	}
	
	public void removeMedlem(User fjerneMedlem) {
		
	}
	
	public void addKalender(int kalenderID) {
		
	}
	
	public void removeKalender(int kalenderID) {
		
	}

}
