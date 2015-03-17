package views;

import java.util.ArrayList;

import models.Alert;
import models.User;
import controllers.AlertController;
import utlils.Console;
import utlils.ViewStack;

public class AlertView extends BaseView {
	
	private User user;
	private ArrayList<Alert> alerts;
	
	public AlertView(User user) {
		this.user = user;
	}

	@Override
	public String getTitle() {
		return "Innboks";
	}

	@Override
	public ArrayList<String> getContent() {
		alerts = AlertController.getAlerts(user.getId());
		ArrayList<String> lines = new ArrayList<String>();
		int listCiphers = (alerts.size() + "").length();
		String ciphers = Console.charLine('-', listCiphers);
		String spaces = Console.charLine(' ', listCiphers);
		lines.add(" ID" + spaces + "| DATO         | START | SLUTT | NAVN ");
		lines.add("---" + ciphers
				+ "+---------------------------------------------------");
		return lines;
	}

	@Override
	public String getQuery() {
		return " Velg ID for å gå til avtalen (eller se hele meldingen??), A for forrige eller D for neste. Trykk Enter for å avslutte >";
	}
	
	@Override
	public void giveInput(String input, ViewStack viewStack) {
		super.giveInput(input, viewStack);
		this.done = true;
	}


}
