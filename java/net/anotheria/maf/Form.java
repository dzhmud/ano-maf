package net.anotheria.maf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to define backing bean type and mapping.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER })
public @interface Form {
	/**
	 * Bean form class name.
	 *
	 * @return return backing bean class
	 */
    Class<? extends FormBean> value();
}
