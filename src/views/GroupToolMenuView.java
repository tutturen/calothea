package views;

import interfaces.View;

import java.util.ArrayList;
import utlils.ViewStack;
import models.Group;

public class GroupToolMenuView extends BaseView {
	private Group group;
	private boolean done;
	private ArrayList<View> editViews;

	public GroupToolMenuView(Group group) {
		this.group = group;
		done = false;
		editViews = new ArrayList<View>();
		editViews.add(new GroupView(group.getId()));
		editViews.add(new GroupCalendarView(group));

	}

	public boolean isDone() {
		return done;
	}

	public void setUnDone() {
		done = false;
	}

	public String getTitle() {
		return "Rediger gruppe:" + group.getName();
	}

	public ArrayList<String> getContent() {
		ArrayList<String> content = new ArrayList<String>();
		content.add(" NR | FUNKSJON");
		content.add("--- + -------------------------------------------------------------------");
		for (int i = 0; i < editViews.size(); i++) {
			content.add("  " + (i + 1) + " | " + editViews.get(i).getTitle());
		}
		return content;

	}

	public String getQuery() {
		return "Velg NR >";
	}

	@Override
	public void giveInput(String input, ViewStack viewStack) {
		if (input.length() == 0) {
			this.done = true;
			return;
		}
		int nr = input.charAt(0) - '0';
		if (nr > 0 && nr <= editViews.size()) {
			View v = editViews.get(nr - 1);
			v.setUnDone();
			viewStack.push(v);
		}

	}

}
