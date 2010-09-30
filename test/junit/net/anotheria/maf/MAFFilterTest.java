package net.anotheria.maf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import net.anotheria.maf.action.ActionForward;
import net.anotheria.maf.action.ActionMappings;
import net.anotheria.maf.action.ActionMappingsConfigurator;
import net.anotheria.maf.mocks.HttpServletRequestMockImpl;
import net.anotheria.maf.mocks.MockHttpSessionFactory;
import net.anotheria.maf.mocks.MockServletRequestFactory;

import org.junit.Before;
import org.junit.Test;

public class MAFFilterTest {
    private static final String CONTEXT_PATH = "/";
    private static final String SERVER_NAME = "localhost";


    private MAFFilter filter;
	@Before
    public void setup() throws Exception{

		filter = new MAFFilter(){
			protected List<ActionMappingsConfigurator> getConfigurators(){
				ArrayList<ActionMappingsConfigurator> configurators = new ArrayList<ActionMappingsConfigurator>();
				configurators.add(new ActionMappingsConfigurator() {

					@Override
					public void configureActionMappings() {
						ActionMappings.addMapping("simple", "test.SimpleClass", new ActionForward("simple", "Simple.jsp"));
						ActionMappings.addMapping("testAction", "net.anotheria.maf.TestCustomAction", new ActionForward("simple", "Simple.jsp"));

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

	@Test
    public void shouldMapAnnotatedFormBean() throws  IOException, ServletException {
        // given
        Map<String, String> params = new HashMap<String, String>();
		params.put("requestId", "1");
		params.put("subject", "7");
		Map<String, Object> attributes = new HashMap<String, Object>();
		HttpSession mockedSession = new MockHttpSessionFactory().createMockedSession();
        HttpServletRequestMockImpl request = MockServletRequestFactory.createMockedRequest(mockedSession, params, attributes, CONTEXT_PATH, SERVER_NAME,Locale.ENGLISH, 80);
		

		filter.doFilter(request, null, new FilterChain(){
            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
            }
        });



	}
}