package controllers;

import helpers.LoginResult;
import requests.ReqClient;
import requests.ReqService;
import models.MainUser;
import models.User;

public class UserController {
	
	private static ReqService db = ReqClient.getInstance().getService();
	
	public static User register(String email, String username, String password, String role) {
		User user = db.register(email, username, password, role);
		return MainUser.newInstance(user);
	}
	
	public static User login(String email, String password) {
		LoginResult result = db.login(email, password);
		
		if (!result.isSuccess()) {
			return null;
		}
		
		User dbUser = db.getUser(result.getUserId());
		return MainUser.newInstance(dbUser);
	}
	
}
