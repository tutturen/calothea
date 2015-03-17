package views;

import interfaces.View;
import java.util.ArrayList;
import utlils.ViewStack;
import controllers.UserController;
import models.MainUser;
import models.User;

public class MenuView extends BaseView {

	ArrayList<View> applicationViews;
	private SelectView<User> sw;

	public MenuView() {
		applicationViews = new ArrayList<View>();
		applicationViews.add(new PersonalCalendarView(MainUser.getInstance()));
		applicationViews.add(new CreditsView());
		applicationViews.add(new MyGroupView());
		applicationViews.add(new NewAppointmentView());
		/*applicationViews.add(new MessageView("Du er skikkelig skikkelig kul! Bare så du vet det."));
		sw = new SelectView<User>("Velg brukere", UserController.getAllUsers());
		applicationViews.add(sw);*/
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
	public void giveInput(String input, ViewStack viewStack) {
		super.giveInput(input, viewStack);
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
}
