package views;

import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

import controllers.AvtaleController;
import models.Aktivitet;
import models.User;

public class PersonalCalendarView extends CalendarView {

	private String title;
	private ArrayList<Aktivitet> aktiviteter;
	private boolean done = false;
	
	public PersonalCalendarView(User user) {
		this.title = user.getName() + " sin kalender";
		User owner = new User(12, "jon@gmail.com", "Jon", "Systemutvikler");
		Date startDate = new Date(System.currentTimeMillis() + 5000L);
		Date endDate = new Date(System.currentTimeMillis() + 10000L);
		aktiviteter = AvtaleController.getAktiviteter(user.getId(), 23, 25); // Her må vi ha riktig dato for en uke
		aktiviteter.add(new Aktivitet(owner, "Kul aktivitet", startDate, endDate));
		aktiviteter.add(new Aktivitet(owner, "Morsom aktivitet", startDate, endDate));
		aktiviteter.add(new Aktivitet(owner, "Artig aktivitet", startDate, endDate));
	}
	
	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public ArrayList<String> getContent() {
		ArrayList<String> lines = new ArrayList<String>();
		for (Aktivitet aktivitet : aktiviteter) {
			lines.add(aktivitet.getNavn());
		}
		return lines;
	}


	public ArrayList<View> getChildViews() {
		ArrayList<View> childViews = new ArrayList<View>();
		for (Aktivitet aktivitet: aktiviteter) {
			childViews.add(new AktivitetView(aktivitet));
		}
		return childViews;
	}

	@Override
	public void giveInput(String input, Stack<View> viewStack) {
		User owner = new User(12, "jon@gmail.com", "Jon", "Systemutvikler");
		Date startDate = new Date(System.currentTimeMillis() + 5000L);
		Date endDate = new Date(System.currentTimeMillis() + 10000L);
		viewStack.push(new AktivitetView(new Aktivitet(owner, "Best aktivitet", startDate, endDate)));
	}

	@Override
	public String getQuery() {
		return done + " Kalender >";
	}

	@Override
	public boolean isDone() {
		return done;
	}

	@Override
	public void setUnDone() {
		this.done = false;
		
	}

}
