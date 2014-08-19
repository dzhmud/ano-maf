package net.anotheria.maf.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.anotheria.maf.bean.FormBean;

public class ForwardAction extends AbstractAction{
	@Override
	public ActionCommand execute(ActionMapping mapping, FormBean bean, HttpServletRequest req, HttpServletResponse res) {
		return mapping.findCommand("forward");
	}
}
