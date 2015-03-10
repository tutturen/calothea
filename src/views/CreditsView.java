package views;

import interfaces.View;

import java.util.ArrayList;
import java.util.Stack;

public class CreditsView implements View {

	private boolean done;

	public CreditsView() {
		done = false;
	}

	@Override
	public boolean isDone() {
		return done;
	}

	@Override
	public String getTitle() {
		return "Credits";
	}

	@Override
	public ArrayList<String> getContent() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("");
		content.add("Laget med ren og skjær flaks av Gruppe 47");
		content.add("");
		content.add("Scrum Master:\t\t\tThea Ullebust");
		content.add("Databaseansvarlig:\t\tAhmed Ahmedov");
		content.add("Applikasjonsansvarlig:\t\tSimen Hellem");
		content.add("Utviklingsansvarlig:\t\tSjur Wagbøe");
		content.add("GUI-ansvarlig:\t\t\tPer Oskar Isdahl");
		content.add("Utvikler:\t\t\tThor Even Tutturen");
		// TODO Auto-generated method stub
		return content;
	}

	@Override
	public String getQuery() {
		return "Trykk enter for å gå tilbake >";
	}

	@Override
	public void giveInput(String input, Stack<View> viewStack) {
		this.done = true;
	}

	@Override
	public void setUnDone() {
		this.done = false;
	}

}
