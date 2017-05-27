package fr.iambluedev.spartan.api.gamemode;

import java.util.Map;

public abstract class SpartanGame {

	public abstract void clearGameModes();
	
	public abstract void addGameMode(SpartanGameMode gamemode, String name);
	
	public abstract SpartanGameMode getGameMode(String name);
	
	public abstract void removeGamemode(String name);
	
	public abstract Map<String, SpartanGameMode> getGameModes();
}
