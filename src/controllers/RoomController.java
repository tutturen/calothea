package controllers;

import java.util.ArrayList;
import java.util.Date;

import requests.ReqClient;
import requests.ReqService;
import models.Rom;
import models.Aktivitet;




public class RoomController{
	
	ReqService db = ReqClient.getInstance().getService();
	
	
	public void ArrayList<Rom> finnRom(Aktivitet aktivitet, int antall, Date start, Date slutt){
		//Assumes reqservice returns a list of available rooms. Changed Activity class here, test needs to be updated. 
		return db.getFreeRomWithMinimum(mimimumSize); //This one has to be changed to also have Date as parameter and return a list
		//of options to a user
		
	}
	
	public void addRom(Aktivitet aktivitet, Rom rom){
		aktivitet.setRom(rom);
		// db.setRoom(aktivitet, rom)
	}
	
	public void deleteRom(Aktivitet aktivitet){
		aktivitet.setRom(null);
		//db.setRoom(aktivitet, null)
		
	}
	
	public Rom romController(int romId){
		return db.getRom(romId);
		
		
	}
	

}
