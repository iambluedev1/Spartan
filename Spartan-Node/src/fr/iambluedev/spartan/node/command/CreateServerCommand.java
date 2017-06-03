package fr.iambluedev.spartan.node.command;

import java.util.logging.Level;

import fr.iambluedev.spartan.api.command.SpartanCommand;
import fr.iambluedev.spartan.api.gamemode.SpartanGameMode;
import fr.iambluedev.spartan.api.server.SpartanServer;
import fr.iambluedev.spartan.node.Main;
import fr.iambluedev.spartan.node.server.Server;

public class CreateServerCommand extends SpartanCommand{

	public CreateServerCommand() {
		super("createserver");
	}

	@Override
	public void execute(String[] args) {
		if(args.length == 1){
			String name = args[0];
			if(Main.getInstance().getGameManager().getGameMode(name) != null){
				SpartanGameMode gm = Main.getInstance().getGameManager().getGameMode(name);
				Main.getInstance().getLogger().log(Level.INFO, "Selected gamemode : " + gm.getName());
				try {
					SpartanServer server = new Server(gm);
					Main.getInstance().getLogger().log(Level.INFO, "Selected server : " + server.getName());
					Main.getInstance().getLogger().log(Level.INFO, "[" + server.getName() + "] Copying server datas");
					Main.getInstance().getServerManager().addServer(server.getName(), server);
					server.createServer();
					Main.getInstance().getLogger().log(Level.INFO, "[" + server.getName() + "] Server datas copied !");
					server.startServer();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else{
				Main.getInstance().getLogger().log(Level.WARNING, "Wrong gamemode");
			}
		}else{
			Main.getInstance().getLogger().log(Level.WARNING, "Wrong command : createserver <gamemode>");
		}
	}

}
