package views;

import java.util.ArrayList;
import java.util.Stack;

import models.MainUser;

public class MenuView implements View {

	ArrayList<View> applicationViews;
	private boolean done = false;

	public MenuView() {
		applicationViews = new ArrayList<View>();
		applicationViews.add(new PersonalCalendarView(MainUser.getInstance()));
		applicationViews.add(new CreditsView());
	}

	@Override
	public boolean isDone() {
		return done;
	}

	@Override
	public String getTitle() {
		return "Calothea";
	}

	@Override
	public ArrayList<String> getContent() {
		ArrayList<String> content = new ArrayList<String>();
		content.add(" NR | FUNKSJON");
		content.add("--- + -------------------------------------------------------------------");
		for (int i = 0; i < applicationViews.size(); i++) {
			content.add("  " + (i + 1) + " | "
					+ applicationViews.get(i).getTitle());
		}
		return content;
	}

	@Override
	public String getQuery() {
		return "Velg NR >";
	}

	@Override
	public void giveInput(String input, Stack<View> viewStack) {
		if (input.length() < 1) {
			return;
		}

		int nr = input.charAt(0) - '0';
		if (nr > 0 && nr <= applicationViews.size()) {
			View v = applicationViews.get(nr - 1);
			v.setUnDone();
			viewStack.push(v);
		}

	}

	@Override
	public void setUnDone() {
		this.done = false;
	}

}
