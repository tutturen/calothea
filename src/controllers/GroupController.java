package controllers;

import java.util.ArrayList;

import requests.ReqClient;
import requests.ReqService;
import models.Group;
import models.User;

public class GroupController {

	private static ReqService db = ReqClient.getInstance().getService();

	public static ArrayList<Group> getAllGroups(User user) {
		return db.getAllGrupper(user.getId());

	}

	public static Group getGroup(int groupId) { // Ligger allerede
		return db.getGroup(groupId);

	}

	public static void addMember(int group_id, int user_id) { // Ligger allerede
		db.addToGroup(group_id, user_id);
	}

	public static void removeMember(int group_id, int user_id) { 
		db.removeFromGroup(group_id, user_id);

	}

	public static Group createGroup(String groupName, int masterGroupId) {
		return db.createGroup(masterGroupId, groupName);

	}

}
