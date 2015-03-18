package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import requests.ReqClient;
import requests.ReqService;
import models.Aktivitet;
import models.User;

public class AvtaleController {

	ArrayList<Aktivitet> avtaler;

	static ReqService db = ReqClient.getInstance().getService();

	public static Aktivitet createAvtale(User admin, String name, String message,
			String location, Date startDate, Date endDate) {
		Aktivitet aktivitet = db.createActivity(admin.getId(), name, message,
				location, startDate.getTime(), endDate.getTime());
		return aktivitet;
	}

	public static ArrayList<Aktivitet> getAktiviteter(int userId, long start,
			long stop) {

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

	public static void setAttending(int activityId, int userId,
			boolean attending) {
		int att = attending ? 1 : -1;
		db.setAttending(activityId, userId, att);
	}

	public static void changeStartTime(int activityId, int year, int month,
			int day, int hours, int minutes) throws ParseException {
		Date date = makeDate(year, month, day, hours, minutes);
		db.setStartTime(activityId, date.getTime());
	}

	public static void changeEndTime(int activityId, int year, int month, int day, int hours, int minutes) throws ParseException {
		String datestring = hours + ":" + minutes + "-" + day + "." + month
				+ "." + year;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm-dd.MM.yyyy",
				Locale.GERMAN);
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date date = format.parse(datestring);
		db.setEndTime(activityId, date.getTime());
	}

	public static void changeLocation(int activityId, int userId, String location) {
		db.setLocation(activityId, userId, location);
	}

	public static void changeMessage(int appointmentId, int userId, String message) {
		db.setMessage(appointmentId, userId, message);
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
	
	public static void setRoom(int roomId, int appointmentId) {
		db.setRoom(appointmentId, roomId);
	}

	/*
	 * private void sendInvitation(Aktivitet avtale, String melding) {
	 * System.out.println("Sending inv");
	 * 
	 * }
	 */

	private static Date makeDate(int year, int month, int day, int hours, int minutes) {
		String datestring = hours + ":" + minutes + "-" + day + "." + month
				+ "." + year;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm-dd.MM.yyyy",
				Locale.GERMAN);
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return format.parse(datestring);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
