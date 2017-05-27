package fr.iambluedev.spartan.node.managers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import fr.iambluedev.spartan.api.cache.SpartanCache;
import fr.iambluedev.spartan.api.cache.SpartanGameCache;
import fr.iambluedev.spartan.api.gamemode.SpartanGameMode;

public class CacheManager extends SpartanCache{

	private Map<String, SpartanGameMode> gamemodes;
	private File folder;
	
	public CacheManager(){
		this.gamemodes = new HashMap<String, SpartanGameMode>();
		this.folder = new File("cache");
		if(this.folder.exists()){
			this.folder.delete();
		}
		
		this.folder.mkdir();
	}
	
	@Override
	public Map<String, SpartanGameMode> getGameModes() {
		return this.gamemodes;
	}

	@Override
	public void clearCache() {
		this.gamemodes.clear();
	}

	@Override
	public SpartanGameCache getCache(String name) {
		if(this.gamemodes.containsKey(name)){
			return this.gamemodes.get(name).getCache();
		}else{
			return null;
		}
	}

	@Override
	public void addGameMode(String name, SpartanGameMode cache) {
		if(!this.gamemodes.containsKey(name)){
			this.gamemodes.put(name, cache);
		}
	}

	public File getFolder() {
		return this.folder;
	}
	
}
