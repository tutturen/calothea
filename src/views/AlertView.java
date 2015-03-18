package views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import models.Alert;
import models.MainUser;
import controllers.AlertController;
import utlils.Console;
import utlils.ViewStack;

public class AlertView extends BaseView {
	
	private int userId;
	private ArrayList<Alert> alerts;
	private int index;
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
		Collections.sort(alerts);
		ArrayList<String> lines = new ArrayList<String>();
		lines.add(Console.tableHead("Dine varsler", WIDTH));
		lines.add("|     DATO           MELDING                                                   |");
		lines.add(Console.tableRow(WIDTH));
		if (alerts.size() > 0) {			
			for (int i = index; i <= alerts.size() - 1; i++) {
				Alert alert = alerts.get(i);
				String id = Console.matchLength(i + 1 + ".", 4);
				String date = Console.matchLength("" + new SimpleDateFormat("dd. MMM HH:mm").format(alert.getUpdated_at()), 14);
				String msg = Console.matchLength(alert.getMessage(), 58);
				lines.add(Console.tableRow(id + date + " " + msg, WIDTH));
				if ((i - index) > 10) {
					break;
				}
			}
		} else {
			lines.add(Console.tableRow("Du har ingen varsler. Grattis.", WIDTH));
		}
		
		lines.add(Console.tableRow(WIDTH));
		return lines;
	}

	@Override
	public String getQuery() {
		return " Velg ID, A for forrige eller D for neste >";
	}
	
	
	@Override
	public void giveInput(String input, ViewStack viewStack) {
		super.giveInput(input, viewStack);
		if (input.length() == 0) {
			this.done = true;
			return;
		}
		
		try {
			int id = Integer.parseInt(input);
			if (id > 0 && id < alerts.size() + 1) {
				viewStack.push(new BigMessageView(alerts.get(id-1).getMessage()));
			}

		} catch (Exception e) {
			Console.print(e.getMessage());
			char c = input.toLowerCase().charAt(0);
			if (c == 'x') {
				this.done = true;
			}
			if (c == 'a') {
				if (index > 12) {
					index -= 12;
				} else {
					index = 0;
				}
			} else if (c == 'd') {
				if (index > (alerts.size() - 12)) {
					index = 0;
				} else {
					index += 12;
				}
			}
		}
	}
}
