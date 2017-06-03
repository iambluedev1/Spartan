package fr.iambluedev.spartan.api.gamemode;

import fr.iambluedev.spartan.api.cache.SpartanGameCache;

public abstract class SpartanGameMode {
	
	public abstract String getName();
	
	public abstract Integer getUsedRam();
	
	public abstract SpartanGameCache getCache();
	
}
