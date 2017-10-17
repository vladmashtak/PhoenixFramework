package org.phoenixframework.core.mapper;

import org.phoenixframework.core.context.PhoenixContext;
import org.phoenixframework.core.context.registry.metadata.DomainMetadataHolder;
import org.phoenixframework.core.context.registry.metadata.FieldMetadataHolder;
import org.phoenixframework.core.exception.PhoenixException;
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
        for (FieldMetadataHolder fieldMetadata: getDomainMetadata().getAllFieldMetadata()) {
            Object value;
            if (!fieldMetadata.isWrapper()) {
                Method method = ScrollableResultMethodResolver.getMethodByColumnLabel(fieldMetadata.getFieldType());
                value = ReflectionUtils.invokeMethod(scrollableResult, method, fieldMetadata.getColumnLabel());
            } else {
                value = getObjectTransformer(fieldMetadata.getFieldType()).toObject(scrollableResult);
            }
            ReflectionUtils.setValueToField(object, fieldMetadata.getField(), value);
        }
        return object;
    }

    @Override
    public Map<String, Object> toNamedValues(Object object) {
        Collection<FieldMetadataHolder> allFieldMetadata = getDomainMetadata().getAllFieldMetadata();
        Map<String, Object> namedValues = new LinkedHashMap<>(allFieldMetadata.size() + 1, 1.0F);
        for (FieldMetadataHolder fieldMetadata: allFieldMetadata) {
            Object fieldValue = ReflectionUtils.getValueFromField(object, fieldMetadata.getField());
            if (!fieldMetadata.isWrapper()) {
                namedValues.put(fieldMetadata.getColumnLabel(), fieldValue);
            } else {
                namedValues.putAll(getObjectTransformer(fieldMetadata.getFieldType()).toNamedValues(fieldValue));
            }
        }
        return namedValues;
    }

    private DomainMetadataHolder getDomainMetadata() {
        DomainMetadataHolder domainMetadata = context.getDomainMetadata(classType);
        if (domainMetadata == null) {
            throw new PhoenixException("Metadata for class \"" + classType.getSimpleName() + "\" is not registered");
        }
        return domainMetadata;
    }

    private ObjectTransformer getObjectTransformer(Class<?> domainClass) {
        ObjectTransformer transformer = context.getTransformer(domainClass);
        if (transformer == null) {
            throw new PhoenixException("ObjectTransformer for class \"" + classType.getSimpleName() + "\" is not registered");
        }
        return transformer;
    }
}
