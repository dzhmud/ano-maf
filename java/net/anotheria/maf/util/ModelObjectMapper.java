package net.anotheria.maf.util;

import net.anotheria.util.mapper.ValueObjectMapperUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Guitar Model Object.
 * <p/>
 * <P>Various attributes of guitars, and related behaviour.
 * <p/>
 *
 * @author vitaliy
 * @version 1.0
 *          Date: Jan 12, 2010
 *          Time: 1:42:18 AM
 */
public final class ModelObjectMapper {

	/**
	 * Map request parameters to model bean.
	 * </p>
	 * <p>
	 * Internal model bean content can be mapped using Dozer notation
	 * (arrays, collections) for parameter name 
	 * </p>
	 *
	 * @param req http request object
	 * @param destination given model bean
	 */
	public static void map(HttpServletRequest req, Object destination) {
		final Map<String, Object> parameterMap = req.getParameterMap();
		ValueObjectMapperUtil.map(parameterMap, destination);
	}
}
