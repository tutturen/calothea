package controllers;

import java.util.ArrayList;
import java.util.Date;

import requests.ReqClient;
import requests.ReqService;
import models.Aktivitet;
import models.User;


public class AvtaleController {
	
	ArrayList<Aktivitet> avtaler;
	
	static ReqService db = ReqClient.getInstance().getService();
	
	public Aktivitet createAvtale(User admin, String name, String message, String location, Date startDate, Date endDate) {
		Aktivitet aktivitet = db.createAktivitet(admin.getId(), name, message, location, startDate.getTime(), endDate.getTime());
		return aktivitet;
	}

	
	public static ArrayList<Aktivitet> getAktiviteter(int userId, long start, long stop) {
		
		return new ArrayList<Aktivitet>();
	}
	
	public static ArrayList<Aktivitet> getAlleAktiviteter(int userId) {
		return db.getAlleBrukerAktiviteter(userId);
	}
	
	public static Aktivitet getAktivitet(int aktivitetId) {
		return db.getAktivitet(aktivitetId);
	}
	
	public void editAvtale(Aktivitet avtale) {
		
	}
	
	public void deleteAvtale(int avtaleid) {
		
	}

	/*private void sendInvitation(Aktivitet avtale, String melding) {
		System.out.println("Sending inv");
		
	}*/
	
}
