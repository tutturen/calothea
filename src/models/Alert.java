package models;

import java.util.Date;

public class Alert implements Comparable<Alert>{
	
	private Aktivitet appointment;
	private String alert_message;
	private User user;
	private Date created_at, updated_at;

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
	
	public Date getCreated_at() {
		return created_at;
	}
	
	public Date getUpdated_at() {
		return updated_at;
	}

	@Override
	public int compareTo(Alert o) {
		return (int) (o.getUpdated_at().getTime() - this.getUpdated_at().getTime());
	}
	
}
