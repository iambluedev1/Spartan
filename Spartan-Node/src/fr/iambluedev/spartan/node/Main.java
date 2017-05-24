package fr.iambluedev.spartan.node;

import java.util.Scanner;
import java.util.logging.Level;

public class Main {

	private static Node instance;
	
	public static void main(String[] args) {
		instance = new Node();
		System.out.println("Hello");
		Scanner scanner = new Scanner(System.in);
		instance.start();
		while(instance.isRunning){
			String command = scanner.nextLine();
			if(!instance.commandManager.dispatchCommand(command)){
				instance.getLogger().log(Level.WARNING, "Command not found");
			}
		}
		scanner.close();
	}
	
	public static Node getInstance(){
		return instance;	
	}

}
