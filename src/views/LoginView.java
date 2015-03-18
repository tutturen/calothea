package views;

import helpers.LoginResult;

import java.util.ArrayList;

import utlils.Console;
import utlils.ViewStack;
import controllers.UserController;
import models.MainUser;

public class LoginView extends BaseView {

	private boolean emailWritten = false;
	private final static int WIDTH = 50;

	private String email;
	private String password;
	
	@Override
	public void setUnDone() {
		super.setUnDone();
		this.email = "";
		emailWritten = false;
	}

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
		content.add("                 " + Console.tableHead("Logg inn", WIDTH));
		content.add("                 " + Console.tableRow("Epost: " + (email == null ? "" : email), WIDTH));
		content.add("                 " + Console.tableRow(WIDTH));

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
	public void giveInput(String input, ViewStack viewStack) {
		super.giveInput(input, viewStack);
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
				this.setUnDone();
			} else {
				email = "";
				emailWritten = false;
			}
		}

	}

}
