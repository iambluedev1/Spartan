package fr.iambluedev.spartan.node.redis;

import fr.iambluedev.spartan.api.gson.JSONObject;
import fr.iambluedev.spartan.api.gson.parser.JSONParser;
import fr.iambluedev.spartan.api.gson.parser.ParseException;
import fr.iambluedev.spartan.node.Main;
import redis.clients.jedis.JedisPubSub;

public class ChannelHandler extends JedisPubSub {
	
	@Override
	public void onMessage(String channel, String message) {
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) new JSONParser().parse(message);
		} catch (ParseException e) {}
		
		if(channel.equalsIgnoreCase("node")){
			String id = (String) jsonObj.get("id");
			System.out.println("Message from : " + id);
		}else if(channel.equalsIgnoreCase("node:" + Main.getInstance().getName() + "-" + Main.getInstance().getId())){
			
		}
	}
}