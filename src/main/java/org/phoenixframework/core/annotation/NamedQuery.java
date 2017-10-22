package org.phoenixframework.core.annotation;

import java.lang.annotation.*;

/**
 * @author Oleg Marchenko
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(value = NamedQueries.class)
public @interface NamedQuery {

    String name();
    String query();
}
