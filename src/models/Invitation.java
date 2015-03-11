package models;

public class Invitation {
	
	private Aktivitet activity;
	private User user;
	private String message;
	private boolean accepted;

	public Invitation(Aktivitet aktivitet, User user) {
		this.user = user;
		this.activity = aktivitet;
		this.message = "Du er invitert til avtale klokken:" + aktivitet.getStartDate().toString() + "Slutt:" + aktivitet.getEndDate().toString() + "i rom:" + aktivitet.getRom().getName();
		this.accepted = false;
	}
	
	public Aktivitet getActivity(){
		return activity;
	}
	public String getMessage(){
		return message;
	}
	
	public boolean isAccepted() {
		return accepted;
	}
	
	public User getUser() {
		return user;
	}
	
	public void accept(){
		this.accepted = true;
	}
	
	public void decline() {
		this.accepted = false;
	}
	
}
