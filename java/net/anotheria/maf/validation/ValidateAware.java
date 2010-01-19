package net.anotheria.maf.validation;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Custom error handler used to process validation errors,
 * action should implement this interface to customize error handling.
 * <p/>
 *
 * @author vitaliy
 * @version 1.0
 *          Date: Jan 16, 2010
 *          Time: 9:23:55 PM
 */
public interface ValidateAware {

	/**
	 * process occur error
	 *
	 * @param req servlet request
	 * @param errors collected errors
	 */
	void processErrors(HttpServletRequest req, List<ValidationError> errors);
}