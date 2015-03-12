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
		Aktivitet aktivitet = db.createActivity(admin.getId(), name, message, location, startDate.getTime(), endDate.getTime());
		return aktivitet;
	}

	
	public static ArrayList<Aktivitet> getAktiviteter(int userId, long start, long stop) {
		
		return new ArrayList<Aktivitet>();
	}
	
	public static ArrayList<Aktivitet> getAlleAktiviteter(int userId) {
		return db.getAlleBrukerAktiviteter(userId);
	}
	
	public static Aktivitet getAktivitet(int aktivitetId) {
		return db.getActivity(aktivitetId);
	}
	
	public void editAvtale(Aktivitet avtale) {
		
	}
	
	public void deleteAvtale(int avtaleid) {
		
	}
	
	public static void setAttending(int activityId, int userId, boolean attending) {
		
	}
	
	public static void changeDate(int activityId, int day, int month, int year){
		
	}
	
	public static void changeStartTime(int activityId, int hours, int minues) {
		
	}
	
	public static void changeEndTime(int activityId, int hours, int minues) {
		
	}
	
	public static void changeLocation(int activityId, String location) {
		
	}
	
	public static void changeMessage(int activityId, String message) {
		
	}
	
	public static void inviteUser(int activityId, int userId) {
		db.inviteUserToActivity(activityId, userId);
	}
	
	public static void removeUser(int activityId, int userId) {
		db.kickUserFromActivity(activityId, userId);
	}
	
	public static void inviteGroup(int activityId, int groupId) {
		
	}
	
	public static void removeGroup(int activityId, int groupId) {
		
	}

	/*private void sendInvitation(Aktivitet avtale, String melding) {
		System.out.println("Sending inv");
		
	}*/
	
}
