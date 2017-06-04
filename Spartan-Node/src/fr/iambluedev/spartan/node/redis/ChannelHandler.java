package fr.iambluedev.spartan.node;

import redis.clients.jedis.JedisPubSub;

public class ChannelHandler extends JedisPubSub {
	public ChannelHandler() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onMessage(String channel, String message) {
		System.out.println("Received " + message + " on channel " + channel);
		
	}
}