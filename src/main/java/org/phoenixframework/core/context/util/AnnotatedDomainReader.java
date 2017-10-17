package org.phoenixframework.core.context.util;

import org.phoenixframework.core.annotation.Domain;
import org.phoenixframework.core.annotation.FromColumn;
import org.phoenixframework.core.annotation.Transient;
import org.phoenixframework.core.annotation.Wrapper;
import org.phoenixframework.core.context.registry.DomainMetadataRegistry;
import org.phoenixframework.core.context.registry.metadata.DomainMetadataHolder;
import org.phoenixframework.core.context.registry.metadata.FieldMetadataHolder;

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
        if (!registry.containsDomain(candidateClass) && candidateClass.isAnnotationPresent(Domain.class)) {
            Class<?> candidateSuperclass = candidateClass.getSuperclass();
            if (!Object.class.equals(candidateSuperclass)) {
                doRegister(candidateSuperclass);
            }

            DomainMetadataHolder domainMetadataHolder = new DomainMetadataHolder(candidateClass);

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
            registry.addMetadata(domainMetadataHolder);
        }
    }
}
