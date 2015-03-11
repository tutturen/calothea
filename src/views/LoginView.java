package views;

import helpers.LoginResult;

import java.util.ArrayList;
import java.util.Stack;

import controllers.UserController;
import models.MainUser;

public class LoginView implements View {

	private boolean done = false;

	private boolean emailWritten = false;

	private String email;
	private String password;

	private void addSplash(ArrayList<String> content) {
		content.add("     _______  _______  ___      _______  _______  __   __  _______  _______    ");
		content.add("    |       ||   _   ||   |    |       ||       ||  | |  ||       ||   _   |   ");
		content.add("    |       ||  |_|  ||   |    |   _   ||_     _||  |_|  ||    ___||  |_|  |   ");
		content.add("    |       ||       ||   |    |  | |  |  |   |  |       ||   |___ |       |   ");
		content.add("    |      _||       ||   |___ |  |_|  |  |   |  |       ||    ___||       |   ");
		content.add("    |     |_ |   _   ||       ||       |  |   |  |   _   ||   |___ |   _   |   ");
		content.add("    |_______||__| |__||_______||_______|  |___|  |__| |__||_______||__| |__|   ");
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
		return "Logg inn";
	}

	@Override
	public ArrayList<String> getContent() {
		ArrayList<String> content = new ArrayList<String>();
		addSplash(content);
		content.add("");
		content.add("                     Hvis du vil registere deg, skriv 'registrer'");
		content.add("");
		content.add("");
		content.add("Epost: " + (email == null ? "" : email));

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
		if (input.toLowerCase().equals("registrer")) {
			viewStack.push(new RegisterView());
			this.email = "";
			emailWritten = false;
			return;
		}
		if (!emailWritten) {
			email = input;
			emailWritten = true;
		} else {
			password = input;
			LoginResult res = UserController.login(email, password);
			if (!res.isSuccess()) {
				viewStack.push(new MessageView(res.getMessage()));
			}
			if (MainUser.getInstance() != null) {
				done = true;
				viewStack.push(new MenuView());
			} else {
				email = "";
				emailWritten = false;
			}
		}

	}

}
