package views;

import java.util.ArrayList;
import java.util.Stack;

import models.Aktivitet;

public class AktivitetView implements View {

	private Aktivitet aktivitet;
	private boolean done = false;

	public AktivitetView(Aktivitet aktivitet) {
		this.aktivitet = aktivitet;
	}

	@Override
	public String getTitle() {
		return aktivitet.getNavn();
	}

	@Override
	public ArrayList<String> getContent() {
		return new ArrayList<String>();
	}

	@Override
	public String getQuery() {
		return "Aktivitet >";
	}

	@Override
	public boolean isDone() {
		return done;
	}

	@Override
	public void giveInput(String input, Stack<View> viewStack) {
		done = true;

	}

	@Override
	public void setUnDone() {
		this.done = false;
	}

}
