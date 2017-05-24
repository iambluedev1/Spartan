package fr.iambluedev.spartan.api.node;

import java.util.UUID;
import java.util.logging.Logger;

import fr.iambluedev.spartan.api.command.SpartanDispatcher;
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
	
	public abstract boolean isRunning();
	
	public static void setInstance(SpartanNode instance){
	    Preconditions.checkNotNull(instance, "instance");
	    Preconditions.checkArgument(SpartanNode.instance == null, "Instance already set");
	    SpartanNode.instance = instance;
	}
}
