package fr.iambluedev.spartan.node.command;

import java.util.logging.Level;

import fr.iambluedev.spartan.api.command.SpartanCommand;
import fr.iambluedev.spartan.api.server.SpartanServer;
import fr.iambluedev.spartan.node.Main;

public class DestroyServerCommand extends SpartanCommand{

	public DestroyServerCommand() {
		super("destroyserver");
	}

	@Override
	public void execute(String[] args) {
		if(args.length == 1){
			String name = args[0];
			if(Main.getInstance().getServerManager().getServer(name) != null){
				SpartanServer server = Main.getInstance().getServerManager().getServer(name);
				Main.getInstance().getLogger().log(Level.INFO, "Selected server : " + server.getName());
				Main.getInstance().getLogger().log(Level.INFO, "[" + server.getName() + "] Stopping server");
				server.stopServer();
				Main.getInstance().getLogger().log(Level.INFO, "[" + server.getName() + "] Server stopped");
				Main.getInstance().getLogger().log(Level.INFO, "[" + server.getName() + "] Removing server folder");
				server.destroyServer();
				Main.getInstance().getLogger().log(Level.INFO, "[" + server.getName() + "] Server folder removed !");
				Main.getInstance().getServerManager().removeServer(server.getName());
				Main.getInstance().getLogger().log(Level.INFO, "[" + server.getName() + "] Server successfully removed !");
			}else{
				Main.getInstance().getLogger().log(Level.WARNING, "Wrong gamemode");
			}
		}else{
			Main.getInstance().getLogger().log(Level.WARNING, "Wrong command : stopserver <server>");
		}
	}

}
