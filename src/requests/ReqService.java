package requests;


import java.util.ArrayList;

import helpers.LoginResult;
import helpers.Result;
import models.Aktivitet;
import models.Invitation;
import models.Kalender;
import models.Rom;
import models.User;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import models.Group;

public interface ReqService {
	
	// KalenderController
	@FormUrlEncoded
	@POST("/calendar/appointment/add")
	public void addActivityToCalendar(@Field("calendar_id") int kalenderId, @Field("avtale_id") int avtaleId);

	@GET("/user/{id}/kalender")
	public Kalender getUserKalender(@Path("id") int userId);
	
	// RomController
	@GET("/room/{id}")
	public Rom getRoom(@Path("id") int roomId);
	
	@GET("/room/find")
	public ArrayList<Rom> getFreeRooms(@Query("antall") int antall, @Query("start") long start, @Query("end") long end );
	
	// AvtaleController
	
	@FormUrlEncoded
	@POST("/appointment/attend")
	public Invitation setAttending(@Field("appointment_id") int appointmentId, @Field("user_id") int userId, @Field("attending") int isAttending);
	
	@GET("/appointment/{id}")
	public Aktivitet getActivity(@Path("id") int activityId);
	
	@FormUrlEncoded
	@POST("/appointment/create")
	public Aktivitet createActivity(@Field("owner_id") int ownerId, @Field("name") String name, @Field("message") String message, @Field("location") String location, @Field("start_time") long start, @Field("end_time") long end);
	
	@FormUrlEncoded
	@POST("/appointment/invite")
	public Invitation inviteUserToActivity(@Field("appointment_id") int activityId, @Field("user_id") int userId);
	
	@FormUrlEncoded
	@POST("/appointment/kick")
	public Invitation kickUserFromActivity(@Field("appointment_id") int activityId, @Field("user_id") int userId);

	@FormUrlEncoded
	@PUT("/appointment/editstart")
	public Aktivitet setStartTime(@Field("appointment_id") int activityId, @Field("start") long startTime);
	
	@FormUrlEncoded
	@PUT("/appointment/editend")
	public Aktivitet setEndTime(@Field("appointment_id") int activityId, @Field("end") long endTime);
	
	@FormUrlEncoded
	@PUT("/appointment/editlocation")
	public Aktivitet setLocation(@Field("appointment_id") int appointmentId, @Field("user_id") int userId, @Field("location") String location);
	

	@PUT("/appointment/editmessage")
	public Aktivitet setMessage(@Field("appointment_id") int appointmentId, @Field("user_id") int userId, @Field("message") String message);

	
	@POST("/appointment/{appointment_id}/rom/{rom_id}")
	public void setRoom(@Path("appointment_id") int appointmentId, @Path("room_id") int romId);
	
	@GET("/user/{user_id}/appointments")
	public ArrayList<Aktivitet> getAlleBrukerAktiviteter(@Path("user_id") int userId);
	
	// GruppeController
	
	@GET("/user/{user_id}/groups")
	public ArrayList<Group> getAllGrupper(@Path("user_id") int UserId);
	
	@GET("/group/{group_id}")
	public Group getGroup(@Path("group_id") int groupId);
	
	@FormUrlEncoded
	@POST("/group/delete")
	public Result deleteGroup(@Field("group_id") int group_id);
	
	@FormUrlEncoded
	@POST("/group/create")
	public Group createGroup(@Field("masterGroupId") int masterGroupID, @Field("groupName") String groupName);
	
	@FormUrlEncoded
	@POST("/group/add_member")
	public Result addToGroup(@Field("group_id") int group_id, @Field("user_id") int user_id);
	
	@FormUrlEncoded
	@POST("/group/remove_member")
	public Result removeFromGroup(@Field("group_id") int group_id, @Field("user_id") int user_id);

	
	@GET("/user/{id}/invitations")
	public ArrayList<Invitation> getNewInvitations(@Path("id") int userId);

	// UserController

	@GET("/user/{id}")
	public User getUser(@Path("id") int userId);
	
	@GET("/users")
	public ArrayList<User> getAllUsers();
	
	// AuthController
	
	@FormUrlEncoded
	@POST("/login")
	public LoginResult login(@Field("email") String email, @Field("password") String password);
	
	@FormUrlEncoded
	@POST("/register")
	public User register(@Field("email") String email, @Field("name") String name, @Field("password") String password, @Field("role") String role);
	
	
	
}
