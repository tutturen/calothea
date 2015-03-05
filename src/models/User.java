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
		
		if (checkMail(mail)) {
			this.email = mail;

		}
		if (checkName(name)) {
			this.name = name;
		}
		if (checkRole(role)){
			this.rolle = role;
		}

		this.medlemAv = new ArrayList<Gruppe>();

	}

	boolean checkMail(String mail) {

		if (!(mail.contains("@"))) {
			throw new IllegalArgumentException();
		}
		String[] oppdeltMail = mail.split("@");
		String etterAlphakroll = oppdeltMail[1];

		// Tester om det eksisterer punktum etter @
		String[] oppdeltDomene = etterAlphakroll.split("\\.");

		if (oppdeltDomene.length != 2) {
			throw new IllegalArgumentException(
					"E-posten har ikke punktum og domene");

		}
		return true;

	}
	
	private boolean isValidId(int id) {
		return id > 0;
	}
	
	private boolean checkRole(String role) {
		if (role.length() < 3) {
			throw new IllegalArgumentException("Role must consist of 3 or more characters");
		}
		for (int i = 0; i > role.length(); i++) {
			char c = role.charAt(i);
			if (Character.isLetter(c)) {
				throw new IllegalArgumentException("Role can not contain numbers");
			}
		}
		return true;
	}

	boolean checkName(String navn) {
		if (!(navn.length() > 1)) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < navn.length(); i++) {

			if ((!(Character.isLetter(navn.charAt(i))))
					&& navn.charAt(i) != ' ') {
				throw new IllegalArgumentException();
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

}
