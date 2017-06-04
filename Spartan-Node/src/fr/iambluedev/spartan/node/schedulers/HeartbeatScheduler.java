package fr.iambluedev.spartan.node.schedulers;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TimerTask;
import java.util.logging.Level;

import fr.iambluedev.spartan.api.utils.Callback;
import fr.iambluedev.spartan.node.Main;
import redis.clients.jedis.Jedis;

public class HeartbeatScheduler extends TimerTask {
    
	@Override
    public void run() {
		Main.getInstance().getRedis().get(new Callback<Jedis>() {
			@Override
			public void call(Jedis jedis) {
				Main.getInstance().getLogger().log(Level.INFO, "Updated");
				jedis.hset("nodes", Main.getInstance().getName() + "-" + Main.getInstance().getId(), String.valueOf(System.currentTimeMillis()));
				Map<String, String> proxies = jedis.hgetAll("nodes");
				for(Entry<String, String> node : proxies.entrySet()){
					 if(System.currentTimeMillis() - Long.parseLong(node.getValue()) > 10000) {
						 Main.getInstance().getLogger().log(Level.WARNING, "Node " + node.getKey() + " is probably offline, removing from nodes datas");
						 jedis.hdel("nodes", node.getKey());
					 }
				}
			}
		});
	}
}
