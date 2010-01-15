package net.anotheria.maf.bean;

import java.util.Map;

/**
 * Marker interface used to define form backing bean.
 * <p/>
 *
 * @author vitaliy
 * @version 1.0
 *          Date: Jan 12, 2010
 *          Time: 1:42:18 AM
 */
public class RequestMapBean implements FormBean {

	/**
	 * Request parameters
	 */
	private final Map<String, Object> parameters;

	/**
	 * Cookie.
	 */
	private final Map<String, String> cookie;

	/**
	 * HTTP headers.
	 */
	private final Map<String, String> header;

	/**
	 * Default constructor
	 *
	 * @param parameters http parameters
	 * @param cookie http cookies
	 * @param header http headers
	 */
	public RequestMapBean(final Map<String, Object> parameters, final Map<String, String> cookie, final Map<String, String> header) {
		this.parameters = parameters;
		this.cookie = cookie;
		this.header = header;
	}

	/**
	 * @return get http parameters
	 */
	public final Map<String, Object> getParameters() {
		return parameters;
	}

	/**
	 *
	 * @return cookie collection.
	 */
	public final Map<String, String> getCookie() {
		return cookie;
	}

	/**
	 *
	 * @return http headers.
	 */
	public final Map<String, String> getHeader() {
		return header;
	}

	@Override
	public final String toString() {
		return "RequestMapBean{"
				+ "parameters=" + parameters
				+ ", cookie=" + cookie
				+ ", header=" + header
				+ '}';
	}
}