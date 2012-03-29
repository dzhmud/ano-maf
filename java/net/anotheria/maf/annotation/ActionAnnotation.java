package net.anotheria.maf.annotation;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation to mark a MAF action class to be mapped automatically, if configuration by annotations setting is set.
 *
 * @author Kirill Reviakin
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SupportedSourceVersion(value = SourceVersion.RELEASE_6)
public @interface ActionAnnotation {
    /**
     * The path the action must be mapped to.
     *
     * @return the path.
     */
    String path();

    /**
     * The list of possible forwards of this action.
     *
     * @return the forwards list.
     */
    CommandForwardAnnotation[] forwards() default {};

    /**
     * The list of possible redirects of this action.
     *
     * @return the redirects list.
     */
    CommandRedirectAnnotation[] redirects() default {};

    /**
     * Context of the action mapping. Used to identify corresponding MAF Filter.
     * If MAF Filter is configured without specified context - leave this not set.
     *
     * @return the context of the action mapping. Empty string by default.
     */
    String context() default "";
}
