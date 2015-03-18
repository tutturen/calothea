package views;

import interfaces.View;

import java.util.ArrayList;

import controllers.UserController;
import utlils.Console;
import utlils.ViewStack;
import models.MainUser;

public class MenuView extends BaseView {

	private final static int WIDTH = 80;
	ArrayList<View> applicationViews;

	public MenuView() {
		applicationViews = new ArrayList<View>();
		applicationViews.add(new PersonalCalendarView(MainUser.getInstance()));
		applicationViews.add(new CreditsView());
		applicationViews.add(new MyGroupView());
		applicationViews.add(new NewAppointmentView());
		applicationViews.add(new ColleagueView());
		applicationViews.add(new InvitationsView());
	}

	@Override
	public String getTitle() {
		return "Calothea";
	}

	@Override
	public ArrayList<String> getContent() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("                     ____ ____ ____ ____ ____ ____ ____ ____ ");
		content.add("                    ||c |||a |||l |||o |||t |||h |||e |||a ||");
		content.add("                    ||__|||__|||__|||__|||__|||__|||__|||__||");
		content.add("                    |/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|");
		content.add("");
		content.add(Console.tableHead("Funksjoner", WIDTH));
		for (int i = 0; i < applicationViews.size(); i++) {
			content.add(Console.tableRow(" " + (i + 1) + ". " + applicationViews.get(i).getTitle() ,WIDTH));
		}
		content.add(Console.tableRow(" q: Logg ut", WIDTH));
		content.add(Console.tableRow(WIDTH));
		return content;
	}

	@Override
	public String getQuery() {
		return "Velg NR >";
	}

	@Override
	public void giveInput(String input, ViewStack viewStack) {
		super.giveInput(input, viewStack);
		if (input.length() < 1) {
			return;
		}
		
		if (input.toLowerCase().equals("q")) {
			UserController.logout();
			this.done = true;
			return;
		}

		int nr = input.charAt(0) - '0';
		if (nr > 0 && nr <= applicationViews.size()) {
			View v = applicationViews.get(nr - 1);
			v.setUnDone();
			viewStack.push(v);
		}

	}
}
