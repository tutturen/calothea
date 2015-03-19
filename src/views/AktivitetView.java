package views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import controllers.AvtaleController;
import utlils.Console;
import utlils.ViewStack;
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
	private final static int WIDTH = 80;
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
		this.activity = AvtaleController.getAktivitet(activity.getId());
		
		int leftTableWidth = 26;
		int rightTableWidth = 38;
		ArrayList<String> lines = new ArrayList<String>();
		
		String ansvarlig = "Ansv.:   " + Console.matchLength(activity.getAdmin().toString(),  leftTableWidth);
		String datoStr = new SimpleDateFormat("dd. MMM").format(activity.getStartDate());
		String dato = "Dato:    " + Console.matchLength(datoStr, leftTableWidth);
		String timeStartStr = new SimpleDateFormat("HH:mm").format(activity.getStartDate());
		String startTid = "Start:   " + Console.matchLength(timeStartStr, leftTableWidth);
		String timeSluttStr = new SimpleDateFormat("HH:mm").format(activity.getEndDate());
		String sluttTid = "Slutt:   " + Console.matchLength(timeSluttStr, leftTableWidth);
		String sted = "Sted:    " + Console.matchLength(activity.getLocation(), leftTableWidth);
		String rom = "";
		if (activity.getRom() != null) {
			rom = "Rom:     " + Console.matchLength(activity.getRom().getSmallName(), leftTableWidth);			
		}
		
		ArrayList<String> messageLines = Console.fitInBox(activity.getMessage(), 35, 6);
		
		lines.add("+--------------  INFO  ---------------+   " + Console.tableHead("MELDING", rightTableWidth));
		lines.add("| "   + ansvarlig +                 " |   " + Console.tableRow(messageLines.get(0), rightTableWidth));
		lines.add("| "   + dato +                      " |   " + Console.tableRow(messageLines.get(1), rightTableWidth));
		lines.add("| "   + startTid +                  " |   " + Console.tableRow(messageLines.get(2), rightTableWidth));
		lines.add("| "   + sluttTid +                  " |   " + Console.tableRow(messageLines.get(3), rightTableWidth));
		lines.add("| "   + sted +                      " |   " + Console.tableRow(messageLines.get(4), rightTableWidth));
		if (activity.getRom() != null) {
			lines.add("| "   + rom +                       " |   " + Console.tableRow(messageLines.get(5), rightTableWidth));			
		}
		lines.add("+-------------------------------------+   " + Console.tableRow(rightTableWidth));
 
		lines.add(Console.tableHead("NOEN DELTAGERE", WIDTH));
		lines.add("| " + Console.matchLength(activity.getAdmin().getName(), WIDTH - 16) + ATTENDING + "|");
		int max = activity.getInvitations().size() > 3 ? 3 : activity.getInvitations().size();
		for (int i = 0; i < max; i++) {
			Invitation invitation = activity.getInvitations().get(i);
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
				String att = "";
				if(userAttending == null){
					att = "Si at du deltar";
				}
				else{
					att = userAttending ? "Si at du ikke deltar" : "Si at du deltar.";
					
				}
				lines.add(Console.tableRow("1. " + att, WIDTH));
				
			}
			lines.add(Console.tableRow("2. Se alle deltagere", WIDTH));
			if (userIsAdmin()) {
				lines.add(Console.tableRow("3. Endre aktivitet", WIDTH));				
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
	public void giveInput(String input, ViewStack viewStack) {
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
		else if (userIsAdmin() && input.length() == 1 && input.charAt(0) == '3') {
			viewStack.push(new ChangeAppointmentView(activity.getId()));
			return;
		} else if (input.length() == 1 && input.charAt(0) == '2') {
			ArrayList<User> members = new ArrayList<User>();
			for (Invitation inv : activity.getInvitations()) {
				members.add(inv.getUser());
			}
			viewStack.push(new SelectView<User>("Alle deltagere", members));
		} else {
			this.done = true;
			return;
		}

	}

}
