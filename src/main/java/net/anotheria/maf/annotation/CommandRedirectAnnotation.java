package net.anotheria.maf.annotation;

/**
 * The annotation which describes the action redirect. This annotation should not be used out of ActionAnnotation context.
 *
 * @author Kirill Reviakin
 */
public @interface CommandRedirectAnnotation {
    /**
     * The name of the command.
     *
     * @return the name.
     */
    String name();

    /**
     * The target of the redirect.
     *
     * @return the target.
     */
    String target();

    /**
     * The HTTP redirect code. 302 by default.
     *
     * @return the redirect code.
     */
    int code() default 302;
}
