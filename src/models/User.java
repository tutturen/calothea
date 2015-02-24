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
		
		if(checkUserName(userName)){
			this.username = userName; 
		}
		
		if(checkName(navn)){
			this.navn = navn;
		}
		
		this.personligKalender = new Kalender();
		this.medlemAv = new ArrayList<Gruppe>();
		
	}
	
	boolean checkMail(String mail){
		return true;
		
		
	}
	
	boolean checkUserName(String userName){
		return true;
		
	}
	
	boolean checkName(String navn){
		return true;
	}
	
	
	
	public String getBrukerNavn(){
		return username;
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
