package fr.iambluedev.spartan.node;

import java.io.File;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.iambluedev.spartan.api.cache.SpartanCache;
import fr.iambluedev.spartan.api.command.SpartanDispatcher;
import fr.iambluedev.spartan.api.node.SpartanNode;
import fr.iambluedev.spartan.node.command.StopCommand;
import fr.iambluedev.spartan.node.logger.SpartanLogger;
import fr.iambluedev.spartan.node.managers.CacheManager;
import fr.iambluedev.spartan.node.managers.CommandManager;
import fr.iambluedev.spartan.node.managers.EventsManager;

public class Node extends SpartanNode{

	private boolean isRunning;
	private Logger logger;
	private File log;
	private Integer freeRam;
	private Integer ram;
	public EventsManager events;
	private CommandManager commandManager;
	private CacheManager cacheManager;
	
	public Node(){
		this.isRunning = false;
		this.log = new File("logs", "node.log");
		File parent = new File(this.log.getParent());
		if(!parent.exists()){
			parent.mkdir();
		}
		this.logger = new SpartanLogger("Node", this.log.getPath());
		this.commandManager = new CommandManager();
		this.commandManager.addCommand("stop", new StopCommand());
		this.events = new EventsManager();
		this.cacheManager = new CacheManager();
	
	}

	@Override
	public void start() {
		this.isRunning = true;
	}

	@Override
	public Logger getLogger() {
		return this.logger;
	}

	@Override
	public String getVersion() {
		return Main.VERSION;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public UUID getId() {
		return null;
	}

	@Override
	public SpartanDispatcher getCommandManager() {
		return this.commandManager;
	}

	@Override
	public boolean isRunning() {
		return this.isRunning;
	}

	@Override
	public void stop() {
		 new Thread("Shutdown Thread"){
			 @Override
			 public void run(){
				 Node.this.isRunning = false;
				 Node.this.getLogger().log(Level.INFO, "Stopping node");
				 System.exit(0);
			 }
		 }.start();
	}

	public File getLog() {
		return this.log;
	}

	@Override
	public SpartanCache getCacheManager() {
		return this.cacheManager;
	}

	@Override
	public boolean hasEnoughtRam(Integer ram) {
		if(this.freeRam - ram > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Integer getFreeRam() {
		return this.freeRam;
	}

	@Override
	public Integer getUsedRam() {
		return this.ram;
	}
	
}
