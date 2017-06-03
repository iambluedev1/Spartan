package fr.iambluedev.spartan.api.server;

import java.util.Map;

public abstract class SpartanServers {
	
	public abstract Map<String, SpartanServer> getServers();
	
	public abstract SpartanServer getServer(String name);
	
	public abstract void addServer(String name, SpartanServer server);
	
	public abstract void removeServer(String name);
	
	public abstract void clearServers();
	
}
