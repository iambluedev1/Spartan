package fr.iambluedev.spartan.node.command;

import java.util.Map.Entry;
import java.util.logging.Level;

import fr.iambluedev.spartan.api.command.SpartanCommand;
import fr.iambluedev.spartan.api.gamemode.SpartanGameMode;
import fr.iambluedev.spartan.node.Main;

public class ListGameModeCommand extends SpartanCommand{

	public ListGameModeCommand() {
		super("listgm");
	}

	@Override
	public void execute(String[] args) {
		Main.getInstance().getLogger().log(Level.INFO, "List of available gamemodes (" +  Main.getInstance().getGameManager().getGameModes().size() + "):");
		StringBuilder sb = new StringBuilder();
		for(Entry<String, SpartanGameMode> gm : Main.getInstance().getGameManager().getGameModes().entrySet()){
			sb.append(gm.getKey());
		}
		if(sb.length() != 0){
			Main.getInstance().getLogger().log(Level.INFO, sb.toString());
		}else{
			Main.getInstance().getLogger().log(Level.INFO, "No gamemode available");
		}
	}

}
