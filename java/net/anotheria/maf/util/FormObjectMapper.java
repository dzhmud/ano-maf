package net.anotheria.maf.util;

import net.anotheria.maf.action.Action;
import net.anotheria.maf.action.ActionMapping;
import net.anotheria.maf.bean.FormBean;
import net.anotheria.maf.bean.RequestMapBean;
import net.anotheria.maf.bean.annotations.Form;
import net.anotheria.maf.bean.annotations.RequestMap;
import net.anotheria.maf.validation.ValidationError;
import net.anotheria.maf.validation.Validator;
import net.anotheria.maf.validation.annotations.ValidateCustom;
import net.anotheria.maf.validation.annotations.ValidateNotEmpty;
import net.anotheria.util.mapper.PopulateMe;
import net.anotheria.util.mapper.PopulateWith;
import net.anotheria.util.mapper.ValueObjectMapperUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
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
public final class FormObjectMapper {

	/**
	 * Mapper log.
	 */
	private static final Logger LOGGER = Logger.getLogger(FormObjectMapper.class);

	/**
	 * action execute method name.
	 */
	private static final String EXECUTE = "execute";

	/**
	 * Default constructor.
	 */
	private FormObjectMapper() {
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
        final Map<String, String> parameterMap = getRequestParameterMap(req);

		final Class destinationClass = destination.getClass();
		final Field[] fields = destinationClass.getDeclaredFields();
		for (Field field : fields) {
			final PopulateWith populateWith = field.getAnnotation(PopulateWith.class);
			if (populateWith != null) {
				try {
					field.setAccessible(true);
					field.set(destination, parameterMap.get(populateWith.value()));
				} catch (IllegalAccessException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
			final PopulateMe populateMe = field.getAnnotation(PopulateMe.class);
			if (populateMe != null) {
				try {
					field.setAccessible(true);
					field.set(destination, parameterMap.get(field.getName()));
				} catch (IllegalAccessException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
	}

    private static Map<String, String> getRequestParameterMap(HttpServletRequest req) {
        final Map<String, String> parameterMap = new HashMap<String, String>();

        for (Object key : req.getParameterMap().keySet()) {
            String reqKey = String.valueOf(key);
            parameterMap.put(reqKey, req.getParameter(reqKey));
        }
        return parameterMap;
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
			final Method executeMethod = action.getClass().getMethod(EXECUTE, ActionMapping.class, FormBean.class,
					HttpServletRequest.class, HttpServletResponse.class);
			final Annotation[] formAnnotations = executeMethod.getParameterAnnotations()[1];

			for (Annotation formAnnotation : formAnnotations) {
				if (Form.class.equals(formAnnotation.annotationType())) {
					final Form bean = (Form) formAnnotation;
					final FormBean formBean = bean.value().newInstance();
                                        final Map<String, String> parameterMap = getRequestParameterMap(req);					
                                        ValueObjectMapperUtil.map(parameterMap, formBean);
					return formBean;
				} else if (RequestMap.class.equals(formAnnotation.annotationType())) {
					final Map<String, Object> parameters = new HashMap<String, Object>();
					FormObjectMapper.map(req, parameters);

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

	/**
	 * Validate mapped bean according defined preconditions.
	 *
	 * @param req  http request
	 * @param bean backing bean
	 */
	public static List<ValidationError> validate(final HttpServletRequest req, final Object bean) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		final Class beanClass = bean.getClass();
		final Field[] fields = beanClass.getDeclaredFields();
		for (Field field : fields) {
			try {
				final ValidateNotEmpty validateNotEmpty = field.getAnnotation(ValidateNotEmpty.class);
				if (validateNotEmpty != null) {
					field.setAccessible(true);
					Object value = field.get(bean);
					if (value == null
							|| String.valueOf(value).isEmpty()) {
						errors.add(new ValidationError(field.getName(), validateNotEmpty.key(), validateNotEmpty.message()));
					}
				}
				final ValidateCustom validateCustom = field.getAnnotation(ValidateCustom.class);
				if (validateCustom != null) {
					field.setAccessible(true);
					Object value = field.get(bean);


					Validator validator = validateCustom.validator().newInstance();
					//noinspection unchecked
					if (!validator.validate(value)) {
						errors.add(new ValidationError(field.getName(), validateCustom.key(), validateCustom.message()));
					}
				}
			} catch (IllegalAccessException e) {
				LOGGER.error(e);
			} catch (InstantiationException e) {
				LOGGER.error(e);
			}
		}
		return errors;
	}
}
