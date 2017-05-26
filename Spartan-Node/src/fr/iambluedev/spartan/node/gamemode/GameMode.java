package fr.iambluedev.spartan.node.gamemode;

import fr.iambluedev.spartan.api.cache.SpartanGameCache;
import fr.iambluedev.spartan.api.gamemode.SpartanGameMode;

public class GameMode extends SpartanGameMode{

	private String name;
	private SpartanGameCache cache;
	
	public GameMode(String name, String zipUrl){
		this.name = name;
		this.cache = new SpartanGameCache(zipUrl);
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public SpartanGameCache getCache() {
		return this.cache;
	}

}
