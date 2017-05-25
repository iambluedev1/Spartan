package fr.iambluedev.spartan.node.configs;

import java.util.logging.Level;

import fr.iambluedev.spartan.api.config.SpartanConfig;
import fr.iambluedev.spartan.api.gson.JSONObject;
import fr.iambluedev.spartan.api.node.SpartanNode;

public class Config extends SpartanConfig{

	public Config(SpartanNode instance) {
		super("config", instance);
		instance.getLogger().log(Level.INFO, "Initialising config file");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setupConfig() {
		this.getInstance().getLogger().log(Level.INFO, "Creating config file");
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
		this.save();
	}

}
