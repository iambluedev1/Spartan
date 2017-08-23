package fr.iambluedev.spartan.node.configs;

import fr.iambluedev.spartan.api.config.SpartanConfig;
import fr.iambluedev.spartan.api.gson.JSONObject;
import fr.iambluedev.spartan.api.node.SpartanNode;

public class GameModeConfig extends SpartanConfig{

	public GameModeConfig(SpartanNode instance) {
		super("gamemodes", instance);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setupConfig() {
		JSONObject bungeeObj = new JSONObject();
		bungeeObj.put("name", "BungeeCord 1.7");
		bungeeObj.put("zipUrl", "https://my.mixtape.moe/inhfkj.zip");
		bungeeObj.put("usedRam", "512");
		this.getJsonObject().put("bg17", bungeeObj);
		this.save();
	}
}
