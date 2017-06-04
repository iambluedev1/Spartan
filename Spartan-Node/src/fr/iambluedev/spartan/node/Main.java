package fr.iambluedev.spartan.node;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;

public class Main {

	public static Node instance;
	
	public static String VERSION = "beta 1.0";
	
	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<String, Object>();
		for(String string : args){
			String[] strings = string.split("=");
			params.put(strings[0].replace("-", ""), strings[1]);
		}
		Boolean reloadCache = (Boolean) params.get("reloadCache");
		if(reloadCache == null) {
			reloadCache = false;
		}
		instance = new Node(reloadCache);
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
