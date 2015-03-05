package views;

import java.util.ArrayList;
import java.util.Stack;
import controllers.UserController;
import models.MainUser;

public class LoginView implements View {

	private boolean done = false;
	private boolean emailWritten = false;
	private String email;
	private String password;

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
		return "Logg inn";
	}

	@Override
	public ArrayList<String> getContent() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("");
		content.add("");
		content.add("Epost: " + (email == null ? "" : email));
		content.add("Du kommer aldri inn.");
		return content;
	}

	@Override
	public String getQuery() {
		if (!emailWritten) {
			return "Skriv epost >";
		}
		return "Skriv passord >";
	}

	@Override
	public void giveInput(String input, Stack<View> viewStack) {
		if (!emailWritten) {
			email = input;
			emailWritten = true;
		} else {
			password = input;
			UserController.login(email, password);
			if (MainUser.getInstance() != null) {
				viewStack.push(new MenuView());
				done = true;
			} else {
				email = "";
				emailWritten = false;
			}
		}

	}

}
