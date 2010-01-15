package net.anotheria.maf.action;

public class ActionForward {
	/**
	 * Name of the forward.
	 */
	private String name;  
	/**
	 * Target path of the forward.
	 */
	private String path;
	
	public ActionForward(String aName, String aPath){
		name = aName;
		path = aPath;
	}
	  
	@Override public String toString(){
		return name+"->"+path;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPath(){
		return path;
	}
}
