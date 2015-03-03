package models;

public class MainUser {

	private static User instance;
	
	public final static User newInstance(User user) {
		instance = user;
		return instance;
	}
	
	public final static User getInstance() {
		return instance;
	}
	

}
