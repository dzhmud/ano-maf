package net.anotheria.maf.util;

import net.anotheria.maf.Action;
import net.anotheria.maf.ActionMapping;
import net.anotheria.maf.Form;
import net.anotheria.maf.FormBean;
import net.anotheria.util.mapper.ValueObjectMapperUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
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

	private static final Logger LOGGER = Logger.getLogger(ModelObjectMapper.class);

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

	public static <T extends Action> FormBean getModelObjectMapped(final HttpServletRequest req, final T action) {
		try {
			Method executeMethod = action.getClass().getDeclaredMethod("execute", ActionMapping.class, FormBean.class);
			Annotation[] formAnnotations = executeMethod.getParameterAnnotations()[1];

			for (Annotation formAnnotation : formAnnotations) {
				if (Form.class.equals(formAnnotation.annotationType())) {
					Form bean = (Form) formAnnotation;
					FormBean formBean = bean.value().newInstance();
					ModelObjectMapper.map(req, formBean);
					return formBean;
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
