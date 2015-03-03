package helpers;

public class LoginResult {
	private String message;
	private boolean success;
	private int userId;
	
	public String getMessage() {
		return message;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public boolean isSuccess() {
		return success;
	}
}
