package views;

import interfaces.View;
import utlils.ViewStack;

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
	public void giveInput(String input, ViewStack viewStack) {
		if (input.length() == 1 && input.toLowerCase().charAt(0) == 'q') {
			this.done = true;
			return;
		}
	}

}
