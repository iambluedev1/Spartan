package fr.iambluedev.spartan.api.node;

import java.util.UUID;
import java.util.logging.Logger;

import fr.iambluedev.spartan.api.cache.SpartanCache;
import fr.iambluedev.spartan.api.command.SpartanDispatcher;
import fr.iambluedev.spartan.api.gamemode.SpartanGame;
import fr.iambluedev.spartan.api.utils.Preconditions;

public abstract class SpartanNode {

	private static SpartanNode instance;
	
	public static SpartanNode getInstance(){
	    return instance;
	}
	
	public abstract void start();
	
	public abstract void stop();
	
	public abstract Logger getLogger();
	
	public abstract String getVersion();
	
	public abstract String getName();
	
	public abstract UUID getId();
	
	public abstract SpartanDispatcher getCommandManager();
	
	public abstract SpartanCache getCacheManager();
	
	public abstract SpartanGame getGameManager();
	
	public abstract boolean isRunning();
	
	public abstract boolean hasEnoughtRam(Integer ram);
	
	public abstract Integer getFreeRam();
	
	public abstract Integer getUsedRam();
	
	public static void setInstance(SpartanNode instance){
	    Preconditions.checkNotNull(instance, "instance");
	    Preconditions.checkArgument(SpartanNode.instance == null, "Instance already set");
	    SpartanNode.instance = instance;
	}
}
