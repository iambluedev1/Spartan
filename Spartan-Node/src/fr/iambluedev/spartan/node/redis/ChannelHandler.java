package fr.iambluedev.spartan.node.redis;

import java.util.logging.Level;

import fr.iambluedev.spartan.api.gson.JSONObject;
import fr.iambluedev.spartan.api.gson.parser.JSONParser;
import fr.iambluedev.spartan.api.gson.parser.ParseException;
import fr.iambluedev.spartan.api.utils.Callback;
import fr.iambluedev.spartan.api.utils.RedisJsonMessage;
import fr.iambluedev.spartan.node.Main;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class ChannelHandler extends JedisPubSub {
	
	@Override
	public void onMessage(String channel, String message) {
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) new JSONParser().parse(message);
		} catch (ParseException e) {
			return;
		}
		String id = (String) jsonObj.get("id");
		String cmd = (String) jsonObj.get("cmd");
		String content = (String) jsonObj.get("content");
		
		if(channel.equalsIgnoreCase("node")){
			if(cmd.equalsIgnoreCase("hasEnoughtForGm") && !content.isEmpty()){
				if(Main.getInstance().hasEnoughtRam(Main.getInstance().getGameManager().getGameMode(content).getUsedRam())){
					Main.getInstance().getLogger().log(Level.INFO, "Received hasEnought question from " + id);
					Main.getInstance().getRedis().get(new Callback<Jedis>() {
						@Override
						public void call(Jedis jedis) {
							jedis.publish("node", new RedisJsonMessage().setCmd("hasEnoughtResponse").setContent("This node has enought ram").get());
						}
					});
				}
			}
		}else if(channel.equalsIgnoreCase("node:" + Main.getInstance().getName() + "-" + Main.getInstance().getId())){
			if(cmd.equalsIgnoreCase("createserver") && !content.isEmpty()){
				Main.getInstance().getLogger().log(Level.INFO, "Received createServer order from " + id);
				Main.getInstance().getCommandManager().dispatchCommand("createserver " + content);
			}
			
			if(cmd.equalsIgnoreCase("destroyserver") && !content.isEmpty()){
				Main.getInstance().getLogger().log(Level.INFO, "Received destroyServer order from " + id);
				Main.getInstance().getCommandManager().dispatchCommand("destroyserver " + content);
			}
			
			if(cmd.equalsIgnoreCase("reloadcache") && !content.isEmpty()){
				Main.getInstance().getLogger().log(Level.INFO, "Received reloadCache order from " + id);
				Main.getInstance().getCommandManager().dispatchCommand("reloadcache");
			}
			
			if(cmd.equalsIgnoreCase("stop") && !content.isEmpty()){
				Main.getInstance().getLogger().log(Level.INFO, "Received stop order from " + id);
				Main.getInstance().getCommandManager().dispatchCommand("stop");
			}
		}
	}
}