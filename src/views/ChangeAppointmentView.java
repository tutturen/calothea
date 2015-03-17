package views;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import controllers.AvtaleController;
import controllers.GroupController;
import controllers.UserController;
import utlils.Console;
import utlils.ViewStack;
import models.Aktivitet;
import models.Group;
import models.Invitation;
import models.MainUser;
import models.User;

public class ChangeAppointmentView extends BaseView {

	private int status, subStatus = 1;
	private Aktivitet activity;
	private final static int NOTHING_CHOSEN = 0;
	private final static int WIDTH = 70, CHANGE_DATE = 1, CHANGE_START = 2,
			CHANGE_END = 3, CHANGE_LOCATION = 4, CHANGE_MESSAGE = 5,
			ADD_MEMBER = 6, REMOVE_MEMBER = 7, ADD_GROUP = 8, REMOVE_GROUP = 9;
	private final static int DATE_DAY = 1, DATE_MONTH = 2, DATE_YEAR = 3;
	private int day, month, year;
	private String message;

	private SelectView<User> userSelect;
	private SelectView<Group> groupSelect;

	public ChangeAppointmentView(Aktivitet activity) {
		this.activity = activity;
	}

	@Override
	public String getTitle() {
		return "Endre avtale";
	}

	@Override
	public ArrayList<String> getContent() {
		if (userSelect != null && userSelect.isDone()) {
			User selectedUser = userSelect.getSelected();
			if (status == ADD_MEMBER) {
				AvtaleController.inviteUser(activity.getId(), selectedUser.getId());
				message = selectedUser.getName() + " er n�� invitert!";
			} else if (status == REMOVE_MEMBER) {
				AvtaleController.removeUser(activity.getId(),
						selectedUser.getId());
				message = selectedUser.getName() + " er n�� fjernet.";
			}
			resetValues();
		} else if (groupSelect != null && groupSelect.isDone()) {
			Group selectedGroup = groupSelect.getSelected();
			if (status == ADD_GROUP) {
				AvtaleController.inviteGroup(activity.getId(),
						selectedGroup.getId());
				message = "Gruppen " + selectedGroup.getName()
						+ " er n�� invitert!";
			} else if (status == REMOVE_GROUP) {
				AvtaleController.removeGroup(activity.getId(),
						selectedGroup.getId());
				message = "Gruppen " + selectedGroup.getName()
						+ " er n�� fjernet.";
			}
			resetValues();
		}

		ArrayList<String> lines = new ArrayList<String>();
		lines.add(Console.tableHead("VELG HVA DU VIL ENDRE", WIDTH));
		lines.add(Console.tableRow("1. Endre dato", WIDTH));
		lines.add(Console.tableRow("2. Endre starttidspunkt", WIDTH));
		lines.add(Console.tableRow("3. Endre slutttidspunkt", WIDTH));
		lines.add(Console.tableRow("4. Endre sted", WIDTH));
		lines.add(Console.tableRow("5. Endre melding", WIDTH));
		lines.add(Console.tableRow("6. Legg til deltaker", WIDTH));
		lines.add(Console.tableRow("7. Fjern deltaker", WIDTH));
		lines.add(Console.tableRow("8. Legg til gruppe [MANGLER]", WIDTH));
		lines.add(Console.tableRow("9. Fjern gruppe [MANGLER]", WIDTH));
		lines.add(Console.tableRow(WIDTH));
		lines.add("");
		if (message != null && message.length() != 0) {
			lines.add(Console.tableHead("melding", WIDTH));
			lines.add(Console.tableRow(message, WIDTH));
			lines.add(Console.tableRow(WIDTH));
		}
		return lines;
	}

	@Override
	public String getQuery() {
		switch (status) {
		case CHANGE_DATE:
			if (subStatus == DATE_DAY) {
				return "Skriv nummer p�� dag i m��neden >";
			} else if (subStatus == DATE_MONTH) {
				return "Skriv inn nummer p�� m��neden i ��ret >";
			} else if (subStatus == DATE_YEAR) {
				return "Skriv ��r >";
			}
			break;
		case CHANGE_START:
			return "Skriv nytt starttidspunkt (HH:mm) >";
		case CHANGE_END:
			return "Skriv nytt sluttidspunkt (HH:mm) >";
		case CHANGE_LOCATION:
			return "Skriv nytt sted >";
		case CHANGE_MESSAGE:
			return "Skriv ny melding >";
		}
		return "Velg NR fra lista >";
	}

	private boolean isInsideOptions(int nr) {
		return (nr > 0 && nr < 9);
	}

	private boolean isValidMinute(int minute) {
		return minute > -1 && minute < 60;
	}

	private boolean isValidHour(int hour) {
		return hour > -1 && hour < 24;
	}

	private boolean isValidDay(int day) {
		return (day > 0 && day < 32);
	}

	private boolean isValidMonth(int month) {
		return (month > 0 && month < 13);
	}

	private boolean isValidYear(int year) {
		return (year > 2014 && year < 2500);
	}

	private boolean isValidLocation(String location) {
		return location.length() > 1 && location.length() < 40;
	}

	private boolean isValidMessage(String message) {
		return message.length() > -1 && message.length() < 140;
	}

