package fr.iambluedev.spartan.node.managers;

import java.util.ArrayList;
import java.util.List;

import fr.iambluedev.spartan.api.events.SpartanListener;

public class EventsManager {
	
	private EventPublisher eventPublisher;
	private List<SpartanListener> handlers = new ArrayList<SpartanListener>();
	
	public EventsManager(){
		this.eventPublisher = new EventPublisher(this);
	}
	
	public void register(SpartanListener clazz){
		if(!this.contains(clazz)){
			this.getHandlers().add(clazz);
		}
	}

	public void unregister(SpartanListener clazz){
		if(this.contains(clazz)){
			this.getHandlers().remove(clazz);
		}
	}
	
	public void clear(){
		this.getHandlers().clear();
	}
	
	public boolean contains(SpartanListener clazz){
		return this.getHandlers().contains(clazz);
	}
	
	public List<SpartanListener> getHandlers() {
        return this.handlers;
    }
	
	public EventPublisher getPublisher(){
		return this.eventPublisher;
	}

	public void setPublisher(EventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	public void setHandlers(List<SpartanListener> handlers) {
		this.handlers = handlers;
	}
}
