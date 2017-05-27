package fr.iambluedev.spartan.node.server;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import fr.iambluedev.spartan.api.gamemode.SpartanGameMode;
import fr.iambluedev.spartan.api.node.SpartanNode;
import fr.iambluedev.spartan.api.server.SpartanServer;
import fr.iambluedev.spartan.api.utils.IOUtils;
import fr.iambluedev.spartan.api.utils.Ports;
import fr.iambluedev.spartan.node.Main;
import fr.iambluedev.spartan.node.events.server.ServerCreatedEvent;
import fr.iambluedev.spartan.node.events.server.ServerDestroyEvent;
import fr.iambluedev.spartan.node.events.server.ServerKillEvent;
import fr.iambluedev.spartan.node.events.server.ServerStartEvent;
import fr.iambluedev.spartan.node.events.server.ServerStopEvent;

public class Server extends SpartanServer{

	private String name;
	private File folder;
	
	public Server(SpartanGameMode gamemode, String name) throws IOException {
		super(gamemode);
		this.name = name;
		this.folder = new File("servers", this.name);
		if(this.folder.exists()){
			System.out.println("Deleting" + this.name + "server folder");
			IOUtils.deleteDir(this.folder);
		}
		this.folder.mkdir();
	}
	
	public Server(SpartanGameMode gamemode) throws IOException {
		super(gamemode);
		this.name = this.getRandomName();
		this.folder = new File("servers", this.name);
		if(this.folder.exists()){
			System.out.println("Deleting" + this.name + "server folder");
			IOUtils.deleteDir(this.folder);
		}
		this.folder.mkdir();
	}

	@Override
	public boolean isAlive() {
		return Ports.isAvailable(this.getPort());
	}

	@Override
	public void killProcess() {
		this.getProcess().destroyForcibly();
		Main.getInstance().getLogger().log(Level.INFO, "Kill proccess for server " + this.getName() + " (gm: " + this.getGamemode().getName() + ") (port: " + this.getPort() +  ")");
		Main.getInstance().getEventsManager().getPublisher().raiseEvent(new ServerKillEvent(this));
	}

	@Override
	public void destroyServer() {
		Main.getInstance().getEventsManager().getPublisher().raiseEvent(new ServerDestroyEvent(this));
		IOUtils.deleteDir(this.folder);
	}

	@Override
	public void startServer() {
		List<String> params = Main.getInstance().getCmd();
		for(String param : params){
			if(param.equals("{port}")) param.replace("{port}", this.getPort() + "");
			if(param.equals("{jarFile}")) param.replace("{jarFile}", this.getPort() + "");
		}
		ProcessBuilder processBuilder = new ProcessBuilder();
		Main.getInstance().getEventsManager().getPublisher().raiseEvent(new ServerStartEvent(this));
	}

	public String getName() {
		return this.name;
	}

	@Override
	public void createServer() {
		Main.getInstance().getEventsManager().getPublisher().raiseEvent(new ServerCreatedEvent(this));
		try {
			IOUtils.copy(new File("cache", this.getGamemode().getCache().getName()), this.folder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stopServer() {
		Main.getInstance().getEventsManager().getPublisher().raiseEvent(new ServerStopEvent(this));
	}
	
}
