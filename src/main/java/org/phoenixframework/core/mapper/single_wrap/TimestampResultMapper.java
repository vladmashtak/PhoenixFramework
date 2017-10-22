package org.phoenixframework.core.mapper.single_wrap;

import org.phoenixframework.core.mapper.CustomResultMapper;
import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;

import java.sql.Timestamp;

/**
 * @author Oleg Marchenko
 */

public class TimestampResultMapper extends CustomResultMapper<Timestamp> {

    @Override
    protected Timestamp map(ReadOnlyScrollableResult scrollableResult) {
        return scrollableResult.getTimestamp(1);
    }
}
