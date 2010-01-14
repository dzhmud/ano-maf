package net.anotheria.maf.util;

import net.anotheria.util.mapper.ValueObjectMapperUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Model Object mapper.
 * <p/>                            	
 * <P>Used to map request parameters to to model bean.
 * <p/>
 *
 * @author vitaliy
 * @version 1.0
 *          Date: Jan 12, 2010
 *          Time: 1:42:18 AM
 */
public final class ModelObjectMapper {

	/**
	 * Default hidden constructor
	 */
	private ModelObjectMapper() {
	}

	/**
	 * Map request parameters to model bean.
	 * </p>
	 * <p>
	 * Internal model bean content can be mapped using Dozer notation
	 * (arrays, collections) for parameter name.
	 * </p>
	 *
	 * @param req http request object
	 * @param destination given model bean
	 */
	public static void map(final HttpServletRequest req, final Object destination) {
		final Map<String, Object> parameterMap = new HashMap<String, Object>(req.getParameterMap());
		ValueObjectMapperUtil.map(parameterMap, destination);
	}
}
