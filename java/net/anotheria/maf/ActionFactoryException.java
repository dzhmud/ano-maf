package net.anotheria.maf;

public class ActionFactoryException extends Exception {

	/**
	 * Basic serialVersionUID variable.
	 */
	private static final long serialVersionUID = 738036982942005924L;

	public ActionFactoryException(Exception source) {
		super("Action instantiation failed because: " + source.getMessage(), source);
	}
}
