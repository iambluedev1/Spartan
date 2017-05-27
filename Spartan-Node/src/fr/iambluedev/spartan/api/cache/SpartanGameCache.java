package fr.iambluedev.spartan.api.cache;

public class SpartanGameCache {

	private String zipUrl;
	private String name;
	
	public SpartanGameCache(String zipUrl, String name){
		this.zipUrl = zipUrl;
		this.name = name;
	}

	public String getZipUrl() {
		return this.zipUrl;
	}

	public String getName() {
		return this.name;
	}
}
