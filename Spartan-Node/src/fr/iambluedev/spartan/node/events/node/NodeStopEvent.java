package fr.iambluedev.spartan.node.events.node;

import fr.iambluedev.spartan.api.events.SpartanEvent;
import fr.iambluedev.spartan.api.node.SpartanNode;

public class NodeStopEvent extends SpartanEvent{

	private SpartanNode node;
	
	public NodeStopEvent(SpartanNode node){
		this.node = node;
	}

	public SpartanNode getNode() {
		return this.node;
	}
}
