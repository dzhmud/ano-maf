package net.anotheria.maf.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Validates that annotated field or parameter holds a number.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER })
public @interface ValidateNumber {
	
	/**
	 * Message custom key.
	 * @return message key.
	 */
	String key();

	/**
	 * Optional custom error message used if message by key isn't available.
	 * @return message string
	 */
	String message() default "";
	
	/**
	 * Boolean customizing if annotated field can be a fractional number.
	 * @return true if annotated field should represent float or double value, 
	 * false - if it should represent integral value;
	 */
	boolean fractional() default false;

}
