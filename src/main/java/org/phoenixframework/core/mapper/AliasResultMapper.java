package org.phoenixframework.core.mapper;

import org.phoenixframework.core.context.registry.descriptor.DomainDescriptor;
import org.phoenixframework.core.context.registry.descriptor.FieldDescriptor;
import org.phoenixframework.core.context.registry.extractor.ValueExtractor;
import org.phoenixframework.core.session_factory.SessionFactory;
import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;
import org.phoenixframework.core.util.ReflectionUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.mapper.CustomResultMapper
 */

public class AliasResultMapper<T> extends CustomResultMapper<T> {

    private final Class<T> classType;

    public AliasResultMapper(Class<T> classType) {
        this.classType = classType;
    }

    @Override
    protected T map(ReadOnlyScrollableResult scrollableResult) {
        T object = ReflectionUtils.newInstance(classType);
        mapInternal(object, scrollableResult, classType);
        return object;
    }

    protected void mapInternal(T object, ReadOnlyScrollableResult scrollableResult, Class<?> classType) {
        Class<?> superclass = classType.getSuperclass();
        if (!Object.class.equals(superclass)) {
            mapInternal(object, scrollableResult, superclass);
        }

        DomainDescriptor domainDescriptor = SessionFactory.context().getDomain(classType);
        for (FieldDescriptor fieldDescriptor: domainDescriptor.getFieldDescriptors()) {
            ValueExtractor valueExtractor = fieldDescriptor.getValueExtractor();
            Object fieldValue = valueExtractor.extractFrom(scrollableResult, fieldDescriptor.getColumnAlias(), fieldDescriptor.getFieldType());
            fieldDescriptor.setFieldValue(object, fieldValue);
        }
    }

    @Override
    public Map<String, Object> toNamedValues(Object object) {
        DomainDescriptor domainDescriptor = SessionFactory.context().getDomain(classType);
        Collection<FieldDescriptor> fieldDescriptors = domainDescriptor.getFieldDescriptors();
        Map<String, Object> namedValues = new LinkedHashMap<>(fieldDescriptors.size() + 1, 1.0F);
        for (FieldDescriptor fieldDescriptor: fieldDescriptors) {
            Object fieldValue = fieldDescriptor.getFieldValue(object);
            ValueExtractor valueExtractor = fieldDescriptor.getValueExtractor();
            namedValues.putAll(valueExtractor.extractFrom(fieldValue, fieldDescriptor.getColumnAlias()));
        }
        return namedValues;
    }
}
