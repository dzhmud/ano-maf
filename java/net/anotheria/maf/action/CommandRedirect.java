package net.anotheria.maf.action;

public class CommandRedirect extends ActionCommand implements Cloneable{
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
	
	public CommandRedirect clone(){
		try{
			return (CommandRedirect)super.clone();
		}catch(CloneNotSupportedException e){
			throw new AssertionError("Can't happen");
		}
	}
	
	public CommandRedirect addParameter(String name, String value){
		CommandRedirect ret = clone();
		if (ret.target.indexOf('?')==-1)
			ret.target += "?";
		else
			ret.target += "&";
		ret.target += name+"="+value;
		return ret;
	}
	
}
