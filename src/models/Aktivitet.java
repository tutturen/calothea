package models;

import java.util.ArrayList;
import java.util.Date;

import requests.ReqClient;
import requests.ReqService;

public class Aktivitet {

	int id;
	ArrayList<User> brukereInvitert;
	ArrayList<User> deltagere;
	Date startDate, endDate;
	Rom rom;
	User eier;
	String name;
	
	ReqService db = ReqClient.getInstance().getService();

	public Aktivitet(User eier, String name, Date startDate, Date endDate) {
		brukereInvitert = new ArrayList<User>();
		deltagere = new ArrayList<User>();
		if (!isValidEier(eier)) {
			throw new IllegalArgumentException("User invalid");
		}
		this.eier = eier;
		if (!isValidDates(startDate, endDate)){
			throw new IllegalArgumentException("Invalid dates");
		}
		this.startDate = startDate;
		this.endDate = endDate;
		this.name = name;
		deltagere.add(eier);
	}
	
	public int getId() {
		return id;
	}
	
	public void setRom(Rom rom) {
		this.rom = rom;
	}

	private boolean isValidEier(User eier) {
		return eier != null;
	}

	private boolean isValidDates(Date startDate, Date endDate) {
		if (startDate == null) {
			return false;
		}
		if (endDate == null) {
			return false;
		}
		return (endDate.getTime() - startDate.getTime()) > 0;
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
	
	public void removeFromInvitedList(User user) {
		if (!brukereInvitert.contains(user)) {
			throw new IllegalStateException("User not invited");
		}
		brukereInvitert.remove(user);
	}

	public void addToInvitedList(User user) {
		if (brukereInvitert.contains(user)) {
			throw new IllegalStateException("User already invited");
		}
		if (user == eier) {
			throw new IllegalStateException("Cant invite owner");
		}
		// Sjekk at vi ikke potensielt overbooker
		int potentialUsers = brukereInvitert.size() + deltagere.size();
		if (potentialUsers > rom.getAntall()) {
			throw new IllegalStateException("Room is too small to invite more people.");
		}
		brukereInvitert.add(user);
		db.inviteToAktivitet(this.getId(), user.getId());
	}

	public void acceptInvitation(User user) {
		if (deltagere.contains(user)) {
			throw new IllegalStateException("User has already accepted");
		}
		if (!brukereInvitert.contains(user)) {
			throw new IllegalStateException("User not invited");
		}
		brukereInvitert.remove(user);
		deltagere.add(user);
	}

}
