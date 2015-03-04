package requests;

import retrofit.RestAdapter;

public class ReqClient {
	
	private static ReqClient instance = new ReqClient();
	private static final String API_URL = "http://localhost:8888";
	private ReqService service;
	
	public static ReqClient getInstance() {
		return instance;
	}
	
	public ReqClient() {
		RestAdapter restAdapter = new RestAdapter
				.Builder()
				.setEndpoint(API_URL)
				.build();
		service = restAdapter.create(ReqService.class);
	}
	
	public ReqService getService() {
		return service;
	}
}
