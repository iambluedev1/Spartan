package fr.iambluedev.spartan.api.cache;

import java.util.Map;

import fr.iambluedev.spartan.api.gamemode.SpartanGameMode;

public abstract class SpartanCache {

	public abstract Map<String, SpartanGameMode> getGameModes();
	
	public abstract SpartanGameCache getCache(String name);
	
	public abstract void addGameMode(String name, SpartanGameMode cache);
	
	public abstract void clearCache();
}
