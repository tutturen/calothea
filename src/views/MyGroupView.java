package views;

import java.util.ArrayList;
import java.util.Stack;

import utlils.Console;
import controllers.GroupController;
import models.Group;
import models.MainUser;

public class MyGroupView implements View {

	private boolean done;
	private ArrayList<Group> grupper;
	private int index;

	public MyGroupView() {
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
		content.add(" ID  | NAVN");
		content.add("-----+---------------------");
		for (int i = index ; i< grupper.size() ; i++) {
			Group group = grupper.get(i);
			
			String id = Console.matchLength((i+1) + "", 3);
			if(group.getMasterGruppe().getName()== null){
				content.add(" " + id + " | " + group.getName());}
			else{
				content.add(" " + id + " | " + Console.matchLength(group.getName() , 25) +"| Master: " + group.getMasterGruppe().getName());
			}
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
		if (input.length() == 0) {
			this.done = true;
			return;
		}
		if (input.equals("+")) {
			viewStack.push(new CreateGroupView());
			return;
		}
		if(input.equals("a") || input.equals("d")){
			char c = input.toLowerCase().charAt(0);
			if (c == 'a') {
				if (index > 16) {
					index -= 16;
				} else {
					index = 0;
				}
			} else if (c == 'd') {
				if (index > (grupper.size() - 16)) {
					index = 0;
				} else {
					index += 16;
				}
			}
	}

		try {
			int id = Integer.parseInt(input);
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
