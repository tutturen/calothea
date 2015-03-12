package views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import utlils.Console;
import utlils.ViewStack;
import controllers.AvtaleController;
import models.Aktivitet;
import models.User;

public class PersonalCalendarView extends CalendarView {

	private String title;
	private ArrayList<Aktivitet> aktiviteter;
	private int index;
	private User user;

	public PersonalCalendarView(User user) {
		this.user = user;
		this.title = user.getName() + " sin kalender";
		aktiviteter = AvtaleController.getAlleAktiviteter(user.getId());
		//Collections.sort(aktiviteter);
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public ArrayList<String> getContent() {
		aktiviteter = AvtaleController.getAlleAktiviteter(user.getId());
		//Collections.sort(aktiviteter);
		ArrayList<String> lines = new ArrayList<String>();
		int listCiphers = (aktiviteter.size() + "").length();
		String ciphers = Console.charLine('-', listCiphers);
		String spaces = Console.charLine(' ', listCiphers);
		lines.add(" ID" + spaces + "| DATO         | START | SLUTT | NAVN ");
		lines.add("---" + ciphers
				+ "+---------------------------------------------------");
		for (int i = index; i < aktiviteter.size(); i++) {

			// ID
			int numberCiphers = (i + 1 + "").length();
			String uniqueSpace = Console.charLine(' ', listCiphers
					- numberCiphers);
			String line = " " + uniqueSpace + (1 + i) + "  | ";

			// DATE
			Date start = aktiviteter.get(i).getStartDate();
			Date end = aktiviteter.get(i).getEndDate();
			String date = new SimpleDateFormat("EEE dd. MMM").format(start);
			line += date + "  | ";

			// START
			String startTime = new SimpleDateFormat("HH:mm").format(start);
			line += startTime + " | ";

			// SLUTT
			String endTime = new SimpleDateFormat("HH:mm").format(end);
			line += endTime + " | ";

			// NAVN
			line += aktiviteter.get(i).getName();
			lines.add(line);

			if ((i - index) > 10) {
				break;
			}
		}
		return lines;
	}

	@Override
	public void giveInput(String input, ViewStack viewStack) {
		if (input.length() == 0) {
			return;
		}
		try {
			int id = Integer.parseInt(input);
			if (id > 0 && id < aktiviteter.size() + 1) {
				Aktivitet aktivitet = aktiviteter.get(id - 1);
				viewStack.push(new AktivitetView(aktivitet.getId()));
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
				if (index > (aktiviteter.size() - 12)) {
					index = 0;
				} else {
					index += 12;
				}
			}
		}
	}

	@Override
	public String getQuery() {
		return " Velg ID, A for forrige eller D for neste >";
	}

}
