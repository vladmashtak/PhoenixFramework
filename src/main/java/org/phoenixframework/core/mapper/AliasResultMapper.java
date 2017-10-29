package org.phoenixframework.core.mapper;

import org.phoenixframework.core.context.PhoenixContext;
import org.phoenixframework.core.context.registry.metadata.DomainMetadataHolder;
import org.phoenixframework.core.context.registry.metadata.FieldMetadataHolder;
import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;
import org.phoenixframework.core.util.ReflectionUtils;
import org.phoenixframework.core.util.ScrollableResultMethodResolver;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.mapper.CustomResultMapper
 */

public class AliasResultMapper<T> extends CustomResultMapper<T> {

    private final PhoenixContext context;
    private final Class<T> classType;

    public AliasResultMapper(PhoenixContext context, Class<T> classType) {
        this.context = context;
        this.classType = classType;
    }

    @Override
    protected T map(ReadOnlyScrollableResult scrollableResult) {
        T object = ReflectionUtils.newInstance(classType);

        Class<?> candidateSuperclass = classType.getSuperclass();
        if (!Object.class.equals(candidateSuperclass)) {
            mapInternal(object, scrollableResult, candidateSuperclass);
        }

        mapInternal(object, scrollableResult, classType);
        return object;
    }

    protected void mapInternal(T object, ReadOnlyScrollableResult scrollableResult, Class<?> classType) {
        DomainMetadataHolder domainMetadata = context.getDomainMetadata(classType);
        for (FieldMetadataHolder fieldMetadata: domainMetadata.getAllFieldMetadata()) {
            Object value;
            if (!fieldMetadata.isWrapper()) {
                Method method = ScrollableResultMethodResolver.getMethodByColumnLabel(fieldMetadata.getFieldType());
                value = ReflectionUtils.invokeMethod(scrollableResult, method, fieldMetadata.getColumnLabel());
            } else {
                ObjectTransformer transformer = context.getTransformer(fieldMetadata.getFieldType());
                value = transformer.toObject(scrollableResult);
            }
            ReflectionUtils.setValueToField(object, fieldMetadata.getField(), value);
        }
    }

    @Override
    public Map<String, Object> toNamedValues(Object object) {
        DomainMetadataHolder domainMetadata = context.getDomainMetadata(classType);
        Collection<FieldMetadataHolder> allFieldMetadata = domainMetadata.getAllFieldMetadata();
        Map<String, Object> namedValues = new LinkedHashMap<>(allFieldMetadata.size() + 1, 1.0F);
        for (FieldMetadataHolder fieldMetadata: allFieldMetadata) {
            Object fieldValue = ReflectionUtils.getValueFromField(object, fieldMetadata.getField());
            if (!fieldMetadata.isWrapper()) {
                namedValues.put(fieldMetadata.getColumnLabel(), fieldValue);
            } else {
                ObjectTransformer transformer = context.getTransformer(classType);
                namedValues.putAll(transformer.toNamedValues(fieldValue));
            }
        }
        return namedValues;
    }
}
