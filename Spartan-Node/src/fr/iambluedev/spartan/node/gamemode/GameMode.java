package fr.iambluedev.spartan.node.gamemode;

import fr.iambluedev.spartan.api.cache.SpartanGameCache;
import fr.iambluedev.spartan.api.gamemode.SpartanGameMode;

public class GameMode extends SpartanGameMode{

	private String name;
	private SpartanGameCache cache;
	private Integer usedRam;
	
	public GameMode(String name, String zipUrl, String dName, Integer usedRam){
		this.name = name;
		this.cache = new SpartanGameCache(zipUrl, dName);
		this.usedRam = usedRam;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public SpartanGameCache getCache() {
		return this.cache;
	}

	@Override
	public Integer getUsedRam() {
		return this.usedRam;
	}

}
