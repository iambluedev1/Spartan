package fr.iambluedev.spartan.node.managers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import fr.iambluedev.spartan.api.cache.SpartanCache;
import fr.iambluedev.spartan.api.cache.SpartanGameCache;

public class CacheManager extends SpartanCache{

	private Map<String, SpartanGameCache> gamemodes;
	private File folder;
	
	public CacheManager(){
		this.gamemodes = new HashMap<String, SpartanGameCache>();
		this.folder = new File("cache");
	}
	
	@Override
	public Map<String, SpartanGameCache> getGameModes() {
		return this.gamemodes;
	}

	@Override
	public void clearCache() {
		this.gamemodes.clear();
	}

	@Override
	public SpartanGameCache getCache(String name) {
		if(this.gamemodes.containsKey(name)){
			return this.gamemodes.get(name);
		}else{
			return null;
		}
	}

	@Override
	public void addGameMode(String name, SpartanGameCache cache) {
		if(!this.gamemodes.containsKey(name)){
			this.gamemodes.put(name, cache);
		}
	}

	public File getFolder() {
		return this.folder;
	}
	
}
