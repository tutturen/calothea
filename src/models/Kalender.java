package models;

import java.util.ArrayList;
import java.util.Date;

public class Kalender {
	
	ArrayList<Aktivitet> AktivitetListe = new ArrayList<Aktivitet>();
	String name;
	int id;
	
	//Kun for test
	
	public Kalender(ArrayList<Aktivitet> aktiviteter, String navn, int ID){
		this.AktivitetListe = aktiviteter;
		this.name = navn;
		this.id = ID;
	}
	
	//Kun for test
	
	public ArrayList<Aktivitet> getAktivitetListe() {
		return AktivitetListe;
	}

	public String getNavn() {
		return name;
	}

	public int getKalenderID() {
		return id;
	}

	//I alle disse metodene maa vi oppdatere server 
	public void setNavn(String navn){
		this.name = navn;
	}
	
	public void setID(int ID){
		this.id = ID;
	}
	
	public void setAktivitetr(ArrayList<Aktivitet> Aktivitetliste){
		this.AktivitetListe = Aktivitetliste;
	}
	
	//Returnerer innenfor gitte tider
	public ArrayList<Aktivitet> getAktiviteter(Date start, Date end){
		ArrayList<Aktivitet> soktAktivitet = new ArrayList<Aktivitet>();
		for (int i = 0; i < AktivitetListe.size(); i++) {
			Aktivitet mulig = AktivitetListe.get(i);
			if(mulig.getStartDate().after(start) && mulig.getEndDate().before(end)){
				soktAktivitet.add(mulig);
				
			}
			
			
		}return soktAktivitet;
		
	}
	public void leggTilAktivitet(Aktivitet Aktivitet){
		AktivitetListe.add(Aktivitet);
	}
	
	//Skal kun vaere mulig for Aktivitet-eier
	public void fjernAktivitet(Aktivitet Aktivitet){
		if(!(AktivitetListe.contains(Aktivitet))){
			throw new IllegalArgumentException();
		}
		AktivitetListe.remove(Aktivitet);
		
	}
	

}
