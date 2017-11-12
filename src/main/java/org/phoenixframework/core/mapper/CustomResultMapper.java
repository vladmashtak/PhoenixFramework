package org.phoenixframework.core.mapper;

import org.phoenixframework.core.session_factory.session.query.scrollable_result.CachedScrollableResult;
import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The <code>CustomResultMapper</code> allows to implement the logic of custom mapping
 * in method {@link #map(ReadOnlyScrollableResult)} for data object.
 *
 * @param <T> the type of mapping object
 *
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.mapper.ResultMapper
 * @see org.phoenixframework.core.mapper.DataTransformer
 */

public abstract class CustomResultMapper<T> implements ResultMapper<T>, DataTransformer {

    @Override
    public T unique(CachedScrollableResult scrollableResult) {
        if (scrollableResult.next()) {
            return map(scrollableResult);
        }
        return null;
    }

    @Override
    public List<T> list(CachedScrollableResult scrollableResult) {
        if (!scrollableResult.isEmpty()) {
            List<T> objects = new ArrayList<>(scrollableResult.getRowCount());
            while (scrollableResult.next()) {
                objects.add(map(scrollableResult));
            }
            return objects;
        }
        return Collections.emptyList();
    }

    @Override
    public Object toObject(ReadOnlyScrollableResult scrollableResult) {
        return map(scrollableResult);
    }

    @Override
    public Map<String, Object> toNamedValues(Object object) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    /**
     * Implementation of custom mapping object method.
     *
     * @param scrollableResult the scrollable result from database
     * @return mapping data object
     */
    protected abstract T map(ReadOnlyScrollableResult scrollableResult);
}
