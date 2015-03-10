package models;

import java.util.ArrayList;
import java.util.Date;

import requests.ReqClient;
import requests.ReqService;

public class Aktivitet implements Comparable<Aktivitet> {

	private int id;
	private ArrayList<User> brukereInvitert;
	private ArrayList<User> deltagere;
	private Date start, end;
	private Rom room;
	private User administrator;
	private String name;
	private String location;
	private String message;
	
	ReqService db = ReqClient.getInstance().getService();

	public Aktivitet(User administrator, String name, Date startDate, Date endDate) {
		brukereInvitert = new ArrayList<User>();
		deltagere = new ArrayList<User>();
		if (!isValidEier(administrator)) {
			throw new IllegalArgumentException("User invalid");
		}
		this.administrator = administrator;
		if (!isValidDates(startDate, endDate)){
			throw new IllegalArgumentException("Invalid dates");
		}

		this.start = startDate;
		this.end = endDate;
		this.name = name;
		deltagere.add(administrator);
	}
	
	public int getId() {
		return id;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setRom(Rom rom) {
		this.room = rom;
	}
	
	public String getNavn() {
		return name;
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
		return start;
	}

	public Date getEndDate() {
		return end;
	}

	public Rom getRom() {
		return room;
	}

	public User getAdmin() {
		return administrator;
	}
	
	public void removeFromInvitedList(User user) {
		removeFromInvitedList(user, true);
	}
	
	public void removeFromInvitedList(User user, boolean updateDatabase) {
		if (!brukereInvitert.contains(user)) {
			throw new IllegalStateException("User not invited");
		}
		brukereInvitert.remove(user);
		if (updateDatabase) {
			// UPDATE THE DATABASE
		}
	}

	public void addToInvitedList(User user) {
		addToInvitedList(user, true);
	}
	
	public void addToInvitedList(User user, boolean updateDatabase) {
		if (brukereInvitert.contains(user)) {
			throw new IllegalStateException("User already invited");
		}
		if (user == administrator) {
			throw new IllegalStateException("Cant invite owner");
		}
		// Sjekk at vi ikke potensielt overbooker
		int potentialUsers = brukereInvitert.size() + deltagere.size();
		if (potentialUsers > room.getAntall()) {
			throw new IllegalStateException("Room is too small to invite more people.");
		}
		brukereInvitert.add(user);
		if (updateDatabase) {
			db.inviteToAktivitet(this.getId(), user.getId());
		}
	}
	
	public void acceptInvitation(User user) {
		acceptInvitation(user, true);
	}

	public void acceptInvitation(User user, boolean updateDatabase) {
		if (deltagere.contains(user)) {
			throw new IllegalStateException("User has already accepted");
		}
		if (!brukereInvitert.contains(user)) {
			throw new IllegalStateException("User not invited");
		}
		brukereInvitert.remove(user);
		deltagere.add(user);
		if (updateDatabase) {
			// Gjør endringer i databasen (retrofit)
		}
	}

	@Override
	public int compareTo(Aktivitet other) {
		if (this.start.getTime() == other.start.getTime()) {
			return (int) (this.end.getTime() - other.end.getTime());
		}
		return (int) (this.start.getTime() - other.start.getTime()); 
	}

}
