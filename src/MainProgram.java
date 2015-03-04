import java.util.Scanner;

import models.MainUser;
import models.User;
import controllers.UserController;

public class MainProgram {

	private static void print(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		print("Skriv inn din epost:");
		String email = scanner.nextLine();
		print("Skriv inn passord:");
		String password = scanner.nextLine();
		UserController.login(email, password);

		User usr = MainUser.getInstance();
		if (usr != null) {
			print("Navn: " + usr.getName());
			print("E-post: " + usr.getEmail());
			print("Stilling: " + usr.getRole());
			print("Kalender: " + usr.getEgenKalender().getNavn());
			
		}
		scanner.close();
	}
}
