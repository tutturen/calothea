package views;

import java.util.ArrayList;
import java.util.Stack;

import controllers.GroupController;
import controllers.UserController;
import utlils.Console;
import models.Group;
import models.User;

public class GroupView implements View {

	private boolean done;
	private Group group;
	private final static int WIDTH = 60;
	private SelectView<User> sw;
	int groupId;
	private String message;
	private Group masterGroup;

	public GroupView(int groupId) {
		done = false;
		this.groupId = groupId;
		group = GroupController.getGroup(groupId);

		try {
			masterGroup = GroupController.getGroup(group.getMasterGruppe()
					.getId());
		} catch (Exception e) {
			masterGroup = null;
		}

		if (group.getMasterGruppe() == null) {
			sw = new SelectView<User>("Velg brukere",
					UserController.getAllUsers());
			this.message = "";
		}
		if (this.masterGroup != null) {
			sw = new SelectView<User>("Velg brukere", masterGroup.getMembers());
			this.message = "";
		}
	}

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
		return "Oversikt: " + group.getName();
	}

	@Override
	public ArrayList<String> getContent() {
		if (sw.isDone()) {
			GroupController.addMember(group.getId(), sw.getSelected().getId());
			sw.setUnDone();

		}
		group = GroupController.getGroup(this.groupId);
		ArrayList<String> content = new ArrayList<String>();
		content.add(Console.tableHead(group.getName(), WIDTH));
		if (group.getMasterGruppe() != null) {
			content.add(Console.tableRow("Mastergruppe: "
					+ group.getMasterGruppe().getName(), WIDTH));
			content.add("+" + Console.charLine('-', WIDTH - 2) + "+");
		}
		content.add("| " + Console.matchLength("NAVN", 38) + " "
				+ Console.matchLength("ROLLE", 18) + "|");
		content.add("+" + Console.charLine('-', WIDTH - 2) + "+");
		for (User user : group.getMembers()) {
			content.add("| " + Console.matchLength(user.getName(), 38) + " "
					+ Console.matchLength(user.getRole(), 18) + "|");
		}
		content.add("+" + Console.charLine('-', WIDTH - 2) + "+");
		if (sw.isDone()) {
			content.add("DU HAR VALGT: " + sw.getSelected());
		}
		if (this.message.length() > 0) {
			content.add(message);
			message = "";
		}

		if (this.masterGroup == null) {
			content.add("\n\nSubgrupper:\n");
			for (Group group : this.group.getSubgrupper()) {
				content.add(group.getName());
			}
		}
		return content;
	}

	@Override
	public String getQuery() {

		return "Trykk enter for å gå tilbake, trykk + for å legge tid medlemmer.\nHvis du prøver å legge til medlemmer fra en subgruppe kan du bare velge fra de som er i Mastergruppen. \nHvis du vil lage en subgruppe til denne gruppen skriv '+sub'.";

	}

	@Override
	public void giveInput(String input, Stack<View> viewStack) {

		if (input.equals("+")) {

			viewStack.push(sw);

		}

		if (this.masterGroup == null && input.toLowerCase().equals("+sub")) {
			viewStack.pop();
			viewStack.push(new CreateSubGroup(group));
		}
		if (this.masterGroup != null && input.toLowerCase().equals("+sub")) {
			this.message = "Du kan ikke lage subgrupper i en subgruppe. Mastergruppe for denne gruppen er: "
					+ group.getMasterGruppe().getName();
		}

		if (input.length() == 0)
			this.done = true;

	}

}
