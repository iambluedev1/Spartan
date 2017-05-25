package fr.iambluedev.spartan.node;

import java.util.Scanner;
import java.util.logging.Level;

public class Main {

	private static Node instance;
	
	public static String VERSION = "beta 1.0";
	
	public static void main(String[] args) {
		instance = new Node();
		instance.getLogger().log(Level.INFO, "Starting SpartanNode");
		instance.getLogger().log(Level.INFO, "Enabled SpartanNode version " + VERSION);
		instance.getLogger().log(Level.INFO, "2017 - iambluedev - all Rights reserved");
		Scanner scanner = new Scanner(System.in);
		instance.start();
		while(instance.isRunning()){
			String command = scanner.nextLine();
			if(!instance.getCommandManager().dispatchCommand(command)){
				instance.getLogger().log(Level.WARNING, "Command not found");
			}
		}
		scanner.close();
	}
	
	public static Node getInstance(){
		return instance;	
	}

}
