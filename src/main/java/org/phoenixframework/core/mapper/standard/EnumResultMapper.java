package org.phoenixframework.core.mapper.standard;

import org.phoenixframework.core.mapper.CustomResultMapper;
import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;

/**
 * @author Oleg Marchenko
 */

public class EnumResultMapper<E extends Enum<E>> extends CustomResultMapper<E> {

    private final Class<E> enumType;

    public EnumResultMapper(Class<E> enumType) {
        this.enumType = enumType;
    }

    @Override
    protected E map(ReadOnlyScrollableResult scrollableResult) {
        String enumName = scrollableResult.getString(1);
        return Enum.valueOf(enumType, enumName);
    }
}
