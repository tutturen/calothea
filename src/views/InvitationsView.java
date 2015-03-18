package views;

import java.util.ArrayList;

import controllers.InvitationController;
import models.Invitation;
import models.MainUser;
import utlils.Console;
import utlils.ViewStack;

public class InvitationsView extends BaseView {

	ArrayList<Invitation> invitations;
	private int userId;
	private final static int WIDTH = 80;
	
	public InvitationsView() {
		userId = MainUser.getInstance().getId();
		
		
	}
	
	@Override
	public String getTitle() {
		return "Dine invitasjoner";
	}

	@Override
	public ArrayList<String> getContent() {
		invitations = InvitationController.getNewInvitations(userId);
		ArrayList<String> lines = new ArrayList<String>();
		lines.add(Console.tableHead("Dine invitasjoner", WIDTH));
		lines.add("| NAVN                                                     INVITERT AV         |");
		lines.add(Console.tableRow(WIDTH));
		int max = invitations.size() > 9 ? 9 : invitations.size();
		if (invitations.size() > 0) {			
			for (int i = 1; i <= max; i++) {
				Invitation invitation = invitations.get(i - 1);
				String inv = Console.matchLength(i + ". " + invitation.getActivity().getName(), 55);
				String usr = Console.matchLength(invitation.getUser().getName(), 20);
				lines.add(Console.tableRow(inv + " " + usr, WIDTH));
			}
		} else {
			lines.add(Console.tableRow("Du har ingen invitatsjoner. Grattis.", WIDTH));
		}
		
		lines.add(Console.tableRow(WIDTH));
		return lines;
	}

	@Override
	public String getQuery() {
		return "Velg nummer >";
	}
	
	@Override
	public void giveInput(String input, ViewStack viewStack) {
		super.giveInput(input, viewStack);
		if (input.length() == 0) {
			this.done = true;
			return;
		}
		int nr = input.charAt(0) - '0';
		if (nr > 0 && nr <= invitations.size()) {
			viewStack.push(new AktivitetView(invitations.get(nr - 1).getActivity().getId()));
		}
		
		
	}

}
