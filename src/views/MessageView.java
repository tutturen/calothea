package views;

import java.util.ArrayList;
import utlils.Console;
import utlils.ViewStack;

public class MessageView extends BaseView {

	private String message;
	
	public MessageView(String message) {
		this.message = message;
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
	public void giveInput(String input, ViewStack viewStack) {
		super.giveInput(input, viewStack);
		this.done = true;
	}

}
