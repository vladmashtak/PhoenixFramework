package org.phoenixframework.core.context.util;

import org.phoenixframework.core.annotation.*;
import org.phoenixframework.core.context.registry.DomainDescriptorRegistry;
import org.phoenixframework.core.context.registry.descriptor.DomainDescriptor;
import org.phoenixframework.core.context.registry.descriptor.FieldDescriptor;
import org.phoenixframework.core.context.registry.extractor.ValueExtractor;
import org.phoenixframework.core.context.registry.extractor.WrappedValueExtractor;
import org.phoenixframework.core.context.registry.holder.FieldHolder;
import org.phoenixframework.core.mapper.CustomResultMapper;
import org.phoenixframework.core.mapper.DataTransformer;
import org.phoenixframework.core.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author Oleg Marchenko
 */

public final class AnnotatedDomainReader {

    private final DomainDescriptorRegistry registry;

    public AnnotatedDomainReader(DomainDescriptorRegistry registry) {
        this.registry = registry;
    }

    public void register(Class<?>... candidateClasses) {
        for (Class<?> candidateClass: candidateClasses) {
            doRegister(candidateClass);
        }
    }

    protected void doRegister(Class<?> candidateClass) {
        if (!registry.isRegistered(candidateClass) && candidateClass.isAnnotationPresent(Domain.class)) {
            Class<?> candidateSuperclass = candidateClass.getSuperclass();
            if (!Object.class.equals(candidateSuperclass)) {
                doRegister(candidateSuperclass);
            }

            DomainDescriptor domainDescriptor;
            CustomMapper customMapper = candidateClass.getAnnotation(CustomMapper.class);
            if (customMapper == null) {
                domainDescriptor = new DomainDescriptor(candidateClass);
            } else {
                Class<? extends CustomResultMapper<?>> customMapperClass = customMapper.value();
                DataTransformer domainTransformer = ReflectionUtils.newInstance(customMapperClass);
                domainDescriptor = new DomainDescriptor(candidateClass, domainTransformer);
            }

            NamedQueries namedQueries = candidateClass.getAnnotation(NamedQueries.class);
            if (namedQueries != null) {
                for (NamedQuery namedQuery: namedQueries.value()) {
                    domainDescriptor.registerNamedQuery(namedQuery.name(), namedQuery.query());
                }
            }

            Field[] fields = candidateClass.getDeclaredFields();
            for (Field field: fields) {
                if (!field.isAnnotationPresent(Transient.class)) {
                    String columnName = field.getName();
                    ColumnAlias columnAlias = field.getAnnotation(ColumnAlias.class);
                    if (columnAlias != null) {
                        columnName = columnAlias.value();
                    }

                    ValueExtractor valueExtractor;
                    if (!field.isAnnotationPresent(Wrapper.class)) {
                        valueExtractor = new ValueExtractor();
                    } else {
                        doRegister(field.getType());

                        DomainDescriptor registeredDomainDescriptor = registry.getDescriptor(field.getType());
                        valueExtractor = new WrappedValueExtractor(registeredDomainDescriptor.getDomainTransformer());
                    }

                    FieldDescriptor fieldDescriptor = new FieldDescriptor(new FieldHolder(field), columnName, valueExtractor);
                    domainDescriptor.registerFieldDescriptor(fieldDescriptor);
                }
            }
            registry.registerDescriptor(domainDescriptor);
        }
    }
}
