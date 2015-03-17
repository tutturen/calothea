package controllers;

import java.util.ArrayList;

import requests.ReqClient;
import requests.ReqService;
import models.Invitation;

public class InvitationController {
	
	private static ReqService db = ReqClient.getInstance().getService();
	
	public static ArrayList<Invitation> getNewInvitations(int userId) {
		return db.getNewInvitations(userId);
	}
	
}
