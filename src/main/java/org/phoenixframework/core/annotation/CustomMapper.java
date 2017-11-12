package org.phoenixframework.core.annotation;

import org.phoenixframework.core.mapper.CustomResultMapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Oleg Marchenko
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CustomMapper {

    Class<? extends CustomResultMapper<?>> value();
}
