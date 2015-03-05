package requests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class ReqClient {
	
	private static ReqClient instance = new ReqClient();
	private static final String API_URL = "http://localhost:8888";
	private ReqService service;
	
	public static ReqClient getInstance() {
		return instance;
	}
	
	public ReqClient() {
		 Gson gson = new GsonBuilder()
         .setDateFormat("yyyy-MM-dd HH:mm:ss")
         .excludeFieldsWithoutExposeAnnotation()
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
