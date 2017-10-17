package org.phoenixframework.core.context.registry;

import org.phoenixframework.core.context.registry.metadata.DomainMetadataHolder;
import org.phoenixframework.core.exception.PhoenixException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleg Marchenko
 */

public class DomainMetadataRegistry {

    private final Map<Class<?>, DomainMetadataHolder> registry = new HashMap<>();

    public Collection<DomainMetadataHolder> getAllMetadata() {
        return registry.values();
    }

    public DomainMetadataHolder getMetadata(Class<?> domainClass) {
        DomainMetadataHolder holder = registry.get(domainClass);
        if (holder == null) {
            throw new PhoenixException("Domain object for class \"" + domainClass.getSimpleName() + "\" is not registered");
        }
        return holder;
    }

    public boolean containsDomain(Class<?> domainClass) {
        return registry.containsKey(domainClass);
    }

    public void addMetadata(DomainMetadataHolder holder) {
        registry.put(holder.getDomainClass(), holder);
    }
}
