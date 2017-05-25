package fr.iambluedev.spartan.api.cache;

public class SpartanGameCache {

	private String jsonUrl;
	
	public SpartanGameCache(String jsonUrl){
		this.jsonUrl = jsonUrl;
	}

	public String getJsonUrl() {
		return this.jsonUrl;
	}
	
}
