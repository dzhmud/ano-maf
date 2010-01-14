package net.anotheria.maf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestCustomAction implements Action, CustomAction {

    @Override
    public ActionForward execute(ActionMapping mapping, HttpServletRequest req,
                                 HttpServletResponse res) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void postProcess(ActionMapping mapping, HttpServletRequest req,
                            HttpServletResponse res) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void preProcess(ActionMapping mapping, HttpServletRequest req,
                           HttpServletResponse res) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public ActionForward execute(ActionMapping mapping,
                                 @FormBean(TestBackingBean.class)
                                 IFormBean IForm) throws Exception {
        return null;
    }
}