package requests;


import java.util.ArrayList;

import helpers.LoginResult;
import models.Aktivitet;
import models.Invitation;
import models.Kalender;
import models.Rom;
import models.User;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import models.Group;

public interface ReqService {
	
	// KalenderController
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
	
	
	@POST("/appointment/{appointment_id}/rom/{rom_id}")
	public void setRoom(@Path("appointment_id") int appointmentId, @Path("room_id") int romId);
	
	@GET("/user/{user_id}/appointments")
	public ArrayList<Aktivitet> getAlleBrukerAktiviteter(@Path("user_id") int userId);
	
	// GruppeController
	
	@GET("/user/{user_id}/groups")
	public ArrayList<Group> getAllGrupper(@Path("user_id") int UserId);
	
	@GET("/group/{group_id}")
	public Group getGroup(@Path("group_id") int groupId);
	
	@POST("/gruppe/create")
	public Group createGruppe(@Field("calendar_id") int calendarId, @Field("first_member_Id") int userId, @Field("name") String gruppeNavn);
	
	@POST("/gruppe/{gruppe_id}/invite/{person_id}")
	public void inviteToGruppe(@Path("gruppe_id") int gruppeId, @Path("person_id") int userId);

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
