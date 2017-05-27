package fr.iambluedev.spartan.api.http;

public class SpartanUrl {

	private String url;
	private String dest;
	private String name;
	
	public SpartanUrl(String url, String name){
		this.url = url;
		this.dest = "";
	}
	
	public SpartanUrl(String url, String dest, String name){
		this.url = url;
		this.dest = dest;
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public String getDest() {
		return this.dest;
	}

	public String getName() {
		return this.name;
	}
}
