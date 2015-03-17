package views;

import java.util.ArrayList;

import models.User;
import controllers.UserController;
import utlils.Console;
import utlils.ViewStack;

public class ColleagueView extends BaseView {

	private final static int WIDTH = 80;
	private SelectView<User> sv;
	private ViewStack stack;
	
	public ColleagueView() {
		sv = new SelectView<User>("Velg en kollega", UserController.getAllUsers());
	}
	
	@Override
	public String getTitle() {
		return "Dine kolleger";
	}

	@Override
	public ArrayList<String> getContent() {
		if (sv.isDone()) {
			User selected = sv.getSelected();
			stack.pop();
			stack.push(new PersonalCalendarView(selected));
		}
		ArrayList<String> lines = new ArrayList<String>();
		lines.add(Console.tableHead("Dine valg", WIDTH));
		lines.add(Console.tableRow("1. Se kalenderen til en kollega", WIDTH));
		lines.add(Console.tableRow(WIDTH));
		return lines;
	}

	@Override
	public String getQuery() {
		if (sv.isDone()) {
			return "Trykk enter for å se kalenderen til " + sv.getSelected().getName() + " >";
		}
		return "Velg NR >";
	}
	
	@Override
	public void setUnDone() {
		super.setUnDone();
		sv = new SelectView<User>("Velg en kollega", UserController.getAllUsers());
	}
	
	@Override
	public void giveInput(String input, ViewStack viewStack) {
		super.giveInput(input, viewStack);
		if (input.equals("1")) {
			viewStack.push(sv);
			stack = viewStack;
			return;
		}
	}

}
