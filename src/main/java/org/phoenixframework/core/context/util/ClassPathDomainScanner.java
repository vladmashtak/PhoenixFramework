package org.phoenixframework.core.context.util;

import org.phoenixframework.core.context.registry.DomainMetadataRegistry;
import org.phoenixframework.core.util.ClassUtils;

import java.util.List;

/**
 * @author Oleg Marchenko
 */

public final class ClassPathDomainScanner {

    private final AnnotatedDomainReader domainReader;

    public ClassPathDomainScanner(DomainMetadataRegistry registry) {
        this.domainReader = new AnnotatedDomainReader(registry);
    }

    public void scan(String... basePackages) {
        for (String basePackage: basePackages) {
            doScan(basePackage);
        }
    }

    protected void doScan(String basePackage) {
        List<Class<?>> classes = ClassUtils.loadClassesFromPackage(basePackage);
        for (Class<?> candidateClass: classes) {
            domainReader.doRegister(candidateClass);
        }
    }
}
