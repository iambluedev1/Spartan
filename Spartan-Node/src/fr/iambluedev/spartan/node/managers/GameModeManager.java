package fr.iambluedev.spartan.node.managers;

import java.util.HashMap;
import java.util.Map;

import fr.iambluedev.spartan.api.gamemode.SpartanGame;
import fr.iambluedev.spartan.api.gamemode.SpartanGameMode;

public class GameModeManager extends SpartanGame{

	private Map<String, SpartanGameMode> gamemodes;
	
	public GameModeManager(){
		this.gamemodes = new HashMap<String, SpartanGameMode>();
	}
	
	@Override
	public void clearGameModes(){
		this.gamemodes.clear();
	}
	
	@Override
	public void addGameMode(SpartanGameMode gamemode, String name){
		if(!this.gamemodes.containsKey(name)){
			this.gamemodes.put(name, gamemode);
		}
	}
	
	@Override
	public void removeGamemode(String name){
		if(this.gamemodes.containsKey(name)){
			this.gamemodes.remove(name);
		}
	}
	
	@Override
	public Map<String, SpartanGameMode> getGameModes(){
		return this.gamemodes;
	}
	
}
