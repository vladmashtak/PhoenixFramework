package org.phoenixframework.core.context.registry;

import org.phoenixframework.core.context.registry.descriptor.DomainDescriptor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.context.registry.descriptor.DomainDescriptor
 */

public class DomainDescriptorRegistry {

    private final Map<Class<?>, DomainDescriptor> registry = new HashMap<>();

    public DomainDescriptor getDescriptor(Class<?> domainType) {
        return registry.get(domainType);
    }

    public boolean isRegistered(Class<?> domainType) {
        return registry.containsKey(domainType);
    }

    public void registerDescriptor(DomainDescriptor domainDescriptor) {
        registry.put(domainDescriptor.getDomainType(), domainDescriptor);
    }
}
