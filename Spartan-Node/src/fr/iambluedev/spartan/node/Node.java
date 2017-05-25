package fr.iambluedev.spartan.node;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.iambluedev.spartan.api.cache.SpartanCache;
import fr.iambluedev.spartan.api.command.SpartanDispatcher;
import fr.iambluedev.spartan.api.gamemode.SpartanGame;
import fr.iambluedev.spartan.api.gson.JSONObject;
import fr.iambluedev.spartan.api.node.SpartanNode;
import fr.iambluedev.spartan.node.command.ListGameModeCommand;
import fr.iambluedev.spartan.node.command.StopCommand;
import fr.iambluedev.spartan.node.configs.Config;
import fr.iambluedev.spartan.node.logger.SpartanLogger;
import fr.iambluedev.spartan.node.managers.CacheManager;
import fr.iambluedev.spartan.node.managers.CommandManager;
import fr.iambluedev.spartan.node.managers.EventsManager;
import fr.iambluedev.spartan.node.managers.GameModeManager;

public class Node extends SpartanNode{

	private boolean isRunning;
	private Logger logger;
	private File log;
	private Integer freeRam;
	private Integer ram;
	private EventsManager events;
	private String name;
	private Integer id;
	
	private CommandManager commandManager;
	private CacheManager cacheManager;
	private GameModeManager gameManager;
	
	private Config config;
	
	public Node(){
		this.isRunning = false;
		this.log = new File("logs", "node.log");
		File parent = new File(this.log.getParent());
		if(!parent.exists()){
			parent.mkdir();
		}
		
		this.logger = new SpartanLogger("Node", this.log.getPath());
		
		this.getLogger().log(Level.INFO, "Starting SpartanNode");
		this.getLogger().log(Level.INFO, "Enabled SpartanNode version " + this.getVersion());
		this.getLogger().log(Level.INFO, "2017 - iambluedev - all Rights reserved");
		
		this.events = new EventsManager();
		this.cacheManager = new CacheManager();
		this.gameManager = new GameModeManager();
		
		this.commandManager = new CommandManager();
		this.commandManager.addCommand("stop", new StopCommand());
		this.commandManager.addCommand("listgm", new ListGameModeCommand());
		
		this.config = new Config(this);
		this.getLogger().log(Level.INFO, "Config file succesfully loaded");
		JSONObject jsonObj = (JSONObject) this.getConfig().getJsonObject().get("node");
		this.name = (String) jsonObj.get("name");
		this.id = ((Long) jsonObj.get("id")).intValue();
		this.ram = ((Long) jsonObj.get("ram")).intValue();
		this.freeRam = ram;
		this.getLogger().log(Level.INFO, "Node specs : ");
		this.getLogger().log(Level.INFO, "name: " + this.name);
		this.getLogger().log(Level.INFO, "id: " + this.id);
		this.getLogger().log(Level.INFO, "ram: " + this.ram);
		this.getLogger().log(Level.INFO, "freeram: " + this.freeRam);
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
		return this.name;
	}

	@Override
	public Integer getId() {
		return this.id;
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

	public void removeRam(Integer ram){
		this.freeRam = this.freeRam - ram;
	}
	
	public void addRam(Integer ram){
		this.freeRam = this.freeRam + ram;
	}
	
	@Override
	public Integer getFreeRam() {
		return this.freeRam;
	}

	@Override
	public Integer getUsedRam() {
		return this.ram;
	}

	@Override
	public SpartanGame getGameManager() {
		return this.gameManager;
	}

	public EventsManager getEventsManager() {
		return this.events;
	}

	public Config getConfig() {
		return this.config;
	}
}
