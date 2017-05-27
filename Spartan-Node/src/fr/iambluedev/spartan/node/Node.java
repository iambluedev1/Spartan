package fr.iambluedev.spartan.node;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.iambluedev.spartan.api.cache.SpartanCache;
import fr.iambluedev.spartan.api.command.SpartanDispatcher;
import fr.iambluedev.spartan.api.download.SpartanDownload;
import fr.iambluedev.spartan.api.gamemode.SpartanGame;
import fr.iambluedev.spartan.api.gamemode.SpartanGameMode;
import fr.iambluedev.spartan.api.gson.JSONObject;
import fr.iambluedev.spartan.api.http.SpartanUrl;
import fr.iambluedev.spartan.api.node.SpartanNode;
import fr.iambluedev.spartan.api.utils.ZipExtract;
import fr.iambluedev.spartan.node.command.CreateServerCommand;
import fr.iambluedev.spartan.node.command.ListGameModeCommand;
import fr.iambluedev.spartan.node.command.StopCommand;
import fr.iambluedev.spartan.node.configs.GameModeConfig;
import fr.iambluedev.spartan.node.configs.GeneralConfig;
import fr.iambluedev.spartan.node.events.node.NodeStartEvent;
import fr.iambluedev.spartan.node.events.node.NodeStopEvent;
import fr.iambluedev.spartan.node.gamemode.GameMode;
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
	
	private GeneralConfig config;
	private GameModeConfig gamemode;
	
	private List<String> cmd;
	private String jarName;
	
	public Node(){
		Main.instance = this;
		this.isRunning = false;
		this.log = new File("logs", "node.log");
		File parent = new File(this.log.getParent());
		if(!parent.exists()){
			parent.mkdir();
		}
		
		this.logger = new SpartanLogger("Node", this.log.getPath());
		
		this.getLogger().log(Level.INFO, "Enabled SpartanNode version " + this.getVersion());
		this.getLogger().log(Level.INFO, "2017 - iambluedev - all Rights reserved");
		
		this.events = new EventsManager();
		this.cacheManager = new CacheManager(this);
		this.gameManager = new GameModeManager();
		
		this.commandManager = new CommandManager();
		this.commandManager.addCommand("stop", new StopCommand());
		this.commandManager.addCommand("listgm", new ListGameModeCommand());
		this.commandManager.addCommand("createserver", new CreateServerCommand());
		
		this.config = new GeneralConfig(this);
		this.gamemode = new GameModeConfig(this);
		
		JSONObject jsonObj = (JSONObject) this.getConfig().getJsonObject().get("node");
		this.name = (String) jsonObj.get("name");
		this.id = Integer.valueOf(jsonObj.get("id") + "");
		this.ram = Integer.valueOf(jsonObj.get("ram") + "");
		this.freeRam = ram;
		this.getLogger().log(Level.INFO, "Node specs : ");
		this.getLogger().log(Level.INFO, "name: " + this.name);
		this.getLogger().log(Level.INFO, "id: " + this.id);
		this.getLogger().log(Level.INFO, "ram: " + this.ram);
		this.getLogger().log(Level.INFO, "freeram: " + this.freeRam);
		
		JSONObject gmsObj = (JSONObject) this.getGamemode().getJsonObject();
		for(Object obj : gmsObj.keySet()){
			JSONObject gm = (JSONObject) this.getGamemode().getJsonObject().get(obj);
			String gName = (String) gm.get("name");
			String zipUrl = (String) gm.get("zipUrl");
			GameMode sGm = new GameMode(gName, zipUrl, obj.toString());
			this.gameManager.addGameMode(sGm, obj.toString());
			this.cacheManager.addGameMode(obj.toString(), sGm);
			this.getLogger().log(Level.INFO, "Added " + gName + " gamemode !");
		}
		
		this.getLogger().log(Level.INFO, "Preparing to update cache");
		for(Entry<String, SpartanGameMode> gm : this.cacheManager.getGameModes().entrySet()){
			this.getLogger().log(Level.INFO, "Updating " + gm.getValue().getName());
			new SpartanDownload(new SpartanUrl(gm.getValue().getCache().getZipUrl(), new File(this.cacheManager.getFolder(), gm.getKey() + ".temp.zip").getPath(), gm.getKey()), this).run();
			try {
				this.getLogger().log(Level.INFO, "[" + gm.getKey() + "] Extracting " + gm.getValue().getName());
				new ZipExtract().unzip(new File(this.cacheManager.getFolder(), gm.getKey() + ".temp.zip").getPath(), new File(this.cacheManager.getFolder(), gm.getKey()).getPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		this.cmd = Arrays.asList(this.getConfig().getJsonObject().get("cmd").toString().split(" "));
		this.jarName = this.getConfig().getJsonObject().get("jarName").toString();
	}

	@Override
	public void start() {
		this.getLogger().log(Level.INFO, "Starting SpartanNode");
		this.isRunning = true;
		Main.getInstance().getEventsManager().getPublisher().raiseEvent(new NodeStartEvent(this));
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
		 Main.getInstance().getEventsManager().getPublisher().raiseEvent(new NodeStopEvent(this));
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

	public GeneralConfig getConfig() {
		return this.config;
	}

	public GameModeConfig getGamemode() {
		return this.gamemode;
	}

	public List<String> getCmd() {
		return this.cmd;
	}

	public String getJarName() {
		return this.jarName;
	}
	
}