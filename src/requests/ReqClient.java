package requests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class ReqClient {
	
	private static ReqClient instance = new ReqClient();
	private static final String API_URL = "http://188.166.53.47/index.php";
	private ReqService service;
	
	public static ReqClient getInstance() {
		return instance;
	}
	
	public ReqClient() {
		 Gson gson = new GsonBuilder()
         .setDateFormat("yyyy-MM-dd HH:mm:ss")
         .create();
		
		RestAdapter restAdapter = new RestAdapter
				.Builder()
				.setEndpoint(API_URL)
				.setConverter(new GsonConverter(gson))
				.build();
		service = restAdapter.create(ReqService.class);
	}
	
	public ReqService getService() {
		return service;
	}
}
