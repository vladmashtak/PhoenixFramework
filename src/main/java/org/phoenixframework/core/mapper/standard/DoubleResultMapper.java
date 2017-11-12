package org.phoenixframework.core.mapper.standard;

import org.phoenixframework.core.mapper.CustomResultMapper;
import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;

/**
 * @author Oleg Marchenko
 */

public class DoubleResultMapper extends CustomResultMapper<Double> {

    @Override
    protected Double map(ReadOnlyScrollableResult scrollableResult) {
        return scrollableResult.getDouble(1);
    }
}
