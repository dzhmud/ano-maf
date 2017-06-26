package net.anotheria.maf;

import net.anotheria.maf.action.ActionMappings;

/**
 * A thread local object that contains information about currently active maf setup.
 * Its primary purpose is to allow multiple maf filters in same application.
 */
public class MAFExecutionContext {

	/**
	 * Filter that was activated by the servlet engine (by the path mapping).
	 */
	private MAFFilter filter;
	/**
	 * Current mappings.
	 */
	private ActionMappings mappings;
	/** InheritableThreadLocal that holds current MAFExecutionContext. */
	private static final InheritableThreadLocal<MAFExecutionContext> context = new InheritableThreadLocal<MAFExecutionContext>(){
		@Override
		protected synchronized MAFExecutionContext initialValue() {
			return new MAFExecutionContext();
		}
	};

	/**
	 * Get current MAFExecutionContext.
	 * @return current MAFExecutionContext.
	 */
	public static final MAFExecutionContext currentExecutionContext(){
		return context.get();
	}

	public MAFFilter getFilter() {
		return filter;
	}

	public void setFilter(MAFFilter filter) {
		this.filter = filter;
	}

	public ActionMappings getMappings() {
		return mappings;
	}

	public void setMappings(ActionMappings mappings) {
		this.mappings = mappings;
	}
}
