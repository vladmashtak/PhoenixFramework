package org.phoenixframework.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation allows to define a set of named queries for a specified domain class.
 *
 * @author Oleg Marchenko
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface NamedQueries {

    NamedQuery[] value() default {};
}
