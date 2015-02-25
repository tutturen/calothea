package models;

import java.util.ArrayList;

public class User {
	String email, username, passord, navn;
	
	Kalender personligKalender;
	ArrayList<Gruppe> medlemAv; //Ikke i klassediagrammet, men knyttet til metoden
	
	
	
	public User(String mail, String userName, String passord, String navn){
		
		if(checkMail(mail)){
			this.email = mail;
			
		}
		if(checkName(navn)){
			this.navn = navn;
		
		
		}
		
		this.passord = passord;
		this.personligKalender = new Kalender();
		this.medlemAv = new ArrayList<Gruppe>();
		this.username = userName;
		
		
	}
		
		
	boolean checkMail(String mail){
	
		
		if(!(mail.contains("@"))) {
			throw new IllegalArgumentException();
		}
		String[] oppdeltMail = mail.split("@");
		String etterAlphakroll = oppdeltMail[1];

		//Tester om det eksisterer punktum etter @
		String[] oppdeltDomene = etterAlphakroll.split("\\.");
		
		if(oppdeltDomene.length != 2) {
			throw new IllegalArgumentException("E-posten har ikke punktum og domene");

		}
		return true;
			

	}
	
	
	boolean checkName(String navn){
		if(!(navn.length() > 1)){
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < navn.length(); i++) {
			
			if((!(Character.isLetter(navn.charAt(i)))) && navn.charAt(i) != ' '){
				throw new IllegalArgumentException();
			}
			
		}
		return true;
		
	}
	
	
	
	public String getBrukerNavn(){
		return username;
	}
	
	public String getEmail(){
		return email;
	}
	
	public ArrayList<Gruppe> getGrupper(){
		return medlemAv;
		
	}
	
	public String getNavn(){
		return navn;
	}
	
	public Kalender getGruppeKalender(Gruppe gruppe){
		return gruppe.gruppeKalender;
	}
	
	public Kalender getEgenKalender(){
		return personligKalender;
	}
	
	
	
}




