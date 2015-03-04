package requests;


import java.util.ArrayList;
import helpers.LoginResult;
import models.Aktivitet;
import models.Kalender;
import models.Rom;
import models.User;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
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
	
	@GET("/rom/find")
	public ArrayList<Rom> getFreeRooms(@Query("antall") int antall, @Query("start") long start, @Query("end") long end );
	
	
	// AvtaleController
	@GET("aktivitet/{id}")
	public Aktivitet getAktivitet(@Path("id") int aktivitetId);
	
	@POST("/avtale/create")
	public Aktivitet createAktivitet(@Field("owner_id") int ownerId, @Field("name") String name, @Field("start_time") long start, @Field("end_time") long end);
	
	@POST("avtale/{avtale_id}/invite/{person_id}")
	public void inviteToAktivitet(@Path("avtale_id") int avtaleId, @Path("person_id") int userId);
	
	@POST("avtale/{avtale_id}/rom/{rom_id}")
	public void setRom(@Path("avtale_id") int avtaleId, @Path("rom_id") int romId);
	
	//Do we need an acceot appointment 
	
	// InnvitasjonsCoontroller
	
	// GruppeController
	
	@POST("/gruppe/create")
	public Gruppe createGruppe(@Field("calendar_id") int calendarId, @Field("first_member_Id") int userId, @Field("name") String gruppeNavn);
	
	@POST("gruppe/{gruppe_id}/invite/{person_id}")
	public void inviteToGruppe(@Path("gruppe_Id") int gruppeId, @Path("person_Id") int userId);
	
	
	// UserController

	@GET("/user/{id}")
	public User getUser(@Path("id") int userId);
	
	// AuthController
	
	@FormUrlEncoded
	@POST("/login")
	public LoginResult login(@Field("email") String email, @Field("password") String password);
	
	@FormUrlEncoded
	@POST("/register")
	public User register(@Field("email") String email, @Field("name") String name, @Field("password") String password, @Field("role") String role);
	
	
	
}
