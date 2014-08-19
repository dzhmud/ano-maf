package net.anotheria.maf.action;

/**
 * Interface for a helper class, that provides configuration of mappings. 
 * @author lrosenberg
 *
 */
public interface ActionMappingsConfigurator {
	/**
	 * Called to add mapping configuration.
	 */
	void configureActionMappings(ActionMappings mappings);
}
