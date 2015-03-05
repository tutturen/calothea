import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import utlils.Console;
import views.LoginView;
import views.View;
import models.MainUser;
import models.User;

public class MainProgram {

	private Stack<View> views;
	int consoleWidth = 89, consoleHeight = 21;

	public MainProgram() {
		views = new Stack<View>();
	}

	private static String SPLASH = "     _______  _______  ___      _______  _______  __   __  _______  _______    \n"
			+ "    |       ||   _   ||   |    |       ||       ||  | |  ||       ||   _   |   \n"
			+ "    |       ||  |_|  ||   |    |   _   ||_     _||  |_|  ||    ___||  |_|  |   \n"
			+ "    |       ||       ||   |    |  | |  |  |   |  |       ||   |___ |       |   \n"
			+ "    |      _||       ||   |___ |  |_|  |  |   |  |       ||    ___||       |   \n"
			+ "    |     |_ |   _   ||       ||       |  |   |  |   _   ||   |___ |   _   |   \n"
			+ "    |_______||__| |__||_______||_______|  |___|  |__| |__||_______||__| |__|   \n\n\n"
			+ "    Velkommen til Calothea!\n    For å logge inn, skriv inn brukernavn og passord.   \n\n\n";

	private void init() {
		Console.clearConsole();
		Console.print(SPLASH);
	}

	private void showView(View view) {
		User user = MainUser.getInstance();
		Console.clearConsole();
		if (user != null) {
			Console.println(" "
					+ user.getName()
					+ Console.emptySpace(user.getName().length()
							+ user.getEmail().length() + 2, consoleWidth)
					+ user.getEmail() + " ");
		} else {
			Console.println(" Ikke logget inn");
		}

		Console.println(Console.charLine('-', consoleWidth));

		String viewStack = "";
		for (View v : views) {
			viewStack += " » " + v.getTitle();
		}
		Console.println(viewStack);
		Console.println("");

		printContent(view.getContent());
		Console.println("");
		Console.print(view.getQuery() + " ");

	}

	private void printContent(ArrayList<String> content) {
		for (int i = 0; i < consoleHeight - 6; i++) {
			if (i >= content.size()) {
				Console.println("");
			} else {
				Console.println(content.get(i));
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MainProgram program = new MainProgram();
		program.init();
		program.views.add(new LoginView());

		while (!program.views.isEmpty()) {
			View view = program.views.peek();
			if (view.isDone()) {
				program.views.pop();
				view = program.views.peek();
			}
			program.showView(view);
			String res = scanner.nextLine();
			view.giveInput(res, program.views);
		}
		scanner.close();
	}
}
