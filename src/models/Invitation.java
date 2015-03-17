package models;

public class Invitation {
	
	private Aktivitet activity;
	private User user;
	private String message;
	private int participates;

	public Invitation(Aktivitet aktivitet, User user) {
		this.user = user;
		this.activity = aktivitet;
		this.message = "Du er invitert til avtale klokken:" + aktivitet.getStartDate().toString() + "Slutt:" + aktivitet.getEndDate().toString() + "i rom:" + aktivitet.getRom().getName();
	}
	
	public Aktivitet getActivity(){
		return activity;
	}
	public String getMessage(){
		return message;
	}
	
	public Boolean isAccepted() {
		if (participates == 0) {
			return null;
		}
		return participates > 0;
	}
	
	public User getUser() {
		return user;
	}
	
	public void accept(){
		this.participates = 1;
	}
	
	public void decline() {
		this.participates = -1;
	}
	
	@Override
	public String toString() {
		return "[Invitation user: " + user.getName() + ", p: " + participates + " ]";
	}
	
}
