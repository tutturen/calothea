package views;

import java.util.ArrayList;
import java.util.Stack;
import controllers.UserController;
import models.MainUser;
import models.User;

public class MenuView implements View {

	ArrayList<View> applicationViews;
	private boolean done = false;
	private SelectView<User> sw;

	public MenuView() {
		applicationViews = new ArrayList<View>();
		applicationViews.add(new PersonalCalendarView(MainUser.getInstance()));
		applicationViews.add(new CreditsView());
		applicationViews.add(new MyGroupView());
		applicationViews.add(new MessageView(
				"Du er skikkelig skikkelig kul! Bare så du vet det."));
		sw = new SelectView<User>("Velg brukere", UserController.getAllUsers());
		applicationViews.add(sw);
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
		if (sw.isDone()) {
			content.add("DU HAR VALGT: " + sw.getSelected());
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
