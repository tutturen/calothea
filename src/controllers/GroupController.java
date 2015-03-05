package controllers;

import java.util.ArrayList;

import requests.ReqClient;
import requests.ReqService;
import models.Gruppe;
import models.User;

public class GroupController {
	
	private static ReqService db = ReqClient.getInstance().getService();
	
	public static ArrayList<String> getAllGroups(User user){
		return db.getAllGruppe(user.getId());
		
	}
	
	public static Gruppe getGroup(int gruppeId){ //Ligger allerede
		return db.getGruppe(gruppeId);
		
	}
	
	
	public static void addMember(User user){ //Ligger allerede
		
	}
	
	public static void removeMember(User user){ // 
		
	}

}
