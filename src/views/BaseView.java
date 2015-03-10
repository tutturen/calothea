package views;

import interfaces.View;
import java.util.Stack;

public abstract class BaseView implements View {

	protected boolean done;
	
	@Override
	public boolean isDone() {
		return done;
	}

	@Override
	public void setUnDone() {
		this.done = false;
	}

	@Override
	public void giveInput(String input, Stack<View> viewStack) {
		if (input.length() == 1 && input.toLowerCase().charAt(0) == 'q') {
			this.done = true;
			return;
		}
	}

}
