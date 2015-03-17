package models;

public class Alert {
	
	private Aktivitet appointment;
	private String alert_message;
	private User user;
	
	public Alert(Aktivitet aktivitet, User user, String message) {
		this.user = user;
		this.appointment = aktivitet;
		this.alert_message = message;
	}

	public String getMessage() {
		return alert_message;
	}
	
	public Aktivitet getActivity(){
		return appointment;
	}
	
	public User getUser() {
		return user;
	}

}
