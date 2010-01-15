package net.anotheria.maf;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

import net.anotheria.maf.action.ActionForward;
import net.anotheria.maf.action.ActionMapping;
import net.anotheria.maf.action.ActionMappings;
import net.anotheria.maf.action.ActionMappingsConfigurator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMappings {
	@Before public void setup() throws Exception{
		
		MAFFilter filter = new MAFFilter(){
			protected List<ActionMappingsConfigurator> getConfigurators(){
				ArrayList<ActionMappingsConfigurator> configurators = new ArrayList<ActionMappingsConfigurator>();
				configurators.add(new ActionMappingsConfigurator() {
					
					@Override
					public void configureActionMappings() {
						ActionMappings.addMapping("simple", "test.SimpleClass", new ActionForward("simple", "Simple.jsp"));

						ActionMappings.addMapping("multi", "test.MultiClass", 
								new ActionForward("varianta", "VariantA.jsp"),
								new ActionForward("variantb", "VariantB.jsp"),
								new ActionForward("variantc", "VariantC.jsp")
						);
						
						ActionMappings.addAlias("verysimple", "simple");
						ActionMappings.addAlias("notverymulti", "multi");
					
					}
				});
				return configurators;
			}
		};
		
		FilterConfig config = new FilterConfig() {
			
			@Override
			public ServletContext getServletContext() {
				return null;
			}

			@Override
			public Enumeration<?> getInitParameterNames() {
				return null;
			}
			
			@Override
			public String getInitParameter(String arg0) {
				return null;
			}
			
			@Override
			public String getFilterName() {
				return null;
			}
		};
		
		filter.init(config);
		
	}
	
	@Test public void testMappings(){
		assertNotNull(ActionMappings.findMapping("simple"));
		assertNotNull(ActionMappings.findMapping("multi"));
		
		assertNull(ActionMappings.findMapping("nonexisting"));
		
		assertNotNull(ActionMappings.findMapping("simple").findForward("simple"));
		assertNull(ActionMappings.findMapping("simple").findForward("not-existing"));
		
	}
	
	@Test public void testAlias(){
		assertNotNull(ActionMappings.findMapping("verysimple"));
		assertNotNull(ActionMappings.findMapping("simple").findForward("simple"));
		assertNull(ActionMappings.findMapping("simple").findForward("not-existing"));
	}
	
	@Test public void testForward(){
		ActionMapping mapping = ActionMappings.findMapping("multi");
		ActionForward f1 = mapping.findForward("varianta");
		ActionForward f1_1 = mapping.findForward("varianta");
		ActionForward f2 = mapping.findForward("variantb");
		ActionForward f3 = mapping.findForward("variantc");
		ActionForward f4 = mapping.findForward("not-existing");
		
		assertEquals(f1, f1_1);
		assertFalse(f1.equals(f2));
		assertFalse(f1.equals(f3));
		assertFalse(f1.equals(f4));
		
	}


}
