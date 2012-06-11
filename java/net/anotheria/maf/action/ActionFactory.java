package net.anotheria.maf.action;

public interface ActionFactory {
	Action getInstanceOf(String actionType) throws ActionFactoryException;
}
