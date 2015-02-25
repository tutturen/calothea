package models;

import java.util.ArrayList;

public class Kalender {
	
	ArrayList<Avtale> avtaleListe;
	String navn;
	int kalenderID;
	
	
	//Lager ikke konstruktor fordi objektet vil komme fra server
	
	//I alle disse metodene maa vi oppdatere server 
	public void setNavn(String navn){
		this.navn = navn;
	}
	
	public void setID(int ID){
		this.kalenderID = ID;
	}
	
	public void setAvtaler(ArrayList<Avtale> avtaleliste){
		this.avtaleListe = avtaleliste;
	}
	

}
