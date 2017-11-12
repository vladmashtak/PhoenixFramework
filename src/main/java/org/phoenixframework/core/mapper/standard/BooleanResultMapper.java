package org.phoenixframework.core.mapper.standard;

import org.phoenixframework.core.mapper.CustomResultMapper;
import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;

/**
 * @author Oleg Marchenko
 */

public class BooleanResultMapper extends CustomResultMapper<Boolean> {

    @Override
    protected Boolean map(ReadOnlyScrollableResult scrollableResult) {
        return scrollableResult.getBoolean(1);
    }
}
