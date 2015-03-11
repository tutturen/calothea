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
	private SelectView<Group> sw;

	public MyGroupView(){
		this.done = false;
		sw = new SelectView<Group>("Velg gruppe du vil slette", GroupController.getAllGroups(MainUser.getInstance()));
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
		if(sw.isDone()){
			GroupController.deleteGroup(sw.getSelected().getId());
			sw = new SelectView<Group>("Velg gruppe du vil slette", GroupController.getAllGroups(MainUser.getInstance()));
			
		}
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
		return "Velg gruppe ved å velge tilhørende tall, opprett gruppe ved å presse '+' eller fjern gruppe ved å skrive '-'. \nPress enter for å gå tilbake";
	}

	@Override
	public void giveInput(String input, Stack<View> viewStack) {
		if(input.length() ==0){
			this.done = true;
			return;
		}
		else if(input.equals("+")){
			viewStack.push(new CreateGroupView());
			return;
		}
		else if(input.equals("-")){
			viewStack.push(sw);
			return;
		}
		
		try {
			System.out.println(input);
			int id = Integer.parseInt(input);
			System.out.println(id);
			if (id > 0 && id <= grupper.size()) {
				Group gruppe = grupper.get(id - 1);
				System.out.println(gruppe.getName());
				viewStack.push(new GroupToolMenuView(gruppe));	
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