	private void displayInviteMemberView(ViewStack viewStack) {
		ArrayList<User> list = UserController.getAllUsers();
		// Remove already invited users
		for (Invitation inv : activity.getInvitations()) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getId() == inv.getUser().getId()) {
					list.remove(list.get(i));
					i--;
				}
			}
		}
		userSelect = new SelectView<User>("Legg til en bruker", list);
		viewStack.push(userSelect);
	}

	private void displayRemoveMemberView(ViewStack viewStack) {
		ArrayList<Invitation> invs = AvtaleController.getAktivitet(
				activity.getId()).getInvitations();
		ArrayList<User> invitedUsers = new ArrayList<User>();
		for (Invitation inv : invs) {
			if (inv.getUser().getId() != MainUser.getInstance().getId()) {
				invitedUsers.add(inv.getUser());
			}
		}
		userSelect = new SelectView<User>("Fjern bruker", invitedUsers);
		viewStack.push(userSelect);
	}
	
	private void displayInviteGroupView(ViewStack viewStack) {
		ArrayList<Group> glist = GroupController.getAllGroups(MainUser.getInstance());
		groupSelect = new SelectView<Group>("Legg til en gruppe. Hvis du legger til en eksisterende vil ingenting skje", glist);
		viewStack.push(groupSelect);
	}
	
	private void displayRemoveGroupView(ViewStack viewStack) {
		
	}

	private void resetValues() {
		day = 0;
		month = 0;
		year = 0;
		status = NOTHING_CHOSEN;
		subStatus = DATE_DAY;
		userSelect = null;
		groupSelect = null;
	}

	private int[] getTime(String input) {
		try {
			String[] s = input.split(":");
			if (s.length == 2) {
				int hour = Integer.parseInt(s[0]);
				int min = Integer.parseInt(s[1]);
				if (isValidMinute(min) && isValidHour(hour)) {
					int[] arr = new int[2];
					arr[0] = hour;
					arr[1] = min;
					return arr;
				}
			}
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public void giveInput(String input, ViewStack viewStack) {
		super.giveInput(input, viewStack);
		if (status == NOTHING_CHOSEN && input.length() == 0) {
			this.done = true;
			return;
		}

		if (status == NOTHING_CHOSEN && input.length() == 1) {
			int nr = input.charAt(0) - '0';
			if (isInsideOptions(nr)) {
				status = nr;
				if (status == ADD_MEMBER) {
					displayInviteMemberView(viewStack);
					return;
				} else if (status == REMOVE_MEMBER) {
					displayRemoveMemberView(viewStack);
				} else if (status == ADD_GROUP) {
					displayInviteGroupView(viewStack);
				} else if (status == REMOVE_GROUP) {
					displayRemoveGroupView(viewStack);
				}
				message = "";
				return;
			}
		} else if (status == CHANGE_DATE) {
			try {
				int nr = Integer.parseInt(input);
				switch (subStatus) {
				case DATE_DAY:
					if (isValidDay(nr)) {
						day = nr;
						subStatus++;
					}
					return;
				case DATE_MONTH:
					if (isValidMonth(nr)) {
						month = nr;
						subStatus++;
					}
					return;
				case DATE_YEAR:
					if (isValidYear(nr)) {
						year = nr;
						Date start = activity.getStartDate();
						AvtaleController.changeStartTime(activity.getId(), year,
								month, day, start.getHours(), start.getMinutes());
						resetValues();
						message = "Datoen p�� aktiviteten er n�� endret.";
					}
					return;
				}
			} catch (Exception e) {
				// DEBUG
				viewStack.push(new MessageView(e.getMessage()));
				return;
			}
		} else if (status == CHANGE_START) {
			int[] time = getTime(input);
			if (time != null) {
				Date start = activity.getStartDate();
				try {
					AvtaleController.changeStartTime(activity.getId(), start.getYear() + 1900, start.getMonth() + 1, start.getDate(), time[0],
							time[1]);
				} catch (ParseException e) {
					viewStack.push(new MessageView(e.getMessage()));
				}
				message = "Starttidspunktet p�� aktivteten er n�� endret.";
				resetValues();
				return;
			}
		} else if (status == CHANGE_END) {
			int[] time = getTime(input);
			if (time != null) {
				Date end = activity.getStartDate();
				try {
					AvtaleController.changeEndTime(activity.getId(), end.getYear() + 1900, end.getMonth() + 1, end.getDate(), time[0],
							time[1]);
				} catch (ParseException e) {
					viewStack.push(new MessageView(e.getMessage()));
				}
				message = "Sluttidspunktet p�� aktivteten er n�� endret.";
				resetValues();
				return;
			}
		} else if (status == CHANGE_LOCATION) {
			if (isValidLocation(input)) {
				AvtaleController.changeLocation(activity.getId(), MainUser.getInstance().getId(), input);
				resetValues();
				message = "Stedet er endret til " + input;
				return;
			}
		} else if (status == CHANGE_MESSAGE) {
			if (isValidMessage(input)) {
				AvtaleController.changeMessage(activity.getId(), MainUser.getInstance().getId(), input);
				resetValues();
				message = "Meldingen til aktiviteten er n�� endret.";
				return;
			}
		}

	}

}
