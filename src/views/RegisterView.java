package views;

import java.util.ArrayList;
import utlils.ViewStack;
import models.User;
import controllers.UserController;

public class RegisterView extends BaseView {

	private String email = "";
	private String name = "";
	private String role = "";
	private String password = "";

	@Override
	public String getTitle() {
		return "Registrer bruker";
	}

	@Override
	public ArrayList<String> getContent() {
		ArrayList<String> output = new ArrayList<String>();
		output.add("For å registrerw bruker kreves at du oppgir: Navn, stillingsrolle, email og passord");
		output.add("Dette har du skrevet så langt:");
		output.add("Navn: " + (name == null ? "" : name));
		output.add("Rolle: " + (role == null ? "" : role));
		output.add("Email: " + (email == null ? "" : email));
		output.add("Passord: " + (password == null ? "" : password));
		return output;
	}

	@Override
	public String getQuery() {
		if (name.length() == 0)
			return "Navn > ";
		else if (!(name.length() == 0) && (role.length() == 0))
			return "Rolle > ";
		else if (!(name.length() == 0) && !(role.length() == 0)
				&& (email.length() == 0))
			return "Email > ";
		else if (!(name.length() == 0) && !(role.length() == 0)
				&& !(email.length() == 0) && (password.length() == 0))
			return "Password > ";
		else
			return null;
	}

	@Override
	public void giveInput(String input, ViewStack viewStack) {
		super.giveInput(input, viewStack);
		if (input.toLowerCase().equals("quit")) {
			this.done = true;
			return;
		}
		if (name.length() == 0)
			name = input;
		else if (role.length() == 0)
			role = input;
		else if (email.length() == 0)
			email = input;
		else if (password.length() == 0) {
			password = input;
			if (User.isValidUser(name, role, email, password)) {
				UserController.register(email, name, password, role);
				this.done = true;
			} else {
				this.clearAll();
			}
		}
	}

	private void clearAll() {
		name = "";
		email = "";
		password = "";
		role = "";

	}

}
