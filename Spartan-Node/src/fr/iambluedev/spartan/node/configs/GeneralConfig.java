package fr.iambluedev.spartan.node.configs;

import fr.iambluedev.spartan.api.config.SpartanConfig;
import fr.iambluedev.spartan.api.gson.JSONObject;
import fr.iambluedev.spartan.api.node.SpartanNode;

public class GeneralConfig extends SpartanConfig{

	public GeneralConfig(SpartanNode instance) {
		super("config", instance);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setupConfig() {
		JSONObject nodeObj = new JSONObject();
		nodeObj.put("id", (Integer) 0);
		nodeObj.put("name", "nodegame-1");
		nodeObj.put("ram", 8192);
		this.getJsonObject().put("node", nodeObj);
		
		JSONObject redisObj = new JSONObject();
		redisObj.put("host", "localhost");
		redisObj.put("port", 6379);
		redisObj.put("password", "mypassword");
		this.getJsonObject().put("redis", redisObj);
		this.getJsonObject().put("cmd", "java -jar {jarFile} --port={port}");
		this.getJsonObject().put("jarName", "spigot.jar");
		this.save();
	}

}
