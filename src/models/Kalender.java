package models;

import java.util.ArrayList;
import java.util.Date;

public class Kalender {
	
	ArrayList<Avtale> avtaleListe = new ArrayList<Avtale>();
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
	
	//Returnerer innenfor gitte tider
	public ArrayList<Avtale> getAvtaler(Date start, Date end){
		ArrayList<Avtale> soktAvtale = new ArrayList<Avtale>();
		for (int i = 0; i < avtaleListe.size(); i++) {
			Avtale mulig = avtaleListe.get(i);
			if(mulig.getStart().after(start) && mulig.getSlutt().before(end)){
				soktAvtale.add(mulig);
				
			}
			
			
		}return soktAvtale;
		
	}
	public void leggTilAvtale(Avtale avtale){
		avtaleListe.add(avtale);
	}
	
	//Skal kun vaere mulig for avtale-eier
	public void fjernAvtale(Avtale avtale){
		avtaleListe.remove(avtale);
		
	}
	

}
