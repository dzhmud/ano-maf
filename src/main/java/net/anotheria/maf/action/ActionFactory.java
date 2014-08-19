package net.anotheria.maf.action;

/**
 * Actionfactory is used to create action objects.
 */
public interface ActionFactory {
	/**
	 * Creates a new action for the given type.
	 * @param actionType
	 * @return
	 * @throws ActionFactoryException
	 */
	Action getInstanceOf(String actionType) throws ActionFactoryException;
}
