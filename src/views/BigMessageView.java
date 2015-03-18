package views;

import java.util.ArrayList;

import utlils.Console;
import utlils.ViewStack;

public class BigMessageView extends BaseView {
	
		private int WIDTH = 70;
		ArrayList<String> message;
		
		public BigMessageView(String message) {
			this.message = Console.fitInBox(message, WIDTH - 2, 2);
		}

		@Override
		public String getTitle() {
			return "Viktig melding";
		}

		@Override
		public ArrayList<String> getContent() {
			ArrayList<String> lines = new ArrayList<String>();
			lines.add("       " + Console.tableHead("MELDING", WIDTH));
			lines.add("       " + Console.tableRow("", WIDTH));
			for (String line : message) {
				lines.add("       " + Console.tableRow(line, WIDTH));
			}
			lines.add("       " + Console.tableRow("", WIDTH));
			lines.add("       " + Console.tableRow(WIDTH));
			return lines;
		}

		@Override
		public String getQuery() {
			return "Hvis du trykker på en tast, så stryker du på eksamen >";
		}
		
		@Override
		public void giveInput(String input, ViewStack viewStack) {
			this.done = true;
		}
}
