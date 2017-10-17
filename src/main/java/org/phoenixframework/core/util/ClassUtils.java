package org.phoenixframework.core.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Oleg Marchenko
 */

public final class ClassUtils {
    private static final char PACKAGE_SEPARATOR = '.';
    private static final char PATH_SEPARATOR = '/';
    private static final String CLASS_EXTENSION = ".class";

    public static List<Class<?>> loadClassesFromPackage(String packageToScan) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = resolveBasePackage(packageToScan);
        URL resource = loader.getResource(packagePath);
        if (resource == null) {
            throw new IllegalStateException("Cannot get resource by path \"" + packagePath + "\"");
        }

        File classesDirectory = new File(resource.getPath());
        String[] classesNames = classesDirectory.list();
        if (classesNames == null || classesNames.length == 0) {
            return Collections.emptyList();
        }

        List<Class<?>> classes = new ArrayList<>(classesNames.length);
        for (String className: classesNames) {
            if (className.endsWith(CLASS_EXTENSION)) {
                String classPath = packageToScan + PACKAGE_SEPARATOR +
                        className.substring(0, className.indexOf(PACKAGE_SEPARATOR));
                classes.add(loadClass(classPath));
            }
        }
        return classes;
    }

    public static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot load a class \"" + className + "\"", e);
        }
    }

    private static String resolveBasePackage(String basePackage) {
        return basePackage.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);
    }

    private ClassUtils() {
    }
}
