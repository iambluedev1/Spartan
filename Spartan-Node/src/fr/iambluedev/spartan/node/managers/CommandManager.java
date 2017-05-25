package fr.iambluedev.spartan.node.managers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import fr.iambluedev.spartan.api.command.SpartanCommand;
import fr.iambluedev.spartan.api.command.SpartanDispatcher;
import fr.iambluedev.spartan.node.Main;
import fr.iambluedev.spartan.node.events.command.CommandExecutedEvent;

public class CommandManager extends SpartanDispatcher{

	private Map<String, SpartanCommand> command;
	
	public CommandManager(){
		this.command = new HashMap<String, SpartanCommand>();
	}

	public void addCommand(String name, SpartanCommand command){
		this.command.put(name.toLowerCase(), command);
	}
	
	public void removeCommand(String name){
		this.command.remove(name);
	}
	
	public void removeCommand(SpartanCommand command){
		this.command.values().remove(command);
	}
	
	public Map<String, SpartanCommand> getCommand() {
		return this.command;
	}

	public void setCommand(Map<String, SpartanCommand> command) {
		this.command = command;
	}
	
	@Override
	public boolean dispatchCommand(String commandLine){
		String[] split = commandLine.replace("/", "").split(" ");
		if (split.length == 0) {
			return false;
	    }
		String commandName = split[0].toLowerCase();
		SpartanCommand command = this.command.get(commandName);
		if (command == null) {
			return false;
	    }
		String[] args = Arrays.copyOfRange(split, 1, split.length);
		Main.getInstance().getLogger().log(Level.INFO, "executed command: /" + commandName);
		Main.getInstance().getEventsManager().getPublisher().raiseEvent(new CommandExecutedEvent(commandName));
		command.execute(args);
		return true;
	}
}
