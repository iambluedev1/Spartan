package fr.iambluedev.spartan.node.events.node;

import fr.iambluedev.spartan.api.events.SpartanEvent;
import fr.iambluedev.spartan.api.node.SpartanNode;

public class NodeStartEvent extends SpartanEvent{

	private SpartanNode node;
	
	public NodeStartEvent(SpartanNode node){
		this.node = node;
	}

	public SpartanNode getNode() {
		return this.node;
	}
	
}
