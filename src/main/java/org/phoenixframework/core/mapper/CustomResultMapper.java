package org.phoenixframework.core.mapper;

import org.phoenixframework.core.executor.CachedResultSet;
import org.phoenixframework.core.executor.ReadOnlyResultSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The <code>CustomResultMapper</code> allows to implement the logic of custom mapping
 * in method {@link #map(ReadOnlyResultSet)} for data object.
 *
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.mapper.ResultMapper
 * @see org.phoenixframework.core.executor.ReadOnlyResultSet
 * @see org.phoenixframework.core.executor.CachedResultSet
 */

public abstract class CustomResultMapper<T> implements ResultMapper<T> {

    @Override
    public T unique(CachedResultSet result) {
        if (result.next()) {
            return map(result);
        }
        return null;
    }

    @Override
    public List<T> list(CachedResultSet result) {
        if (!result.isEmpty()) {
            List<T> objects = new ArrayList<>(result.getRowCount());
            while (result.next()) {
                objects.add(map(result));
            }
            return objects;
        }
        return Collections.emptyList();
    }

    /**
     * Implementation of custom mapping object method.
     *
     * @param result the result set from database
     * @return mapping data object
     */
    protected abstract T map(ReadOnlyResultSet result);
}
