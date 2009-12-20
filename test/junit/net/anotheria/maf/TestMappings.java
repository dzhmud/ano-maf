package net.anotheria.maf;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

import org.junit.Test;

public class TestMappings {
	@Test public void xyz() throws Exception{
		
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
	
}
