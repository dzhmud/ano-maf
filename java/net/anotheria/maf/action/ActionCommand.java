package net.anotheria.maf.action;

public class ActionCommand {
	/**
	 * Name of the command.
	 */
	private String name;  
	
	public ActionCommand(String aName){
		name = aName;
	}
	
	public String getName(){
		return name;
	}
	
	@Override public String toString(){
		return "command "+getName();
	}
}
