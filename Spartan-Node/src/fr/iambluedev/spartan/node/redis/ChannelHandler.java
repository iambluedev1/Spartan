package fr.iambluedev.spartan.node.redis;

import redis.clients.jedis.JedisPubSub;

public class ChannelHandler extends JedisPubSub {
	
	@Override
	public void onMessage(String channel, String message) {
		System.out.println("Received " + message + " on channel " + channel);
		
	}
}