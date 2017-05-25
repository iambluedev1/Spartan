package fr.iambluedev.spartan.node.events.command;

import fr.iambluedev.spartan.api.events.SpartanEvent;

public class CommandExecutedEvent extends SpartanEvent{

	private String commandName;
	
	public CommandExecutedEvent(String commandName){
		this.commandName = commandName;
	}

	public String getCommandName() {
		return this.commandName;
	}
}
