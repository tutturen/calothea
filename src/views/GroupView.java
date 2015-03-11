package views;

import java.util.ArrayList;
import java.util.Stack;

import controllers.GroupController;
import controllers.UserController;
import utlils.Console;
import models.Group;
import models.User;



public class GroupView implements View{
	
	private boolean done;
	private Group group;
	private final static int WIDTH = 60;
	private SelectView<User> sw1;
	private SelectView<User> sw2;
	int groupId;
	
	
	public GroupView(int groupId){
		done = false;
		this.groupId = groupId;
		group = GroupController.getGroup(groupId);
		sw1 = new SelectView<User>("Velg brukere", UserController.getAllUsers());
		sw2 = new SelectView<User>("Velg bruker", GroupController.getGroup(groupId).getMembers());
		
		
		
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
		if(sw1.isDone()){
			GroupController.addMember(group.getId(), sw1.getSelected().getId());
			sw1.setUnDone();
			sw2 = new SelectView<User>("Velg bruker", GroupController.getGroup(groupId).getMembers());
		}
		if(sw2.isDone()){
			GroupController.removeMember(group.getId(), sw2.getSelected().getId());
			sw2.setUnDone();
			sw2 = new SelectView<User>("Velg bruker", GroupController.getGroup(groupId).getMembers());
		}
		
		group = GroupController.getGroup(this.groupId);
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
		if (sw1.isDone()) {
			content.add("DU HAR VALGT: " + sw1.getSelected());
		}
		return content;
	}

	@Override
	public String getQuery() {

		return "Trykk enter for å gå tilbake, trykk + for å legge tid medlemmer eller - for å fjerne medlem";


	}

	@Override
	public void giveInput(String input, Stack<View> viewStack) {

		if(input.equals("+")){
			viewStack.push(sw1);
			
			
		}
		else if(input.equals("-")){
			viewStack.push(sw2);
		}
		
		else if(input.length() ==0){
			this.done = true;
			return;
		
		}
		
		
	}

}
