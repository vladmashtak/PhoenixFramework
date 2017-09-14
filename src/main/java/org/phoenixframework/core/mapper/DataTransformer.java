package org.phoenixframework.core.mapper;

import org.phoenixframework.core.executor.ReadOnlyResultSet;

import java.util.Collection;
import java.util.Map;

/**
 * Interface provides transformation from result set to data object and retrieve the values from data object.
 * The <code>DataTransformer</code> is an internal data transformation mechanism.
 *
 * @param <T> the type of data object
 *
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.executor.ReadOnlyResultSet
 */

public interface DataTransformer<T> {

    /**
     * Transformation the result set to a data object.
     *
     * @param result result set
     * @return data object
     */
    T toObject(ReadOnlyResultSet result);

    /**
     * Transformation a data object to a set of named member values.
     *
     * @param object data object
     * @return set of named member values
     */
    Map<String, Object> toNamedValues(T object);

    /**
     * Transformation a data object to a set of member values.
     *
     * @param object data object
     * @return set of member values
     */
    Collection<Object> toValues(T object);
}
