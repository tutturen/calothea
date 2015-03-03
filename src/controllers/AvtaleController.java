package controllers;

import java.util.ArrayList;
import java.util.Date;

import requests.ReqClient;
import requests.ReqService;
import models.Aktivitet;
import models.User;


public class AvtaleController {
	
	ArrayList<Aktivitet> avtaler;
	
	ReqService db = ReqClient.getInstance().getService();
	
	public Aktivitet createAvtale(User eier, String name, ArrayList<User> inviterte, Date startDate, Date endDate) {
		Aktivitet aktivitet = db.createAktivitet(eier.getId(), name, startDate.getTime(), endDate.getTime());
		for (User user:  inviterte) {
			aktivitet.addToInvitedList(user);
			
		}
		return aktivitet;
	}
	
	public void editAvtale(Aktivitet avtale) {
		
	}
	
	public void deleteAvtale(int avtaleid) {
		
	
	}

	private void sendInvitation(Aktivitet avtale, String melding) {
		
		
	}
	
}
