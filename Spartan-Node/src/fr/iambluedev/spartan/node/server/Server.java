package fr.iambluedev.spartan.node.server;

import java.io.IOException;
import java.util.logging.Level;

import fr.iambluedev.spartan.api.gamemode.SpartanGameMode;
import fr.iambluedev.spartan.api.server.SpartanServer;
import fr.iambluedev.spartan.api.utils.Ports;
import fr.iambluedev.spartan.node.Main;
import fr.iambluedev.spartan.node.events.server.ServerCreatedEvent;
import fr.iambluedev.spartan.node.events.server.ServerDestroyEvent;
import fr.iambluedev.spartan.node.events.server.ServerKillEvent;
import fr.iambluedev.spartan.node.events.server.ServerStartEvent;
import fr.iambluedev.spartan.node.events.server.ServerStopEvent;

public class Server extends SpartanServer{

	private String name;
	
	public Server(Process process, SpartanGameMode gamemode, String name) throws IOException {
		super(process, gamemode);
		this.name = name;
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
	}

	@Override
	public void startServer() {
		Main.getInstance().getEventsManager().getPublisher().raiseEvent(new ServerStartEvent(this));
	}

	public String getName() {
		return this.name;
	}

	@Override
	public void createServer() {
		Main.getInstance().getEventsManager().getPublisher().raiseEvent(new ServerCreatedEvent(this));
	}

	@Override
	public void stopServer() {
		Main.getInstance().getEventsManager().getPublisher().raiseEvent(new ServerStopEvent(this));
	}
	
}
