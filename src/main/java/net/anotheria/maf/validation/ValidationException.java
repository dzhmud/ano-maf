package net.anotheria.maf.validation;

import java.util.List;

/**
 * Validation error.
 *
 * <P>Used to pass.
 *
 * @author vitaliy
 * @version 1.0
 *          Date: Jan 17, 2010
 *          Time: 7:18:20 PM
 */
public class ValidationException extends Exception {
	/**
	 * SerialUID.
	 */
	private static final long serialVersionUID = 1L;
	private final List<ValidationError> errors;

	public ValidationException(String s, List<ValidationError> errors) {
		super(s);
		this.errors = errors;
	}
	
	public List<ValidationError> getErrors(){
		return errors;
	}
}