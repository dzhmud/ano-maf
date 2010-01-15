package net.anotheria.maf.util;

import net.anotheria.maf.action.Action;
import net.anotheria.maf.action.ActionMapping;
import net.anotheria.maf.bean.FormBean;
import net.anotheria.maf.bean.RequestMapBean;
import net.anotheria.maf.bean.annotations.Form;
import net.anotheria.maf.bean.annotations.RequestMap;
import net.anotheria.util.mapper.ValueObjectMapperUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Model Object mapper.
 * <p/>
 *
 * @author vitaliy
 * @version 1.0
 *          Date: Jan 12, 2010
 *          Time: 1:42:18 AM
 */
public final class ModelObjectMapper {

	/**
	 * Mapper log.
	 */
	private static final Logger LOGGER = Logger.getLogger(ModelObjectMapper.class);

	/**
	 * action execute method name.
	 */
	private static final String EXECUTE = "execute";

	/**
	 * Default constructor.
	 */
	private ModelObjectMapper() {
	}

	/**
	 * Map request parameters to model bean.
	 * </p>
	 * <p>
	 * Internal model bean content can be mapped using Dozer notation
	 * (arrays, collections) for parameter name
	 * </p>
	 *
	 * @param req		 http request object
	 * @param destination given model bean
	 */
	public static void map(final HttpServletRequest req, final Object destination) {
		final Map<String, String> parameterMap = new HashMap<String, String>();

		for (Object key : req.getParameterMap().keySet()) {
			String reqKey = String.valueOf(key);
			parameterMap.put(reqKey, req.getParameter(reqKey));
		}

		ValueObjectMapperUtil.map(parameterMap, destination);
	}

	/**
	 * Map http request to model object by action annotations.
	 *
	 * @param req	http request
	 * @param action given action
	 * @return instantiated bean
	 */
	public static FormBean getModelObjectMapped(final HttpServletRequest req, final Action action) {
		try {
			final Method executeMethod = action.getClass().getDeclaredMethod(EXECUTE, ActionMapping.class, FormBean.class,
					HttpServletRequest.class, HttpServletResponse.class);
			final Annotation[] formAnnotations = executeMethod.getParameterAnnotations()[1];

			for (Annotation formAnnotation : formAnnotations) {
				if (Form.class.equals(formAnnotation.annotationType())) {
					final Form bean = (Form) formAnnotation;
					final FormBean formBean = bean.value().newInstance();
					ModelObjectMapper.map(req, formBean);
					return formBean;
				} else if (RequestMap.class.equals(formAnnotation.annotationType())) {
					final Map<String, Object> parameters = new HashMap<String, Object>();
					ModelObjectMapper.map(req, parameters);

					final Enumeration headerNames = req.getHeaderNames();
					final Map<String, String> headerMap = new HashMap<String, String>();
					while (headerNames.hasMoreElements()) {
						final String name = (String) headerNames.nextElement();
						headerMap.put(name, req.getHeader(name));
					}

					final Map<String, String> cookieMap = new HashMap<String, String>();
					for (Cookie cookie : req.getCookies()) {
						cookieMap.put(cookie.getName(), cookie.getValue());
					}

					return new RequestMapBean(parameters, cookieMap, headerMap);
				}

			}
		} catch (NoSuchMethodException e) {
			LOGGER.error(e);
		} catch (InstantiationException e) {
			LOGGER.error(e);
		} catch (IllegalAccessException e) {
			LOGGER.error(e);
		}
		return null;
	}
}
