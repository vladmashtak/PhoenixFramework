package org.phoenixframework.core.mapper.single_wrap;

import org.phoenixframework.core.mapper.CustomResultMapper;
import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;

/**
 * @author Oleg Marchenko
 */

public class FloatResultMapper extends CustomResultMapper<Float> {

    @Override
    protected Float map(ReadOnlyScrollableResult scrollableResult) {
        return scrollableResult.getFloat(1);
    }
}
