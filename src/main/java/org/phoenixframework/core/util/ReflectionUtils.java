package org.phoenixframework.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Oleg Marchenko
 */

public class ReflectionUtils {

    public static <T> T newInstance(Class<T> classType) {
        try {
            return classType.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalStateException("Cannot instantiate an object by type \"" + classType.getSimpleName() + "\"", e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Cannot access to instantiate an object by type \"" + classType.getSimpleName() + "\"", e);
        }
    }

    public static Object invokeMethod(Object object, Method method, Object... args) {
        try {
            return method.invoke(object, args);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Cannot access method \"" + object.getClass().getSimpleName() + "." + method.getName() + "(" + Arrays.toString(args) + ")\"", e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException("Cannot invoke method \"" + object.getClass().getSimpleName() + "." + method.getName() + "(" + Arrays.toString(args) + ")\"", e);
        }
    }

    public static void setValueToField(Object object, Field field, Object value) {
        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Cannot access field \"" + object.getClass().getSimpleName() + "." + field.getName() + " = " + value + "\"", e);
        }
    }

    public static Method getMethodByName(Class<?> clazz, String methodName, Class<?> argTypes) {
        try {
            return clazz.getMethod(methodName, argTypes);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("In class \"" + clazz.getSimpleName() + "\" is no such method \"" + methodName + "\"", e);
        }
    }

    private ReflectionUtils() {
    }
}
