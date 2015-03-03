package controllers;

import java.util.ArrayList;
import java.util.Date;

import requests.ReqClient;
import requests.ReqService;
import models.Rom;
import models.Aktivitet;




public class RoomController{
	
	ReqService db = ReqClient.getInstance().getService();
	
	
	public ArrayList<Rom> finnRom(int antall, long start, long slutt){
		return db.getFreeRooms(antall, start, slutt); 
		
	}
	
	public void addRom(Aktivitet aktivitet, Rom rom){
		aktivitet.setRom(rom);
		db.setRom(aktivitet., rom.getRomNr())
		
	}
	
	
	public void deleteRom(Aktivitet aktivitet){
		//db.setRoom(aktivitet.getId, null)
		aktivitet.setRom(null);
		
	}
	
	//Gir deg ingen informasjon annet enn at du henter et rom. 
	public Rom getRoom(int romId){
		return db.getRom(romId);
		
		
	}
	

}
