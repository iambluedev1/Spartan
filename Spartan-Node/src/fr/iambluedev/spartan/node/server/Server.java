package fr.iambluedev.spartan.node.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.iambluedev.spartan.api.gamemode.SpartanGameMode;
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
		if(!new File("servers").exists()){
			new File("servers").mkdir();
		}
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
	public void startServer() throws Exception {
		String params = Main.getInstance().getCmd();
		params = params.replace("{port}", this.getPort() + "").replace("{jarFile}", Main.getInstance().getJarName() + "");
		
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command(params.split(" "));
		processBuilder.directory(this.folder);
		//processBuilder.redirectOutput(Redirect.INHERIT);
		//processBuilder.redirectError(Redirect.INHERIT);
		
		Main.getInstance().getLogger().log(Level.INFO, "[" + this.getName() + "] Starting server");
		Process process = processBuilder.start();
		this.setProcess(process);
		BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String ligne = "";
		while ((ligne = output.readLine()) != null) {
		    if(ligne.contains("Done")){
		    	String regex = "Done \\([0-9a-zA-z-,]+s\\)! For help, type \"help\" or \"\\?\"";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(ligne);
				Main.getInstance().getLogger().log(Level.INFO, "[" + this.getName() + "] Started in " + ligne.replace(matcher.replaceAll(""), "").replace("(", "").replace(")", "").replace("! For help, type \"help\" or \"?\"", "").replaceAll("Done", "").replaceAll(" ", ""));
				break;
		    }
		}
		Main.getInstance().getEventsManager().getPublisher().raiseEvent(new ServerStartEvent(this));
	}

	@Override
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
