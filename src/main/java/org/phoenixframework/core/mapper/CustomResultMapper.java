package org.phoenixframework.core.mapper;

import org.phoenixframework.core.executor.CachedResultSet;
import org.phoenixframework.core.executor.ReadOnlyResultSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.mapper.ResultMapper
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
     * Implementation of mapping object method.
     *
     * @param result the result set from database
     * @return mapping object
     */
    protected abstract T map(ReadOnlyResultSet result);
}
