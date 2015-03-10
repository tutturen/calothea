import interfaces.View;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import utlils.Console;
import views.LoginView;
import models.MainUser;
import models.User;

public class MainProgram {

	private Stack<View> views;

	public MainProgram() {
		views = new Stack<View>();
	}

	private void init() {
		Console.clearConsole();
	}

	private void showView(View view) {
		User user = MainUser.getInstance();
		Console.clearConsole();
		if (user != null) {
			Console.println(" "
					+ user.getName()
					+ Console.emptySpace(user.getName().length()
							+ user.getEmail().length() + 2, Console.WIDTH)
					+ user.getEmail() + " ");
		} else {
			Console.println(" Ikke logget inn");
		}

		Console.println(Console.charLine('-', Console.WIDTH));

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
		for (int i = 0; i < Console.HEIGHT - 6; i++) {
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
