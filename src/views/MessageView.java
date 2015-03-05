package views;

import java.util.ArrayList;
import java.util.Stack;

import utlils.Console;

public class MessageView implements View {

	private String message;
	private boolean done;
	
	public MessageView(String message) {
		this.message = message;
	}
	
	@Override
	public boolean isDone() {
		return done;
	}

	@Override
	public void setUnDone() {
		done = false;
	}

	@Override
	public String getTitle() {
		return "Viktig melding";
	}

	@Override
	public ArrayList<String> getContent() {
		ArrayList<String> lines = new ArrayList<String>();
		lines.add(""); lines.add(""); lines.add("");
		lines.add("              +------------------------ MELDING -----------------------+");
		lines.add("              |                                                        |");
		lines.add("              | "      +     Console.matchLength(message, 54)    +   " |");
		lines.add("              |                                                        |");
		lines.add("              +--------------------------------------------------------+");
		return lines;
	}

	@Override
	public String getQuery() {
		return "Trykk hva du vil for å fortsette. >";
	}

	@Override
	public void giveInput(String input, Stack<View> viewStack) {
		this.done = true;
	}

}
