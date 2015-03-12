package views;

import interfaces.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Stack;

import controllers.AvtaleController;
import utlils.Console;
import models.Aktivitet;
import models.Invitation;
import models.MainUser;
import models.User;

public class AktivitetView extends BaseView {

	private Aktivitet activity;
	private Boolean userAttending;
	private final static String NOT_ANSWERED    = " IKKE SVART  ";
	private final static String ATTENDING 		= "   DELTAR    ";
	private final static String NOT_ATTENDING 	= " DELTAR IKKE ";
	private final static int WIDTH = 70;
	private int userId;
	private Invitation userInvitation;
	
	public AktivitetView(int aktivitetId) {
		this.activity = AvtaleController.getAktivitet(aktivitetId);
		this.userId = MainUser.getInstance().getId();
		// Get if user is attending
		for (Invitation inv : activity.getInvitations()) {
			if (inv.getUser().getId() == userId) {
				if (inv.isAccepted() == null) {
					userAttending = false;
				} else {
					userAttending = inv.isAccepted();
				}
				userInvitation = inv;
				break;
			}
		}
	}

	@Override
	public String getTitle() {
		return activity.getName();
	}

	@Override
	public ArrayList<String> getContent() {
		int tableWidth = 21;
		ArrayList<String> lines = new ArrayList<String>();
		
		String ansvarlig = "Ansv.:   " + Console.matchLength(activity.getAdmin().toString(),  tableWidth);
		String datoStr = new SimpleDateFormat("dd. MMM").format(activity.getStartDate());
		String dato = "Dato:    " + Console.matchLength(datoStr, tableWidth);
		String timeStartStr = new SimpleDateFormat("HH:mm").format(activity.getStartDate());
		String startTid = "Start:   " + Console.matchLength(timeStartStr, tableWidth);
		String timeSluttStr = new SimpleDateFormat("HH:mm").format(activity.getEndDate());
		String sluttTid = "Slutt:   " + Console.matchLength(timeSluttStr, tableWidth);
		String sted = "Sted:    " + Console.matchLength(activity.getRom().toString(), tableWidth);
		
		
		lines.add("+-------------  INFO  -----------+     +---------- MELDING ----------+");
		lines.add("| " + ansvarlig + " |");
		lines.add("| " + dato + " |");
		lines.add("| " + startTid + " |");
		lines.add("| " + sluttTid + " |");
		lines.add("| " + sted + " |");
		lines.add("+--------------------------------+     +-----------------------------+");

		lines.add(Console.tableHead("DELTAGERE", WIDTH));
		lines.add("| " + Console.matchLength(activity.getAdmin().getName(), WIDTH - 16) + ATTENDING + "|");
		for (Invitation invitation : activity.getInvitations()) {
			String dText;
			if (invitation.isAccepted() == null) {
				dText = NOT_ANSWERED;
			} else {
				dText = invitation.isAccepted() ? ATTENDING : NOT_ATTENDING;				
			}
			lines.add("| " + Console.matchLength(invitation.getUser().getName(), WIDTH - 16) + dText + "|");
		}
		lines.add(Console.tableRow(WIDTH));
		
		// TODO: Endre aktivitet
		
		if (userIsAdmin() || userIsInvited()) {
			lines.add(Console.tableHead("HANDLINGER", WIDTH));
			
			if (userIsInvited()) {
				String att = userAttending ? "Si at du ikke deltar" : "Si at du deltar.";
				lines.add(Console.tableRow("1. " + att, WIDTH));
				
			}
			if (userIsAdmin()) {
				lines.add(Console.tableRow("2. Endre aktivitet", WIDTH));				
			}
			lines.add(Console.tableRow(WIDTH));

		}

		return lines;
	}

	@Override
	public String getQuery() {
		return "Aktivitet >";
	}
	
	private boolean userIsAdmin() {
		return activity.getAdmin().getId() == userId;
	}
	
	private boolean userIsInvited() {
		for (Invitation invite : activity.getInvitations()) {
			if (userId == invite.getUser().getId()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void giveInput(String input, Stack<View> viewStack) {
		super.giveInput(input, viewStack);
		 
		if (userIsInvited() && input.length() == 1 && input.charAt(0) == '1') {
			userAttending = !userAttending;
			AvtaleController.setAttending(activity.getId(), userId, userAttending);
			if (userAttending) {
				userInvitation.accept();
			} else {
				userInvitation.decline();
			}
			return;
		}
		else if (userIsAdmin() && input.length() == 1 && input.charAt(0) == '2') {
			viewStack.push(new ChangeAppointmentView(activity));
			return;
		} else {
			this.done = true;
			return;
		}

	}

}
