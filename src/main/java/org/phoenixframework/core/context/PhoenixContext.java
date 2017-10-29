package org.phoenixframework.core.context;

import org.phoenixframework.core.context.registry.DomainMetadataRegistry;
import org.phoenixframework.core.context.registry.DomainTransformerRegistry;
import org.phoenixframework.core.context.registry.metadata.DomainMetadataHolder;
import org.phoenixframework.core.context.registry.metadata.NamedQueryMetadataHolder;
import org.phoenixframework.core.context.util.AnnotatedDomainReader;
import org.phoenixframework.core.context.util.ClassPathDomainScanner;
import org.phoenixframework.core.exception.PhoenixException;
import org.phoenixframework.core.mapper.ObjectTransformer;
import org.phoenixframework.core.mapper.ResultMapper;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.context.registry.DomainMetadataRegistry
 * @see org.phoenixframework.core.context.registry.DomainTransformerRegistry
 */

public final class PhoenixContext {

    private final DomainMetadataRegistry domainMetadataRegistry = new DomainMetadataRegistry();
    private final DomainTransformerRegistry domainTransformerRegistry = new DomainTransformerRegistry();

    public void register(Class<?>... domainClasses) {
        AnnotatedDomainReader reader = new AnnotatedDomainReader(domainMetadataRegistry);
        reader.register(domainClasses);
    }

    public void scan(String... domainPackages) {
        ClassPathDomainScanner scanner = new ClassPathDomainScanner(domainMetadataRegistry);
        scanner.scan(domainPackages);
    }

    public void registerTransformer(Class<?> domainClass, ObjectTransformer transformer) {
        domainTransformerRegistry.registerTransformer(domainClass, transformer);
    }

    public DomainMetadataHolder getDomainMetadata(Class<?> domainClass) {
        DomainMetadataHolder domainMetadata = domainMetadataRegistry.getMetadata(domainClass);
        if (domainMetadata == null) {
            throw new PhoenixException("Metadata for class \"" + domainClass.getSimpleName() + "\" is not registered");
        }
        return domainMetadata;
    }

    public ObjectTransformer getTransformer(Class<?> domainClass) {
        ObjectTransformer transformer = domainTransformerRegistry.getTransformer(domainClass);
        if (transformer == null) {
            throw new PhoenixException("Object transformer for class \"" + domainClass.getSimpleName() + "\" is not registered");
        }
        return transformer;
    }

    public <T> ResultMapper<T> getMapper(Class<T> domainClass) {
        ResultMapper<T> mapper = domainTransformerRegistry.getMapper(domainClass);
        if (mapper == null) {
            throw new PhoenixException("Result mapper for class \"" + domainClass.getSimpleName() + "\" is not registered");
        }
        return mapper;
    }

    public NamedQueryMetadataHolder getNamedQueryMetadata(Class<?> domainClass, String queryName) {
        DomainMetadataHolder domainMetadata = getDomainMetadata(domainClass);
        NamedQueryMetadataHolder namedQueryMetadata = domainMetadata.getNamedQueryMetadata(queryName);
        if (namedQueryMetadata == null) {
            throw new PhoenixException("Named query \"" + queryName + "\" for class \"" + domainClass.getSimpleName() + "\" is not registered");
        }
        return namedQueryMetadata;
    }
}
