package net.anotheria.maf.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractAction implements Action{

	@Override
	public void preProcess(ActionMapping mapping, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		
	}

	@Override
	public void postProcess(ActionMapping mapping, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		
	}

}
