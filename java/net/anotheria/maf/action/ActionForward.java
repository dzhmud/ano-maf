package net.anotheria.maf.action;

/**
 * Return object of an action execution. Defines the processing of action results.
 * @author lrosenberg
 *
 */
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
