package org.phoenixframework.core.context.registry;

import org.phoenixframework.core.exception.PhoenixException;
import org.phoenixframework.core.mapper.ObjectTransformer;
import org.phoenixframework.core.mapper.ResultMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleg Marchenko
 */

public class DomainTransformerRegistry {

    private final Map<Class<?>, ObjectTransformer> registry = new HashMap<>();

    public ObjectTransformer getTransformer(Class<?> classType) {
        ObjectTransformer transformer = registry.get(classType);
        if (transformer == null) {
            throw new PhoenixException("ObjectTransformer for class \"" + classType.getSimpleName() + "\" is not registered");
        }
        return transformer;
    }

    @SuppressWarnings("unchecked")
    public <T> ResultMapper<T> getMapper(Class<T> classType) {
        ObjectTransformer transformer = registry.get(classType);
        if (transformer == null) {
            throw new PhoenixException("ResultMapper for class \"" + classType.getSimpleName() + "\" is not registered");
        }
        return (ResultMapper<T>) transformer;
    }

    public void addTransformer(Class<?> classType, ObjectTransformer transformer) {
        registry.put(classType, transformer);
    }
}
