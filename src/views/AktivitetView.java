package views;

import interfaces.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Stack;

import controllers.AvtaleController;
import utlils.Console;
import models.Aktivitet;
import models.Invitation;
import models.User;

public class AktivitetView extends BaseView {

	private Aktivitet aktivitet;
	private final static String ATTENDING 		= "   DELTAR    ";
	private final static String NOT_ATTENDING 	= " DELTAR IKKE ";

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
		lines.add("+--------------------------------+     +-----------------------------+");

		lines.add("+------------------------- DELTAGERE -------------------------+");
		lines.add("| " + Console.matchLength(aktivitet.getAdmin().getName(), 47) + ATTENDING + "|");
		for (Invitation invitation : aktivitet.getInvitations()) {
			String dText = invitation.isAccepted() ? ATTENDING : NOT_ATTENDING;
			lines.add("| " + Console.matchLength(invitation.getUser().getName(), 47) + dText + "|");
		}
		lines.add("+-------------------------------------------------------------+");

		return lines;
	}

	@Override
	public String getQuery() {
		return "Aktivitet >";
	}

	@Override
	public void giveInput(String input, Stack<View> viewStack) {
		super.giveInput(input, viewStack);
		//done = true;

	}

}
