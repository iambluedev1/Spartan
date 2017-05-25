package fr.iambluedev.spartan.api.cache;

import java.util.Map;

public abstract class SpartanCache {

	public abstract Map<String, SpartanGameCache> getGameModes();
	
	public abstract SpartanGameCache getCache(String name);
	
	public abstract void addGameMode(String name, SpartanGameCache cache);
	
	public abstract void clearCache();
}
