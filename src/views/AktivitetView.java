package views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Stack;

import controllers.AvtaleController;
import utlils.Console;
import models.Aktivitet;

public class AktivitetView implements View {

	private Aktivitet aktivitet;
	private boolean done = false;
	private String deltar = "  DELTAR   ";
	private String deltarIkke = "DELTAR IKKE";

	public AktivitetView(int aktivitetId) {
		this.aktivitet = AvtaleController.getAktivitet(aktivitetId);
	}

	@Override
	public String getTitle() {
		return aktivitet.getNavn();
	}

	@Override
	public ArrayList<String> getContent() {
		int tableWidth = 21;
		ArrayList<String> lines = new ArrayList<String>();
		
		String ansvarlig = "Ansv.:   " + Console.matchLength(aktivitet.getAdmin().toString(),  tableWidth);
		String datoStr = new SimpleDateFormat("dd. MMM").format(aktivitet.getStartDate());
		String dato = "Dato:    " + Console.matchLength(datoStr, tableWidth);
		String timeStartStr = new SimpleDateFormat("HH:mm").format(aktivitet.getStartDate());
		String startTid = "Start:   " + Console.matchLength(timeStartStr, tableWidth);
		String timeSluttStr = new SimpleDateFormat("HH:mm").format(aktivitet.getEndDate());
		String sluttTid = "Slutt:   " + Console.matchLength(timeSluttStr, tableWidth);
		String sted = "Sted:    " + Console.matchLength(aktivitet.getRom().toString(), tableWidth);
		
		
		lines.add("+-------------  INFO  -----------+     +---------- MELDING ----------+");
		lines.add("| " + ansvarlig + " |");
		lines.add("| " + dato + " |");
		lines.add("| " + startTid + " |");
		lines.add("| " + sluttTid + " |");
		lines.add("| " + sted + " |");
		/*lines.add("| Ansv.:    Thea Ullebust      |     | Det blir anledning til å    |");
		lines.add("| Dato:     27. Mar            |     | ake så mye dere vil.        |");
		lines.add("| Start:    12:25              |     | Ha en fin dag.              |");
		lines.add("| Slutt:    12:25              |     |                             |");
		lines.add("| Sted:     ROM 401, P15       |     |                             |");*/
		lines.add("+--------------------------------+     +-----------------------------+");

		lines.add("+------------------------- DELTAGERE -------------------------+");
		lines.add("| Thor Even Tutturen                                DELTAR    |");
		lines.add("| Per Oskar Isdahl                                  DELTAR    |");
		lines.add("| Sjur Waagbø                                       DELTAR    |");
		lines.add("| Simen Hellem                                      DELTAR    |");
		lines.add("| Ahmed                                             DELTAR    |");
		lines.add("+-------------------------------------------------------------+");

		return lines;
	}

	@Override
	public String getQuery() {
		return "Aktivitet >";
	}

	@Override
	public boolean isDone() {
		return done;
	}

	@Override
	public void giveInput(String input, Stack<View> viewStack) {
		//done = true;

	}

	@Override
	public void setUnDone() {
		this.done = false;
	}

}
