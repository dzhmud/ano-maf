package net.anotheria.maf.annotation;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation which describes the action forward. This annotation should not be used out of ActionAnnotation context.
 *
 * @author Kirill Reviakin
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SupportedSourceVersion(value = SourceVersion.RELEASE_6)
public @interface CommandForwardAnnotation {
    /**
     * The name of the command.
     *
     * @return the name.
     */
    String name();

    /**
     * The path of the forward.
     *
     * @return the path.
     */
    String path();
}
