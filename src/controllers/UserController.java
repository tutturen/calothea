package controllers;

import java.util.ArrayList;

import helpers.LoginResult;
import requests.ReqClient;
import models.MainUser;
import models.User;

public class UserController {
	

	public static ArrayList<User> getAllUsers() {
		return ReqClient.getInstance().getService().getAllUsers();
	}
	
	public static User register(String email, String name, String password, String role) {
		User user = ReqClient.getInstance().getService().register(email, name, password, role);
		return user;
	}
	
	public static LoginResult login(String email, String password) {
		LoginResult result = ReqClient.getInstance().getService().login(email, password);
		System.out.println(result.getMessage());
		if (!result.isSuccess()) {
			return result;
		}
		
		User dbUser = ReqClient.getInstance().getService().getUser(result.getUserId());
		MainUser.newInstance(dbUser);
		return result;
	}
	
	public static void logout() {
		MainUser.newInstance(null);
	}
	
}
