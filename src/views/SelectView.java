package views;

import interfaces.Selectable;

import java.util.ArrayList;
import java.util.Stack;

import utlils.Console;

public class SelectView<T extends Selectable> implements View {

	private boolean done;
	private ArrayList<Selectable> elements;
	private int index = 0;
	private String title;
	private T chosen;

	public SelectView(String title, ArrayList<T> elements) {
		this.title = title;
		this.done = false;
		this.elements = new ArrayList<Selectable>(elements);
	}

	public T getSelected() {
		return chosen;
	}

	@Override
	public boolean isDone() {
		return done;
	}

	@Override
	public void setUnDone() {
		this.done = false;
		this.chosen = null;
	}

	@Override
	public String getQuery() {
		return "A <- VELG ID -> D";
	}

	@SuppressWarnings("unchecked")
	@Override
	public void giveInput(String input, Stack<View> viewStack) {
		if (input.length() == 0) {
			return;
		}
		try {
			int id = Integer.parseInt(input);
			if (id > 0 && id < elements.size() + 1) {
				this.chosen = (T) elements.get(id - 1);
				this.done = true;
			}

		} catch (Exception e) {
			char c = input.toLowerCase().charAt(0);
			if (c == 'a') {
				if (index > 12) {
					index -= 12;
				} else {
					index = 0;
				}
			} else if (c == 'd') {
				if (index > (elements.size() - 12)) {
					index = 0;
				} else {
					index += 12;
				}
			}
		}
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public ArrayList<String> getContent() {
		int width = 70;
		ArrayList<String> c = new ArrayList<String>();
		c.add(Console.tableHead("Velg et element", width));
		for (int i = index + 1; i <= elements.size(); i++) {
			c.add(Console.tableRow(Console.matchLength(" " + i, 4) + "| "
					+ elements.get(i - 1).getName(), width));
		}
		c.add("+" + Console.charLine('-', width - 2) + "+");
		return c;
	}

}
