package fr.iambluedev.spartan.node.command;

import fr.iambluedev.spartan.api.command.SpartanCommand;
import fr.iambluedev.spartan.node.Main;

public class StopCommand extends SpartanCommand{

	public StopCommand() {
		super("stop");
	}

	@Override
	public void execute(String[] args) {
		Main.getInstance().stop();
	}

}
