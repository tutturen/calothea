package views;

import java.util.ArrayList;

import models.Alert;
import models.MainUser;
import controllers.AlertController;
import utlils.Console;
import utlils.ViewStack;

public class AlertView extends BaseView {
	
	private int userId;
	private ArrayList<Alert> alerts;
	private final static int WIDTH = 80;
	
	public AlertView() {
		userId = MainUser.getInstance().getId();
	}

	@Override
	public String getTitle() {
		return "Dine varsler";
	}

	@Override
	public ArrayList<String> getContent() {
		alerts = AlertController.getAlerts(userId);
		ArrayList<String> lines = new ArrayList<String>();
		lines.add(Console.tableHead("Dine varsler", WIDTH));
		lines.add("| AVTALENAVN                                                 MELDING           |");
		lines.add(Console.tableRow(WIDTH));
		int max = alerts.size() > 9 ? 9 : alerts.size();
		if (alerts.size() > 0) {			
			for (int i = 1; i <= max; i++) {
				Alert alert = alerts.get(i - 1);
				String name = Console.matchLength(i + ". " + alert.getActivity().getName(), 55);
				String msg = Console.matchLength(alert.getMessage(), 20);
				lines.add(Console.tableRow(name + " " + msg, WIDTH));
			}
		} else {
			lines.add(Console.tableRow("Du har ingen varsler. Grattis.", WIDTH));
		}
		
		lines.add(Console.tableRow(WIDTH));
		return lines;
	}

	@Override
	public String getQuery() {
		return " Velg ID for å gå til avtalen. Trykk Enter for å avslutte >";
	}
	
	
	@Override
	public void giveInput(String input, ViewStack viewStack) {
		super.giveInput(input, viewStack);
		if (input.length() == 0) {
			this.done = true;
			return;
		}
		int nr = input.charAt(0) - '0';
		if (nr > 0 && nr <= alerts.size()) {
			viewStack.push(new AktivitetView(alerts.get(nr - 1).getActivity().getId()));
		}
		
		
	}


}
