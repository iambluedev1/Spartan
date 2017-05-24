package fr.iambluedev.spartan.node.managers;

import java.lang.reflect.Method;

import fr.iambluedev.spartan.api.events.SpartanEvent;
import fr.iambluedev.spartan.api.events.EventHandler;
import fr.iambluedev.spartan.api.events.SpartanListener;

public class EventPublisher {

	private EventsManager events;
	
	public EventPublisher(EventsManager events){
		this.events = events;
	}
	
	public void raiseEvent(final SpartanEvent event) {
		new Thread() {
            @Override
            public void run() {
            	try {
					raise(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        }.start();
    }

	@SuppressWarnings("rawtypes")
	private void raise(final SpartanEvent event) throws Exception {
        for (SpartanListener handler : this.events.getHandlers()) {
            Method[] methods = handler.getClass().getMethods();
            for (int i = 0; i < methods.length; ++i) {
                EventHandler eventHandler = methods[i].getAnnotation(EventHandler.class);
                if (eventHandler != null) {
					Class[] methodParams = methods[i].getParameterTypes();

                    if (methodParams.length < 1){
                    	throw new Exception("Invalid Event Method Parameters ("+methods[i].getClass().getSimpleName()+":"+methods[i].getName()+")!");
                    }
	                        
                    if (!event.getClass().getSimpleName().equals(methodParams[0].getSimpleName())){
                    	continue;
                    }
	                    
                	try {
                        methods[i].invoke(handler, event);
                    } catch (Exception e) {
	                        System.err.println(e);
                    }
                }
            }
        }
    }
}
