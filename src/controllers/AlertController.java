package controllers;

import java.util.ArrayList;

import models.Alert;
import requests.ReqClient;
import requests.ReqService;

public class AlertController {
	static ReqService db = ReqClient.getInstance().getService();
	
	public static ArrayList<Alert> getAlerts(int userId) {
		return db.getUserAlerts(userId);
	}
}
