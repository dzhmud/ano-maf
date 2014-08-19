package net.anotheria.maf.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract action class which can be used as parent of all action classes.
 * @author another
 *
 */
public abstract class AbstractAction implements Action{

	@Override
	public void preProcess(ActionMapping mapping, HttpServletRequest req, HttpServletResponse res) {
		
	}

	@Override
	public void postProcess(ActionMapping mapping, HttpServletRequest req, HttpServletResponse res) {
		
	}

}
