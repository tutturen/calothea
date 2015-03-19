package views;

import interfaces.View;

import java.util.ArrayList;
import utlils.ViewStack;
import controllers.GroupController;
import models.Group;
import models.MainUser;

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
		ArrayList<String> output = new ArrayList<String>();
		output.add("For � opprette gruppe kreves det at du gir gruppen et navn. Navnet m� v�re lenger enn 4 bokstaver");
		output.add("Du legger til medlemmer etter at gruppen er opprettet");
		return output;
	}

	@Override
	public String getQuery() {
		if(groupName==null)
			return "Angi gruppenavn - minst 5 tegn > ";
		if(groupName.length() < 5 )
			return "Gruppenavnet ditt var ikke gyldig. \n Angi gruppenavn - minst 5 tegn > ";
		else
			return "";
	}

	@Override
	public void giveInput(String input, ViewStack viewStack) {
		this.groupName= input;
		if(groupName.length() < 5) {
			return;
		}	
		else{
			this.done = true;
			viewStack.pop();
			this.group = GroupController.createGroup(groupName, 0);
			GroupController.addMember(this.group.getId() ,MainUser.getInstance().getId());
			viewStack.push(new GroupToolMenuView(this.group));
			return;
		}	
	}
	
}
