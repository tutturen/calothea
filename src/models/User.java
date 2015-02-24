package models;

import java.util.ArrayList;

public class User {
	String email, username, passord, navn;
	boolean loggedIn;
	Kalender personligKalender;
	ArrayList<Gruppe> medlemAv; //Ikke i klassediagrammet, men knyttet til metoden
	
	public void setData(String mail, String userName, String passord, String navn, boolean loggedIn, Kalender personligKalender, ArrayList<Gruppe> medlemAV){
		if(checkMail(mail)){
			this.email = mail;
		}
		
		if(checkUserName(userName)){
			this.username = userName; 
		}
		
		if(checkName(navn)){
			this.navn = navn;
		}
		
		this.loggedIn = loggedIn;
		this.personligKalender = personligKalender;
		this.medlemAv = medlemAV;
		
	}
	
	boolean checkMail(String mail){
		
		
	}
	
	boolean checkUserName(String userName){
		
	}
	
	boolean checkName(String navn){
		
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
		return gruppe.kalender;
	}
	
	public Kalender getEgenKalender(){
		return personligKalender;
	}

	
}
