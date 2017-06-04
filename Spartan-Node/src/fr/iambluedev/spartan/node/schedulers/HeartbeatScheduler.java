package fr.iambluedev.spartan.node.schedulers;

import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import fr.iambluedev.spartan.node.Main;
import redis.clients.jedis.Pipeline;

public class HeartbeatScheduler implements Runnable {
    
	@Override
    public void run() {
		Main.getInstance().getJedis().hset("nodes", Main.getInstance().getName() + "-" + Main.getInstance().getId(), String.valueOf(System.currentTimeMillis()));
		Map<String, String> proxies = Main.getInstance().getJedis().hgetAll("nodes");
		Pipeline pipeline = Main.getInstance().getJedis().pipelined();
		for(Entry<String, String> node : proxies.entrySet()){
			 if(System.currentTimeMillis() - Long.parseLong(node.getValue()) > 5000) {
				 Main.getInstance().getLogger().log(Level.WARNING, "Node " + node.getKey() + " is probably offline, removing from nodes datas");
				 pipeline.hdel("nodes", node.getKey());
			 }
		}
		pipeline.sync();
	}
}
