package net.anotheria.maf;

/**
 * An action is the base of the MAF. Everything that is mapped to a request path must be an action. Actions are singletons (or at least the framework knows only one instance of them).
 * @author vitaliy
 *
 */
public interface CustomAction {

	/**
	 * Called by the framework. This is the method where you implement controller-logic (mvc) in your action.
	 * @param mapping action mapping
	 * @param formBean backing bean
     * @return a forward to another action or jsp for view rendering.
	 * @throws Exception occurs if error happened
	 */
	ActionForward execute(ActionMapping mapping, IFormBean formBean) throws Exception;


}