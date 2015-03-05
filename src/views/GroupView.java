package views;

import java.util.ArrayList;
import java.util.Stack;



public class GroupView implements View{
	
	private boolean done;
	String gruppe;
	
	public GroupView(String gruppe){
		this.gruppe = gruppe;
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
		return "Den beste gruppa";
	}

	@Override
	public ArrayList<String> getContent() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("");
		content.add("Simen");
		content.add("Thea");
		content.add("Per Oscar");
		content.add("");
		return content;
	}

	@Override
	public String getQuery() {
		return "Trykk enter for å gå tilbake";
	}

	@Override
	public void giveInput(String input, Stack<View> viewStack) {
		this.done = true;
		
	}

}
