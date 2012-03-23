package net.anotheria.maf.annotation;

/**
 * @author Kirill Reviakin
 */
public @interface CommandRedirectAnnotation {
    String name();
    String target();
    int code() default 302;
}
