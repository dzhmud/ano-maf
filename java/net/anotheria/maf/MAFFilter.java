	package net.anotheria.maf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.anotheria.maf.action.*;
import net.anotheria.maf.bean.FormBean;
import net.anotheria.maf.util.FormObjectMapper;
import net.anotheria.maf.validation.ValidationAware;
import net.anotheria.maf.validation.ValidationError;
import net.anotheria.maf.validation.ValidationException;
import org.apache.log4j.Logger;

import net.java.dev.moskito.core.predefined.Constants;
import net.java.dev.moskito.core.predefined.FilterStats;
import net.java.dev.moskito.core.predefined.ServletStats;
import net.java.dev.moskito.core.producers.IStats;
import net.java.dev.moskito.core.producers.IStatsProducer;
import net.java.dev.moskito.core.registry.ProducerRegistryFactory;
import net.java.dev.moskito.core.stats.Interval;

/**
 * MAFFilter is the dispatcher filter of the MAF. We are using a Filter instead of Servlet to be able to inject MAF parts in huge we-map-everything-through-one-servlet systems (aka spring).
 * In particular it is useful to inject moskito-webui which is maf-based into an existing spring application.
 * @author lrosenberg
 *
 */
public class MAFFilter implements Filter, IStatsProducer{

	/**
	 * Stats for get request.
	 */
	private ServletStats getStats;
	/**
	 * List of stats as required by stats producer interface.
	 */
	private List<IStats> cachedStatList;
	/**
	 * Path on which to react.
	 */
	private String path;
	
	/**
	 * Log.
	 */
	private static Logger log = Logger.getLogger(MAFFilter.class);
	
	@Override
	public void destroy() {
		
	}

	@Override public void init(FilterConfig config) throws ServletException {
		getStats          = new FilterStats("cumulated", getMonitoringIntervals());
		cachedStatList = new ArrayList<IStats>();
		cachedStatList.add(getStats);
		ProducerRegistryFactory.getProducerRegistryInstance().registerProducer(this);
		
		path = config.getInitParameter("path");
		if (path==null)
			path = "";
		
		List<ActionMappingsConfigurator> configurators = getConfigurators();
		for (ActionMappingsConfigurator configurator : configurators){
			try{
				configurator.configureActionMappings();
			}catch(Throwable t){
				log.fatal("Configuration failed by configurator "+configurator, t);
			}
		}
	}

	@Override public void doFilter(ServletRequest sreq, ServletResponse sres,	FilterChain chain) throws IOException, ServletException {
		if (!(sreq instanceof HttpServletRequest)){
			chain.doFilter(sreq, sres);
			return;
		}
		
		HttpServletRequest req = (HttpServletRequest)sreq;
		HttpServletResponse res = (HttpServletResponse)sres;
		String servletPath = req.getServletPath();
		if (servletPath==null || servletPath.length()==0)
			servletPath = req.getPathInfo();
		
		if (!(servletPath==null)){
			if (path.length()==0 || servletPath.startsWith(path)){
				doPerform(req, res, servletPath);
				//optionally allow the chain to run further?
				return;
			}
		}

		chain.doFilter(req, res);
			
	}
 
	private void doPerform(HttpServletRequest req, HttpServletResponse res, String servletPath) throws ServletException, IOException {
		getStats.addRequest();
		long startTime = System.nanoTime();
		try{
			String actionPath = servletPath.substring(path.length());
			if (actionPath==null || actionPath.length()==0){
				if (getDefaultActionName()!=null)
					actionPath = getDefaultActionName();
			}
			ActionMapping mapping = ActionMappings.findMapping(actionPath);
			if (mapping == null){
				res.sendError(404, "Action "+actionPath+" not found.");
				return;
			}
			Action action;
			try{
				action = ActionFactory.getInstanceOf(mapping.getType());
			}catch(ActionFactoryException e){
				throw new ServletException("Can't instantiate "+mapping.getType()+" for path: "+actionPath+", because: "+e.getMessage(), e);
			}
			
			ActionForward forward = null;
			try{
				action.preProcess(mapping, req, res);
				FormBean bean = FormObjectMapper.getModelObjectMapped(req, action);
				if(bean != null){
					List<ValidationError> errors = FormObjectMapper.validate(req, bean);
					if(!errors.isEmpty()) {
						if(action instanceof ValidationAware) {
							forward = ((ValidationAware)action).executeOnValidationError(mapping, bean, errors, req, res);
						}else{
							throw new ServletException("Mapper validation failed: "+errors);
						}
					}
				}
				if (forward==null)
					forward = action.execute(mapping, bean, req, res);

				action.postProcess(mapping, req, res);
			}catch(ValidationException e){
				throw new ServletException("Error in processing: "+e.getMessage(), e);
			}catch(Exception e){
				throw new ServletException("Error in processing: "+e.getMessage(), e);
			}
			
			if (forward!=null){
				req.getRequestDispatcher(forward.getPath()).forward(req, res);
			}	

		}catch(ServletException e){
			getStats.notifyServletException();
			throw e;
		}catch(IOException e){
			getStats.notifyIOException();
			throw e;
		}catch(RuntimeException e){
			getStats.notifyRuntimeException();
			throw e;
		}catch(Error e){
			getStats.notifyError();
			throw e;
		}finally{
			long executionTime = System.nanoTime()-startTime;
			getStats.addExecutionTime(executionTime);
			getStats.notifyRequestFinished();
		}
	}


	@Override public List<IStats> getStats() {
		return cachedStatList;
	}

	/**
	 * Override this method to setup custom monitoring intervals.
	 * @return
	 */
	protected Interval[] getMonitoringIntervals(){
		return Constants.DEFAULT_INTERVALS;
	}
	
	/**
	 * Override this operation to perform access control to moskitoUI. Default is yes (true).
	 * @param req the HttpServletRequest
	 * @param res the HttpServletResponse
	 * @return true if the operation is allowed (post or get).
	 */
	protected boolean operationAllowed(HttpServletRequest req, HttpServletResponse res){
		return true;
	}

	@Override public String getProducerId() {
		return "maffilter";
	}

	@Override public String getSubsystem() {
		return "maf";
	}

	@Override public String getCategory() {
		return "filter";
	}
	
	/**
	 * Overwrite this method and return configurators for your project.
	 * @return
	 */
	protected List<ActionMappingsConfigurator> getConfigurators(){
		return new ArrayList<ActionMappingsConfigurator>();
	}
	
	/**
	 * if not null an empty path is replaced by this default action name, for example 'index'.
	 * @return
	 */
	protected String getDefaultActionName(){
		return null;
	}
	
}
