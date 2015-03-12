package models;

public class Invitation {
	
	private Aktivitet activity;
	private User user;
	private String message;
	private Boolean participates;

	public Invitation(Aktivitet aktivitet, User user) {
		this.user = user;
		this.activity = aktivitet;
		this.message = "Du er invitert til avtale klokken:" + aktivitet.getStartDate().toString() + "Slutt:" + aktivitet.getEndDate().toString() + "i rom:" + aktivitet.getRom().getName();
		this.participates = false;
	}
	
	public Aktivitet getActivity(){
		return activity;
	}
	public String getMessage(){
		return message;
	}
	
	public Boolean isAccepted() {
		return participates;
	}
	
	public User getUser() {
		return user;
	}
	
	public void accept(){
		this.participates = true;
	}
	
	public void decline() {
		this.participates = false;
	}
	
	@Override
	public String toString() {
		return "[Invitation user: " + user.getName() + ", p: " + participates + " ]";
	}
	
}
