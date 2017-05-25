package fr.iambluedev.spartan.node.events.server;

import fr.iambluedev.spartan.api.events.SpartanEvent;
import fr.iambluedev.spartan.api.server.SpartanServer;

public class ServerStartEvent extends SpartanEvent{
	
	private SpartanServer server;
	
	public ServerStartEvent(SpartanServer server){
		this.server = server;
	}

	public SpartanServer getServer() {
		return this.server;
	}
	
}
