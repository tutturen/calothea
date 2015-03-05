package models;

import java.util.ArrayList;

public class User {
	private String email, name, rolle;
	private int id;
	// For aa ikke ha konflikt
	Kalender calendar = null;
	ArrayList<Gruppe> medlemAv; // Ikke i klassediagrammet, men knyttet til
								// metoden

	public User(int userId, String mail, String name, String role) {

		if (!isValidId(userId)) {
			throw new IllegalArgumentException("Illegal ID");
		}
		this.id = userId;
		
		if (!(checkMail(mail))) {
			throw new IllegalArgumentException("Feil i email");

		}
		if (!(checkName(name))) {
			throw new IllegalArgumentException("Feil i navn");
		}
		if (!(checkRole(role))){
			throw new IllegalArgumentException("Feil i Rolle");
		}
		this.email = mail;
		this.name = name;
		this.rolle = role;
		this.medlemAv = new ArrayList<Gruppe>();

	}

	static boolean checkMail(String mail) {

		if (!(mail.contains("@"))) {
			return false;
		}
		String[] oppdeltMail = mail.split("@");
		String etterAlphakroll = oppdeltMail[1];

		// Tester om det eksisterer punktum etter @
		String[] oppdeltDomene = etterAlphakroll.split("\\.");

		if (oppdeltDomene.length != 2) {
			return false;

		}
		return true;

	}
	
	private static boolean isValidId(int id) {
		return id > 0;
	}
	
	private static boolean checkRole(String role) {
		if (role.length() < 3) {
			return false;
		}
		for (int i = 0; i > role.length(); i++) {
			char c = role.charAt(i);
			if (Character.isLetter(c)) {
				return false;
			}
		}
		return true;
	}

	static boolean checkName(String navn) {
		if (!(navn.length() > 1)) {
			return false;
		}
		for (int i = 0; i < navn.length(); i++) {

			if ((!(Character.isLetter(navn.charAt(i))))
					&& navn.charAt(i) != ' ') {
				return false;
			}

		}
		return true;

	}
	
	public int getId() {
		return id;
	}

	public String getRole() {
		return rolle;
	}

	public String getEmail() {
		return email;
	}

	public ArrayList<Gruppe> getGrupper() {
		return medlemAv;

	}

	public String getName() {
		return name;
	}

	public Kalender getGruppeKalender(Gruppe gruppe) {
		return gruppe.getKalender();
	}

	public Kalender getEgenKalender() {
		return calendar;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	public static boolean isValidUser(String name, String role, String email, String password) {
		return (checkMail(email) && checkRole(role) && checkName(name));
	}

}

