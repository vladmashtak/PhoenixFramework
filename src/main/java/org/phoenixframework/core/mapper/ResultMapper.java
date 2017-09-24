package org.phoenixframework.core.mapper;

import org.phoenixframework.core.session_factory.session.query.scrollable_result.CachedScrollableResult;

import java.util.List;

/**
 * The <code>ResultMapper</code> allows configure data mapping from database in the domain object.
 *
 * @param <T> the type of mapping object
 *
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.session_factory.session.query.scrollable_result.CachedScrollableResult
 */

public interface ResultMapper<T> {

    /**
     * Returns unique mapping object.
     *
     * @param scrollableResult the data from database
     * @return mapping object
     */
    T unique(CachedScrollableResult scrollableResult);

    /**
     * Returns list of mapping objects.
     *
     * @param scrollableResult the data from database
     * @return list of mapping objects
     */
    List<T> list(CachedScrollableResult scrollableResult);
}
