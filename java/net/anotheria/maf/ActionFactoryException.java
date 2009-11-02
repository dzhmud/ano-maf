package net.anotheria.maf;

public class ActionFactoryException extends Exception{
	public ActionFactoryException(Exception source){
		super("Action instantiation failed because: "+source.getMessage(), source);
	}
}
