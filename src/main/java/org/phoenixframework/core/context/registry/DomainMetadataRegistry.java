package org.phoenixframework.core.context.registry;

import org.phoenixframework.core.context.registry.metadata.DomainMetadataHolder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.context.registry.metadata.DomainMetadataHolder
 */

public class DomainMetadataRegistry {

    private final Map<Class<?>, DomainMetadataHolder> registry = new HashMap<>();

    public Collection<DomainMetadataHolder> getAllMetadata() {
        return registry.values();
    }

    public DomainMetadataHolder getMetadata(Class<?> domainClass) {
        return registry.get(domainClass);
    }

    public boolean isRegistered(Class<?> domainClass) {
        return registry.containsKey(domainClass);
    }

    public void registerMetadata(DomainMetadataHolder holder) {
        registry.put(holder.getDomainClass(), holder);
    }
}
