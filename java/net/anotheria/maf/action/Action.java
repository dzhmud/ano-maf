package net.anotheria.maf.action;

import net.anotheria.maf.bean.FormBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * An action is the base of the MAF. Everything that is mapped to a request path must be an action. Actions are singletons (or at least the framework knows only one instance of them).
 * @author lrosenberg
 *
 */
public interface Action {
	/**
	 * Called by the framework prior to call to the execute. Useful for action hierarchies to put common activities (authorisation checks etc) into classes higher in the class hierarchy.
	 * @param mapping
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	void preProcess(ActionMapping mapping, HttpServletRequest req, HttpServletResponse res) throws Exception;
	/**
	 * Called by the framework. This is the method where you implement controller-logic (mvc) in your action.  
	 * @param mapping action mapping
	 * @param formBean backing bean
	 * @param req http request
	 * @param res http response
	 * @return a forward to another action or jsp for view rendering.
	 * @throws Exception any exception
	 */
	ActionCommand execute(ActionMapping mapping, FormBean formBean, HttpServletRequest req, HttpServletResponse res) throws Exception;
	/**
	 * Called by the framework after call to the execute.
	 * @param mapping
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	void postProcess(ActionMapping mapping, HttpServletRequest req, HttpServletResponse res) throws Exception; 
}
