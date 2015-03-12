package views;

import java.util.ArrayList;
import controllers.GroupController;
import utlils.Console;
import utlils.ViewStack;
import models.Group;
import models.User;

public class GroupView extends BaseView{
	
	private Group group;
	private final static int WIDTH = 60;
	
	public GroupView(int groupId){
		System.out.println("hey hey");
		group = GroupController.getGroup(groupId);
		System.out.println("laget");
	}


	@Override
	public String getTitle() {
		return group.getName();
	}

	@Override
	public ArrayList<String> getContent() {
		ArrayList<String> content = new ArrayList<String>();
		content.add(Console.tableHead(group.getName(), WIDTH));
		if (group.getMasterGruppe() != null) {
			content.add(Console.tableRow("Mastergruppe: " + group.getMasterGruppe().getName(), WIDTH));
			content.add("+" + Console.charLine('-', WIDTH - 2) + "+");
		}
		content.add("| " + Console.matchLength("NAVN", 38) + " " + Console.matchLength("ROLLE", 18) + "|");
		content.add("+" + Console.charLine('-', WIDTH - 2) + "+");
		for (User user : group.getMembers()) {
			content.add("| " + Console.matchLength(user.getName(), 38) + " " + Console.matchLength(user.getRole(), 18) + "|");
		}
		content.add("+" + Console.charLine('-', WIDTH - 2) + "+");
		return content;
	}

	@Override
	public String getQuery() {
		return "Trykk enter for å gå tilbake >";
	}

	@Override
	public void giveInput(String input, ViewStack viewStack) {
		super.giveInput(input, viewStack);
		super.done = true;
		
	}

}
