package fr.iambluedev.spartan.node.command;

import java.util.Map.Entry;
import java.util.logging.Level;

import fr.iambluedev.spartan.api.command.SpartanCommand;
import fr.iambluedev.spartan.api.server.SpartanServer;
import fr.iambluedev.spartan.node.Main;

public class ListServerCommand extends SpartanCommand{

	public ListServerCommand() {
		super("listservers");
	}

	@Override
	public void execute(String[] args) {
		Main.getInstance().getLogger().log(Level.INFO, "List of created servers (" +  Main.getInstance().getServerManager().getServers().size() + "):");
		StringBuilder sb = new StringBuilder();
		for(Entry<String, SpartanServer> server : Main.getInstance().getServerManager().getServers().entrySet()){
			sb.append(server.getKey() + "(port:" + server.getValue().getPort() + ", gamemode: " + server.getValue().getGamemode().getName()+ "), ");
		}
		if(sb.length() != 0){
			Main.getInstance().getLogger().log(Level.INFO, sb.toString());
		}else{
			Main.getInstance().getLogger().log(Level.INFO, "No server created");
		}
	}

}
