package org.phoenixframework.core.mapper;

import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;

import java.util.Map;

/**
 * Interface provides transformation from result set to data object and retrieve the values from data object.
 * The <code>DataTransformer</code> is an internal data transformation mechanism.
 *
 * @author Oleg Marchenko
 */

public interface DataTransformer {

    /**
     * Transformation the scrollable result to a data object.
     *
     * @param scrollableResult result set for transformation
     * @return data object
     */
    Object toObject(ReadOnlyScrollableResult scrollableResult);

    /**
     * Transformation a data object to a set of named member values.
     *
     * @param object data object for transformation
     * @return set of named member values
     */
    Map<String, Object> toNamedValues(Object object);
}
