package net.anotheria.maf.validation;

/**
 * Validation error.
 *
 * <p>Used to pass.</p>
 *
 * @author vitaliy
 * @version 1.0
 *          Date: Jan 17, 2010
 *          Time: 7:18:20 PM
 */
public class ValidationError {
	/**
	 * Field in which the error occurred.
	 */
	private final String field;

	/**
	 * A debug message for the developer.
	 */
	private final String message;

	/**
	 * The key with error description.
	 */
	private final String key;

	public ValidationError(String field, String message, String key) {
		this.field = field;
		this.message = message;
		this.key = key;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

	public String getKey() {
		return key;
	}

	@Override
	public String toString() {
		return "ValidationError{" +
				"field='" + field + '\'' +
				", message='" + message + '\'' +
				", key='" + key + '\'' +
				'}';
	}
}
