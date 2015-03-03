package requests;

import java.util.ArrayList;

import models.Aktivitet;
import models.Kalender;
import models.Rom;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import models.Gruppe;

public interface ReqService {
	
	// KalenderController
	@POST("/kalender/add")
	public void addAvtaleToKalender(@Field("calendar_id") int calendarId, @Field("avtale_id") int avtaleId);

	@GET("/user/{id}/kalender")
	public Kalender getUserKalender(@Path("user_id") int userId);
	
	// RomController
	@GET("rom/{id}")
	public Rom getRom(@Path("id") int romId);
	
	//Has to be changed. Needs to include startTime, and endTime. Returns a arrayList with all available rooms
	@GET("/rom/find")
	public ArrayList<Rom> getFreeRooms(@Query("antall") int antall, @Query("start") long start, @Query("end") long end );
	
	
	// AvtaleController
	@GET("aktivitet/{id}")
	public Aktivitet getAktivitet(@Path("id") int aktivitetId);
	
	//This needs to change since room is set seperatly
	@POST("/avtale/create")
	public Aktivitet createAktivitet(@Field("owner_id") int ownerId, @Field("name") String name, @Field("start_time") int start, @Field("end_time") int end);
	
	@POST("avtale/{avtale_id}/invite/{person_id}")
	public void inviteToAktivitet(@Path("avtale_id") int avtaleId, @Path("person_id") int userId);
	
	@POST("avtale/{avtale_id}/rom/{rom_id}")
	public void setRom(@Path("avtale_id") int avtaleId, @Path("rom_id") int romId);
	
	// GruppeController
	
	@POST("/gruppe/create")
	public Gruppe createGruppe(@Field("calendar_id") int calendarId, @Field("first_member_Id") int userId, @Field("name") String gruppeNavn);
	
	@POST("gruppe/{gruppe_id}/invite/{person_id}")
	public void inviteToGruppe(@Path("gruppe_Id") int gruppeId, @Path("person_Id") int userId);
	
	
	// UserController

	
	// AuthController
	
	@POST("/login")
	public void login(@Field("email") String email, @Field("password") String password);
	
	@POST("/register")
	public void register(@Field("email") String email, @Field("username") String username, @Field("name") String name, @Field("password") String password);
	
	
}
