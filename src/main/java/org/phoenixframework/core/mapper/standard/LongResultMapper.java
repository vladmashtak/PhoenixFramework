package org.phoenixframework.core.mapper.standard;

import org.phoenixframework.core.mapper.CustomResultMapper;
import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;

/**
 * @author Oleg Marchenko
 */

public class LongResultMapper extends CustomResultMapper<Long> {

    @Override
    protected Long map(ReadOnlyScrollableResult scrollableResult) {
        return scrollableResult.getLong(1);
    }
}
