package views;

import java.util.ArrayList;
import java.util.Date;

import controllers.AvtaleController;
import models.Aktivitet;
import models.MainUser;
import utlils.ViewStack;

public class NewAppointmentView extends BaseView {

	private int status = 1;
	private final static int NOT_CREATED = 0, SET_NAME = 1, SET_START = 2, SET_END = 3, SET_LOCATION = 4;
	
	
	@Override
	public String getTitle() {
		return "Lag ny avtale";
	}

	@Override
	public ArrayList<String> getContent() {
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("En ny avtale må lages. Hva venter du på?");
		return lines;
	}

	@Override
	public String getQuery() {
		switch (status) {
		case NOT_CREATED: return "wat";
		case SET_NAME: return "Skriv navnet på aktiviteten >";
		}
		return "ehm";
	}
	
	@Override
	public void giveInput(String input, ViewStack viewStack) {
		super.giveInput(input, viewStack);
		if (status == SET_NAME && input.length() > 1) {
			String message = "Viktig møte.";
			String location = "Ubestemt";
			Date end = new Date();
			// Set it to last two hours
			end.setTime(end.getTime() + 7200000);
			Aktivitet newActivity = AvtaleController.createAvtale(MainUser.getInstance(), input, message, location, new Date(), end);
			viewStack.pop();
			viewStack.push(new AktivitetView(newActivity.getId()));
			viewStack.push(new ChangeAppointmentView(newActivity));
			this.done = true;
		}
	}
}
