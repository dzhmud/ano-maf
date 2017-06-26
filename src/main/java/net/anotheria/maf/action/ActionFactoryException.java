package net.anotheria.maf.action;

/**
 * Exception class for the ActionFactory.
 */
public class ActionFactoryException extends Exception {

	/**
	 * Basic serialVersionUID variable.
	 */
	private static final long serialVersionUID = 738036982942005924L;

	/**
	 * Creates a new ActionFactoryException.
	 * @param source source of created ActionFactoryException.
	 */
	public ActionFactoryException(Exception source) {
		super("Action instantiation failed because: " + source.getMessage(), source);
	}
}
