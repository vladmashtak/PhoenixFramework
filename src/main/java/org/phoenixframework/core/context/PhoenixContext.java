package org.phoenixframework.core.context;

import org.phoenixframework.core.context.registry.DomainDescriptorRegistry;
import org.phoenixframework.core.context.registry.StandardMapperRegistry;
import org.phoenixframework.core.context.registry.descriptor.DomainDescriptor;
import org.phoenixframework.core.context.util.AnnotatedDomainReader;
import org.phoenixframework.core.context.util.ClassPathDomainScanner;
import org.phoenixframework.core.exception.PhoenixException;
import org.phoenixframework.core.mapper.ResultMapper;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.context.registry.DomainDescriptorRegistry
 * @see org.phoenixframework.core.context.registry.StandardMapperRegistry
 */

public final class PhoenixContext {

    private final DomainDescriptorRegistry domainDescriptorRegistry = new DomainDescriptorRegistry();
    private final StandardMapperRegistry standardMapperRegistry = new StandardMapperRegistry();

    public PhoenixContext() {
    }

    public void register(Class<?>... domainClasses) {
        AnnotatedDomainReader reader = new AnnotatedDomainReader(domainDescriptorRegistry);
        reader.register(domainClasses);
    }

    public void scan(String... domainPackages) {
        ClassPathDomainScanner scanner = new ClassPathDomainScanner(domainDescriptorRegistry);
        scanner.scan(domainPackages);
    }

    public DomainDescriptor getDomain(Class<?> domainType) {
        DomainDescriptor domainDescriptor = domainDescriptorRegistry.getDescriptor(domainType);
        if (domainDescriptor == null) {
            throw new PhoenixException("Metadata for domain class \"" + domainType.getSimpleName() + "\" is not registered");
        }
        return domainDescriptor;
    }

    public <T> ResultMapper<T> getStandardMapper(Class<T> classType) {
        return standardMapperRegistry.getMapper(classType);
    }
}
