package net.anotheria.maf.action;

/**
 * This commands sends forces the filter to execute a forward to the given path. 
 * @author lrosenberg
 */
public class CommandForward extends ActionCommand{
	/**
	 * Target path of the forward.
	 */
	private String path;
	
	public CommandForward(String aName, String aPath){
		super(aName);
		path = aPath;
	}
	  
	@Override public String toString(){
		return getName()+"->"+path;
	}
	
	public String getPath(){
		return path;
	}

}
