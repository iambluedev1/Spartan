package fr.iambluedev.spartan.node.managers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import fr.iambluedev.spartan.api.cache.SpartanCache;
import fr.iambluedev.spartan.api.cache.SpartanGameCache;
import fr.iambluedev.spartan.api.gamemode.SpartanGameMode;
import fr.iambluedev.spartan.api.node.SpartanNode;
import fr.iambluedev.spartan.api.utils.IOUtils;

public class CacheManager extends SpartanCache{

	private Map<String, SpartanGameMode> gamemodes;
	private File folder;
	private SpartanNode instance;
	
	public CacheManager(SpartanNode instance){
		this.gamemodes = new HashMap<String, SpartanGameMode>();
		this.folder = new File("cache");
		this.instance = instance;
		/*if(this.folder.exists()){
			this.instance.getLogger().log(Level.INFO, "Deleting cache folder");
			IOUtils.deleteDir(this.folder);
		}
		
		this.folder.mkdir();*/
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
	public void addGameMode(String name, SpartanGameMode gamemode) {
		if(!this.gamemodes.containsKey(name)){
			this.gamemodes.put(name, gamemode);
		}
	}

	public File getFolder() {
		return this.folder;
	}

	public SpartanNode getInstance() {
		return this.instance;
	}
	
	
}
