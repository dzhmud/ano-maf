package net.anotheria.maf.action;

/**
 * A command that is returned by the action and signalizes to the maf filter what to do next.
 * @author lrosenberg
 *
 */
public class ActionCommand {
	/**
	 * Name of the command.
	 */
	private final String name;

	/**
	 * Creates ActionCommand with given name.
	 * @param aName name of the ActionCommand.
	 */
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
