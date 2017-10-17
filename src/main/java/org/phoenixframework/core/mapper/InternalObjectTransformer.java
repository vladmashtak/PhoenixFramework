package org.phoenixframework.core.mapper;

import java.util.Collection;

/**
 * @author Oleg Marchenko
 * @see ObjectTransformer
 */

public abstract class InternalObjectTransformer implements ObjectTransformer {

    @Override
    public Collection<Object> toValues(Object object) {
        return toNamedValues(object).values();
    }
}
