package views;

import java.util.ArrayList;
import java.util.Stack;

import models.Group;

public class CreateGroupView implements View {

	private boolean done;
	private String groupName;
	private Group group;

	@Override
	public boolean isDone() {
		return done;
	}

	@Override
	public void setUnDone() {
		this.done = false;

	}

	@Override
	public String getTitle() {
		return "Lag gruppe";
	}

	@Override
	public ArrayList<String> getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQuery() {

		return null;
	}

	@Override
	public void giveInput(String input, Stack<View> viewStack) {
		// TODO Auto-generated method stub

	}

}
