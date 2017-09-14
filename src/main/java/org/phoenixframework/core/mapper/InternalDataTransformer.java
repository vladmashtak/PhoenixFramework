package org.phoenixframework.core.mapper;

import java.util.Collection;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.mapper.DataTransformer
 */

public abstract class InternalDataTransformer<T> implements DataTransformer<T> {

    @Override
    public Collection<Object> toValues(T object) {
        return toNamedValues(object).values();
    }
}
