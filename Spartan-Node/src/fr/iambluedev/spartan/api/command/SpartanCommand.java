package fr.iambluedev.spartan.api.command;

public abstract class SpartanCommand {
	
	private String name;
	
	public SpartanCommand(String name){
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return "SpartanCommand(name=" + this.getName() + ")";
	}
	
	public abstract void execute(String[] args);
}
