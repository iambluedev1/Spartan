package fr.iambluedev.spartan.node.logger;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class SpartanLogger extends Logger {

	public SpartanLogger(String name, String file) {
		super(name, null);
		setUseParentHandlers(false);
		
		Handler[] handlers = getHandlers();
		for(Handler handler : handlers){
	        if(handler.getClass() == ConsoleHandler.class){
	        	removeHandler(handler);
	        }
		}
		
		try {
			FileHandler fileHandler = new FileHandler(file, true);
			fileHandler.setFormatter(new SpartanFileFormatter());
		    addHandler(fileHandler);
		    
		    ConsoleHandler ch = new ConsoleHandler();
		    ch.setFormatter(new SpartanFileFormatter());
		    addHandler(ch);
		    
		    setLevel(Level.ALL);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void log(LogRecord record){
		super.log(record);
	}

}
