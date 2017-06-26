package net.anotheria.maf.action;

/**
 * Actionfactory is used to create action objects.
 */
public interface ActionFactory {
	/**
	 * Creates a new action for the given type.
	 * @param actionType type of the Action.
	 * @return created Action instance of the given type.
	 * @throws ActionFactoryException if Action creation failed.
	 */
	Action getInstanceOf(String actionType) throws ActionFactoryException;
}
