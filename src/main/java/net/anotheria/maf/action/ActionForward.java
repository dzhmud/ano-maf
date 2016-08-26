package net.anotheria.maf.action;

/**
 * Return object of an action execution. Defines the processing of action results.
 * @author lrosenberg
 * @deprecated use CommandForward instead.
 *
 */
public class ActionForward extends ActionCommand{
	/**
	 * Target path of the forward.
	 */
	private String path;
	
	public ActionForward(String aName, String aPath){
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
