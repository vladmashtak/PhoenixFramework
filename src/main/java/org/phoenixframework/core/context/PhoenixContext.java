package org.phoenixframework.core.context;

import org.phoenixframework.core.context.registry.DomainMetadataRegistry;
import org.phoenixframework.core.context.registry.DomainTransformerRegistry;
import org.phoenixframework.core.context.registry.metadata.DomainMetadataHolder;
import org.phoenixframework.core.context.util.AnnotatedDomainReader;
import org.phoenixframework.core.context.util.ClassPathDomainScanner;
import org.phoenixframework.core.mapper.ObjectTransformer;
import org.phoenixframework.core.mapper.ResultMapper;

/**
 * @author Oleg Marchenko
 */

public final class PhoenixContext {

    private final DomainMetadataRegistry domainMetadataRegistry = new DomainMetadataRegistry();
    private final DomainTransformerRegistry domainTransformerRegistry = new DomainTransformerRegistry();

    public PhoenixContext() {
    }

    public void register(Class<?>... domainClasses) {
        AnnotatedDomainReader reader = new AnnotatedDomainReader(domainMetadataRegistry);
        reader.register(domainClasses);
    }

    public void scan(String... domainPackages) {
        ClassPathDomainScanner scanner = new ClassPathDomainScanner(domainMetadataRegistry);
        scanner.scan(domainPackages);
    }

    public void registerTransformer(Class<?> domainClass, ObjectTransformer transformer) {
        domainTransformerRegistry.addTransformer(domainClass, transformer);
    }

    public DomainMetadataHolder getDomainMetadata(Class<?> domainClass) {
        return domainMetadataRegistry.getMetadata(domainClass);
    }

    public ObjectTransformer getTransformer(Class<?> domainClass) {
        return domainTransformerRegistry.getTransformer(domainClass);
    }

    public <T> ResultMapper<T> getMapper(Class<T> domainClass) {
        return domainTransformerRegistry.getMapper(domainClass);
    }
}
