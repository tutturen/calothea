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
import models.Group;

public interface ReqService {
	
	// KalenderController
	@POST("/calendar/appointment/add")
	public void addAvtaleToKalender(@Field("calendar_id") int kalenderId, @Field("avtale_id") int avtaleId);

	@GET("/user/{id}/kalender")
	public Kalender getUserKalender(@Path("id") int userId);
	
	// RomController
	@GET("/rom/{id}")
	public Rom getRom(@Path("id") int romId);
	
	@GET("/rom/find")
	public ArrayList<Rom> getFreeRooms(@Query("antall") int antall, @Query("start") long start, @Query("end") long end );
	
	
	// AvtaleController
	@GET("/appointment/{id}")
	public Aktivitet getAktivitet(@Path("id") int aktivitetId);
	
	@POST("/avtale/create")
	public Aktivitet createAktivitet(@Field("owner_id") int ownerId, @Field("name") String name, @Field("message") String message, @Field("location") String location, @Field("start_time") long start, @Field("end_time") long end);
	
	@POST("/avtale/{avtale_id}/invite/{person_id}")
	public void inviteToAktivitet(@Path("avtale_id") int avtaleId, @Path("person_id") int userId);
	
	@POST("/avtale/{avtale_id}/rom/{rom_id}")
	public void setRom(@Path("avtale_id") int avtaleId, @Path("rom_id") int romId);
	
	@GET("/user/{user_id}/appointments")
	public ArrayList<Aktivitet> getAlleBrukerAktiviteter(@Path("user_id") int userId);
	
	//Do we need an acceåt appointment
	
	// InnvitasjonsController
	
	// GruppeController
	
	@GET("/user/{user_id}/groups")
	public ArrayList<Group> getAllGrupper(@Path("user_id") int UserId);
	
	@GET("/group/{group_id}")
	public Group getGroup(@Path("group_id") int groupId);
	
	@POST("/group/create")
	public Group createGroup(@Field("masterGroupId") int masterGroupID, @Field("groupName") String groupName);
	
	@POST("/group/add_member")
	public void addToGroup(@Path("group_id") int group_id, @Path("user_id") int user_id);

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
