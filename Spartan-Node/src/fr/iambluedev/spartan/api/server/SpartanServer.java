package fr.iambluedev.spartan.api.server;

import java.io.IOException;

import fr.iambluedev.spartan.api.gamemode.SpartanGameMode;
import fr.iambluedev.spartan.api.utils.Ports;

public abstract class SpartanServer {

	private Process process;
	private SpartanGameMode gamemode;
	private Integer port;
	
	public SpartanServer(SpartanGameMode gamemode, Integer port){
		this.gamemode = gamemode;
		this.port = port;
	}
	
	public SpartanServer(SpartanGameMode gamemode) throws IOException{
		this.gamemode = gamemode;
		this.port = Ports.getRandomPort();
	}

	public void setProcess(Process process){
		this.process = process;
	}
	
	public Process getProcess() {
		return this.process;
	}
	
	public SpartanGameMode getGamemode() {
		return this.gamemode;
	}
	
	public Integer getPort() {
		return this.port;
	}

	public abstract void startServer() throws Exception;
	
	public abstract void stopServer();
	
	public abstract boolean isAlive();
	
	public abstract void killProcess();
	
	public abstract void destroyServer();
	
	public abstract void createServer();
	
	public abstract String getName();
	
	public String getRandomName(){
		return this.getGamemode().getCache().getName() + "_" + this.getPort();
	}
	
}
