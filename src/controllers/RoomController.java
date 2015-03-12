package controllers;

import java.util.ArrayList;
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
		db.setRom(aktivitet.getId(), rom.getRomNr());
		
	}
	
	
	public void deleteRom(Aktivitet aktivitet){
		aktivitet.setRom(null);
		db.setRom(aktivitet.getId(), 0);
		
	}
	
	//Gir deg ingen informasjon annet enn at du henter et rom. 
	public Rom getRoom(int romId){
		return db.getRoom(romId);
		
		
	}
	

}
