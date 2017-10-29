package org.phoenixframework.core.context.util;

import org.phoenixframework.core.annotation.*;
import org.phoenixframework.core.context.registry.DomainMetadataRegistry;
import org.phoenixframework.core.context.registry.metadata.DomainMetadataHolder;
import org.phoenixframework.core.context.registry.metadata.FieldMetadataHolder;
import org.phoenixframework.core.context.registry.metadata.NamedQueryMetadataHolder;

import java.lang.reflect.Field;

/**
 * @author Oleg Marchenko
 */

public final class AnnotatedDomainReader {

    private final DomainMetadataRegistry registry;

    public AnnotatedDomainReader(DomainMetadataRegistry registry) {
        this.registry = registry;
    }

    public void register(Class<?>... candidateClasses) {
        for (Class<?> candidateClass: candidateClasses) {
            doRegister(candidateClass);
        }
    }

    protected void doRegister(Class<?> candidateClass) {
        if (!registry.isRegistered(candidateClass) && candidateClass.isAnnotationPresent(Domain.class)) {
            DomainMetadataHolder domainMetadataHolder = new DomainMetadataHolder(candidateClass);

            Class<?> candidateSuperclass = candidateClass.getSuperclass();
            if (!Object.class.equals(candidateSuperclass)) {
                doRegister(candidateSuperclass);
            }

            NamedQueries namedQueries = candidateClass.getAnnotation(NamedQueries.class);
            if (namedQueries != null) {
                for (NamedQuery namedQuery: namedQueries.value()) {
                    Class<?> returnType = namedQuery.returnType();
                    if (Object.class.equals(returnType)) {
                        returnType = candidateClass;
                    }

                    NamedQueryMetadataHolder namedQueryMetadataHolder =
                            new NamedQueryMetadataHolder(namedQuery.name(), namedQuery.query(), returnType);
                    domainMetadataHolder.addNamedQueryMetadata(namedQueryMetadataHolder);
                }
            }

            Field[] fields = candidateClass.getDeclaredFields();
            for (Field field: fields) {
                if (!field.isAnnotationPresent(Transient.class)) {
                    String columnName = field.getName();
                    FromColumn fromColumn = field.getAnnotation(FromColumn.class);
                    if (fromColumn != null) {
                        columnName = fromColumn.value();
                    }

                    FieldMetadataHolder fieldMetadataHolder;
                    if (!field.isAnnotationPresent(Wrapper.class)) {
                        fieldMetadataHolder = new FieldMetadataHolder(field, columnName);
                    } else {
                        doRegister(field.getType());

                        fieldMetadataHolder = new FieldMetadataHolder(field, columnName, true);
                    }
                    domainMetadataHolder.addFieldMetadata(fieldMetadataHolder);
                }
            }
            registry.registerMetadata(domainMetadataHolder);
        }
    }
}
