package fr.iambluedev.spartan.node;

import java.util.Scanner;
import java.util.logging.Level;

public class Main {

	public static Node instance;
	
	public static String VERSION = "beta 1.0";
	
	public static void main(String[] args) {
		instance = new Node();
		Scanner scanner = new Scanner(System.in);
		instance.start();
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
		    public void run() {
		    	instance.stop();
		    }
		});
		
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
