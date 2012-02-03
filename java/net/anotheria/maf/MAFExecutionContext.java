package net.anotheria.maf;

import net.anotheria.maf.action.ActionMappings;

public class MAFExecutionContext {
	
	private MAFFilter filter;
	private ActionMappings mappings;
	
	private static final InheritableThreadLocal<MAFExecutionContext> context = new InheritableThreadLocal<MAFExecutionContext>(){
		@Override
		protected synchronized MAFExecutionContext initialValue() {
			return new MAFExecutionContext();
		}
	};
	
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
