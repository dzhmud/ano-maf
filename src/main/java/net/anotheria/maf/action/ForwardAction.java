package net.anotheria.maf.action;

import net.anotheria.maf.bean.FormBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Simple action that allows to forward to a previously defined jsp.
 */
public class ForwardAction extends AbstractAction{
	@Override
	public ActionCommand execute(ActionMapping mapping, FormBean bean, HttpServletRequest req, HttpServletResponse res) {
		return mapping.findCommand("forward");
	}
}
