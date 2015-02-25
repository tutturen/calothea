package requests;

import models.Aktivitet;
import models.Kalender;
import models.Rom;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ReqService {
	
	// KalenderController
	@POST("/kalender/add")
	public void addAvtaleToKalender(@Field("calendar_id") int calendarId, @Field("avtale_id") int avtaleId);

	@GET("/user/{id}/kalender")
	public Kalender getUserKalender(@Path("user_id") int userId);
	
	// RomController
	@GET("rom/{id}")
	public Rom getRom(@Path("id") int romId);
	
	@GET("/rom/minimum/{minimum}")
	public Rom getFreeRomWithMinimum(@Path("minimum") int mimimumSize);
	
	// AvtaleController
	@POST("/avtale/create")
	public Aktivitet createAktivitet(@Field("owner_id") int ownerId, @Field("name") String name, @Field("start_time") int start, @Field("end_time") int end);
	
	@POST("avtale/{avtale_id}/invite/{person_id}")
	public void inviteToAktivitet(@Path("avtale_id") int avtaleId, @Path("person_id") int userId);
}
