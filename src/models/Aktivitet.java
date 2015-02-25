package models;

import java.util.ArrayList;
import java.util.Date;

public class Aktivitet {

	ArrayList<User> brukereInvitert;
	ArrayList<User> deltagere;
	Date startDate, endDate;
	Rom rom;
	User eier;
	
	public Aktivitet(User eier, Date startDate, Date endDate, Rom rom) {
		this.eier = eier;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rom = rom;
		deltagere.add(eier);
	}

	public ArrayList<User> getBrukereInvitert() {
		return brukereInvitert;
	}

	public ArrayList<User> getDeltagere() {
		return deltagere;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Rom getRom() {
		return rom;
	}

	public User getEier() {
		return eier;
	}
	
	public void addToInvitedList(User user) {
		if (!brukereInvitert.contains(user)) {
			brukereInvitert.add(user);
		}
		throw new IllegalStateException("User already invited");
	}
	
	public void acceptInvitation(User user) {
		if (deltagere.contains(user)){
			throw new IllegalStateException("User has already accepted");
		}
		if (!brukereInvitert.contains(user)){
			throw new IllegalStateException("User not invited");
		}
		brukereInvitert.remove(user);
		deltagere.add(user);
	}
	
	
}
