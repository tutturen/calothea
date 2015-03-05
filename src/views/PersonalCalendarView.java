package views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Stack;

import utlils.Console;
import controllers.AvtaleController;
import models.Aktivitet;
import models.Rom;
import models.User;

public class PersonalCalendarView extends CalendarView {

	private String title;
	private ArrayList<Aktivitet> aktiviteter;
	private boolean done = false;
	private int index;

	@SuppressWarnings("unchecked")
	public PersonalCalendarView(User user) {
		this.title = user.getName() + " sin kalender";
		User owner = new User(12, "jon@gmail.com", "Jon", "Systemutvikler");
		Date startDate = new Date(System.currentTimeMillis() + 5000L);
		Date middleDate = new Date(System.currentTimeMillis() + 1000000L);
		Date lateDate = new Date(System.currentTimeMillis() + 20000000L);
		aktiviteter = AvtaleController.getAktiviteter(user.getId(),
				startDate.getTime(), middleDate.getTime());
		Aktivitet firstAktivitet = new Aktivitet(owner, "MONGO aktivitet", startDate, middleDate);
		firstAktivitet.setRom(new Rom("Gobo", 424, 12));
		aktiviteter.add(firstAktivitet);
		for (int i = 0; i < 20; i++) {
			aktiviteter.add(new Aktivitet(owner, "Kul aktivitet", startDate,
					middleDate));
			aktiviteter.add(new Aktivitet(owner, "Morsom aktivitet",
					middleDate, lateDate));
		}
		Collections.sort(aktiviteter);
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public ArrayList<String> getContent() {
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
			line += aktiviteter.get(i).getNavn();
			lines.add(line);

			if ((i - index) > 10) {
				break;
			}
		}
		return lines;
	}

	public ArrayList<View> getChildViews() {
		ArrayList<View> childViews = new ArrayList<View>();
		for (Aktivitet aktivitet : aktiviteter) {
			childViews.add(new AktivitetView(aktivitet));
		}
		return childViews;
	}

	@Override
	public void giveInput(String input, Stack<View> viewStack) {
		if (input.length() == 0) {
			return;
		}
		try {
			int id = Integer.parseInt(input);
			if (id > 0 && id < aktiviteter.size() + 1) {
				Aktivitet aktivitet = aktiviteter.get(id - 1);
				viewStack.push(new AktivitetView(aktivitet));
			}

		} catch (Exception e) {
			char c = input.toLowerCase().charAt(0);
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

	@Override
	public boolean isDone() {
		return done;
	}

	@Override
	public void setUnDone() {
		this.done = false;

	}

}
