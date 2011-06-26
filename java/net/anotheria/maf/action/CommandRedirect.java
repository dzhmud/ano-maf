package net.anotheria.maf.action;

public class CommandRedirect extends ActionCommand{
	private String target;
	private int code;
	
	public CommandRedirect(String name, String aTarget, int aCode){
		super(name);
		target = aTarget;
		code = aCode;
	}
	
	public CommandRedirect(String name, String target){
		this(name, target, 302);
	}
	
	@Override public String toString(){
		return getName()+" to "+getTarget()+" via "+getCode();
	}

	public String getTarget() {
		return target;
	}

	public int getCode() {
		return code;
	}
	
}
