package net.anotheria.maf.annotation;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation to mark a MAF action class to be mapped automatically, if configuration by annotations setting is set.
 * This annotation allows multiply mapping at once.
 *
 * @author Kirill Reviakin
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SupportedSourceVersion(value = SourceVersion.RELEASE_6)
public @interface ActionsAnnotation {
    /**
     * The list of the action mappings.
     * @return the action mappings list.
     */
    ActionAnnotation[] maps();
}
