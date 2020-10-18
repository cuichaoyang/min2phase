package anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Created by Intellij IDEA.
 *
 * @author cuiguiyang
 * @since 2020/10/18 22:21
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldAnno {

    String value() default "Field Annotation";

}
