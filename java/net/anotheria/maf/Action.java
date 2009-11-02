package net.anotheria.maf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	void preProcess(ActionMapping mapping, HttpServletRequest req, HttpServletResponse res) throws Exception;
	ActionForward execute(ActionMapping mapping, HttpServletRequest req, HttpServletResponse res) throws Exception;
	void postProcess(ActionMapping mapping, HttpServletRequest req, HttpServletResponse res) throws Exception; 
}
