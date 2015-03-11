package views;

import java.util.ArrayList;
import java.util.Stack;

import controllers.GroupController;
import models.Group;
import models.MainUser;

public class CreateSubGroup implements View {

	private boolean done;
	private String groupName;
	private Group masterGroup;
	private Group subGroup;

	public CreateSubGroup(Group masterGroup) {
		this.masterGroup = masterGroup;
		this.done = false;
	}

	public boolean isDone() {
		return done;
	}

	public void setUnDone() {
		this.done = false;

	}

	public String getTitle() {
		return "Lag gruppe";
	}

	public ArrayList<String> getContent() {
		ArrayList<String> output = new ArrayList<String>();
		output.add("For Œ opprette en subgruppe kreves det at du gir gruppen et navn. Navnet mŒ v¾re lenger enn 4 bokstaver");
		output.add("Du legger til medlemmer etter at gruppen er opprettet");
		return output;
	}

	public String getQuery() {
		if (groupName == null)
			return "Angi gruppenavn - minst 5 tegn > ";
		if (groupName.length() < 5)
			return "Gruppenavnet ditt var ikke gyldig. \n Angi gruppenavn - minst 5 tegn > ";
		else
			return "";
	}

	public void giveInput(String input, Stack<View> viewStack) {
		this.groupName = input;
		if (groupName.length() < 5) {
			return;
		} else {
			this.done = true;
			viewStack.remove(viewStack.size() - 1);
			int id = masterGroup.getId();
			this.subGroup = GroupController.createGroup(groupName, id);
			GroupController.addMember(this.subGroup.getId(), MainUser
					.getInstance().getId());
			viewStack.push(new GroupToolMenuView(this.subGroup));
			return;
		}
	}

}
