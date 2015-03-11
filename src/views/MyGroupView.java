package views;

import java.util.ArrayList;
import java.util.Stack;

import utlils.Console;
import controllers.GroupController;
import models.Group;
import models.MainUser;

public class MyGroupView implements View{
	
	private boolean done;
	private ArrayList<Group> grupper;

	public MyGroupView(){
		this.done = false;
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
		return "Dine grupper";
	}

	@Override
	public ArrayList<String> getContent() {
		grupper = GroupController.getAllGroups(MainUser.getInstance());
		ArrayList<String> content = new ArrayList<String>();
		int i = 1;
		content.add(" ID  | NAVN");
		content.add("-----+---------------------");
		for (Group gruppe : grupper) {
			String id = Console.matchLength(i++ + "" , 3);
			content.add(" " + id + " | " + gruppe.getName());
		}
		content.add("");
		return content;
		
	}

	@Override
	public String getQuery() {
		return "Velg gruppe eller opprett gruppe ved å presse '+'. Press enter for å gå tilbake";
	}

	@Override
	public void giveInput(String input, Stack<View> viewStack) {
		if(input.length() ==0){
			this.done = true;
			return;
		}
		if(input.equals("+")){
			viewStack.push(new CreateGroupView());
			return;
		}
		
		try {
			int id = Integer.parseInt(input);
			if (id > 0 && id <= grupper.size()) {
				Group gruppe = grupper.get(id - 1);
				viewStack.push(new GroupView(gruppe.getId()));			
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
