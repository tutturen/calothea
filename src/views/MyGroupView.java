package views;

import java.util.ArrayList;
import utlils.Console;
import utlils.ViewStack;
import controllers.GroupController;
import models.Group;
import models.MainUser;

public class MyGroupView extends BaseView {
	private ArrayList<Group> grupper;
	private int index;
	private SelectView<Group> sw;

	public MyGroupView() {
		this.done = false;
		sw = new SelectView<Group>("Velg gruppe du vil slette", GroupController.getAllGroups(MainUser.getInstance()));

	}

	@Override
	public String getTitle() {
		return "Dine grupper";
	}

	@Override
	public ArrayList<String> getContent() {
		if(sw.isDone()){
			GroupController.deleteGroup(sw.getSelected().getId());
			sw = new SelectView<Group>("Velg gruppe du vil slette", GroupController.getAllGroups(MainUser.getInstance()));
			
		}
		grupper = GroupController.getAllGroups(MainUser.getInstance());
		ArrayList<String> content = new ArrayList<String>();
		content.add(Console.tableRow(70));
		content.add("| ID | NAVN                     | MASTERGRUPPE                       |");
		content.add("+----+--------------------------+------------------------------------+");
		
		int teller = index;
		for (int i = index ; i< index+6 ; i++) {
			if(i>= grupper.size()){
				break;
			}
			teller++;
			Group group = grupper.get(i);
			
			String id = Console.matchLength((i+1) + "", 3);
			if(group.getMasterGruppe().getName()== null){
				content.add("| " + id + "| " + Console.matchLength(group.getName() , 25) + Console.tableRow("", 38)); }
			else{
				content.add("| " + id + "| " + Console.matchLength(group.getName() , 25) +Console.tableRow("Master: " + group.getMasterGruppe().getName(), 38));
			}
		}
		content.add(Console.tableRow(70));
		for(int k = teller ; k < index+5 ; k++) {
			content.add("");
		}
		
		content.add("");
		content.add(Console.tableHead("Handlinger", 70));
		content.add(Console.tableRow("1. Velg gruppe ved å velge tilhørende tall", 70));
		content.add(Console.tableRow("2. Opprett gruppe ved å presse '+'", 70));
		content.add(Console.tableRow("3. Fjern gruppe ved å skrive '-'", 70));
		content.add(Console.tableRow("4. Press enter for å gå tilbake", 70));
		content.add(Console.tableRow("5. Bla til venstre ved å presse 'a' og bla til høyre ved å presse 'd'", 70));
		content.add(Console.tableRow(70));
		return content;
		}


	@Override
	public String getQuery() {
		return "Velg hva du vil gjøre > ";
	}

	@Override

	public void giveInput(String input, ViewStack viewStack) {
		if (input.length() == 0) {

			this.done = true;
			return;
		}

		if(input.equals("a") || input.equals("d")){
			int antallGrupperPrSide = 6;
			char c = input.toLowerCase().charAt(0);
			if (c == 'a') {
				if (index > antallGrupperPrSide) {
					index -= antallGrupperPrSide;
				} else {
					index = 0;
				}
			} else if (c == 'd') {
				if (index > (grupper.size() - antallGrupperPrSide-1)) {
					index = 0;
				} else {
					index += antallGrupperPrSide;
				}
			}
	}


		else if(input.equals("+")){
			viewStack.push(new CreateGroupView());
			return;
		}
		else if(input.equals("-")){
			viewStack.push(sw);
			return;
		}
		

		try {
			System.out.println(input);
			int id = Integer.parseInt(input);
			if (id > 0 && id <= grupper.size()) {
				Group gruppe = grupper.get(id - 1);
				System.out.println(gruppe.getName());
				viewStack.push(new GroupToolMenuView(gruppe));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
