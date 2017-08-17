package org.phoenixframework.core.mapper;

import org.phoenixframework.core.executor.CachedResultSet;

import java.util.List;

/**
 * The <code>ResultMapper</code> allows configure data mapping from database in the domain object.
 *
 * @param <T> the type of mapping object
 *
 * @author Oleg Marchenko
 */

public interface ResultMapper<T> {

    /**
     * Returns unique mapping object.
     *
     * @param result the data from database
     * @return mapping object
     */
    T unique(CachedResultSet result);

    /**
     * Returns list of mapping objects.
     *
     * @param result the data from database
     * @return list of mapping objects
     */
    List<T> list(CachedResultSet result);
}
