package net.anotheria.maf.action;

/**
 * Interface for a helper class, that provides configuration of mappings. 
 * @author lrosenberg
 *
 */
public interface ActionMappingsConfigurator {
	/**
	 * Called to add mapping configuration.
	 * @param mappings {@link ActionMappings} to configure.
	 */
	void configureActionMappings(ActionMappings mappings);
}
