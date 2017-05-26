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
		JSONObject hunger1Obj = new JSONObject();
		hunger1Obj.put("name", "HungerGames 1.8 w/ teams & kits");
		hunger1Obj.put("zipUrl", "https://my.mixtape.moe/milxlj.zip");
		this.getJsonObject().put("hg18tk", hunger1Obj);
		
		JSONObject hunger2Obj = new JSONObject();
		hunger2Obj.put("name", "HungerGames 1.8 w/ kits");
		hunger1Obj.put("zipUrl", "https://my.mixtape.moe/milxlj.zip");
		this.getJsonObject().put("hg18k", hunger2Obj);
		
		JSONObject hunger3Obj = new JSONObject();
		hunger3Obj.put("name", "HungerGames 1.11 w/ teams & kits");
		hunger1Obj.put("zipUrl", "https://my.mixtape.moe/milxlj.zip");
		this.getJsonObject().put("hg111tk", hunger3Obj);
		
		JSONObject hunger4Obj = new JSONObject();
		hunger4Obj.put("name", "HungerGames 1.11 w/ kits");
		hunger1Obj.put("zipUrl", "https://my.mixtape.moe/milxlj.zip");
		this.getJsonObject().put("hg111k", hunger4Obj);
		
		this.save();
	}

}
