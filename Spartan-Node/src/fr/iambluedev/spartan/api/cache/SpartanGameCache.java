package fr.iambluedev.spartan.api.cache;

public class SpartanGameCache {

	private String zipUrl;
	
	public SpartanGameCache(String zipUrl){
		this.zipUrl = zipUrl;
	}

	public String getZipUrl() {
		return this.zipUrl;
	}
	
}
