package controllers;

import java.util.ArrayList;
import requests.ReqClient;
import requests.ReqService;
import models.Rom;
import models.Aktivitet;

public class RoomController{
	
	static ReqService db = ReqClient.getInstance().getService();
	
	
	public static ArrayList<Rom> finnRom(int antall, int id){
		return db.getFreeRooms(antall, id);
	}
	
	public void addRom(Aktivitet aktivitet, Rom rom){
		aktivitet.setRom(rom);
		db.setRoom(aktivitet.getId(), rom.getRomNr());
		
	}
	
	
	public void deleteRom(Aktivitet aktivitet){
		aktivitet.setRom(null);
		db.setRoom(aktivitet.getId(), 0);
		
	}
	
	//Gir deg ingen informasjon annet enn at du henter et rom. 
	public Rom getRoom(int romId){
		return db.getRoom(romId);
		
		
	}
	

}
