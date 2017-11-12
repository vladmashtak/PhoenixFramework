package org.phoenixframework.core.context.registry.extractor;

import org.phoenixframework.core.mapper.DataTransformer;
import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;

import java.util.Map;

/**
 * @author Oleg Marchenko
 */

public class WrappedValueExtractor extends ValueExtractor {

    private final DataTransformer transformer;

    public WrappedValueExtractor(DataTransformer transformer) {
        this.transformer = transformer;
    }

    @Override
    public Object extractFrom(ReadOnlyScrollableResult scrollableResult, String columnName, Class<?> columnType) {
        return transformer.toObject(scrollableResult);
    }

    @Override
    public Map<String, Object> extractFrom(Object fieldValue, String columnName) {
        return transformer.toNamedValues(fieldValue);
    }
}
