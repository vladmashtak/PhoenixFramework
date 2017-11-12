package org.phoenixframework.core.mapper.standard;

import org.phoenixframework.core.mapper.CustomResultMapper;
import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;

import java.sql.Time;

/**
 * @author Oleg Marchenko
 */

public class TimeResultMapper extends CustomResultMapper<Time> {

    @Override
    protected Time map(ReadOnlyScrollableResult scrollableResult) {
        return scrollableResult.getTime(1);
    }
}
