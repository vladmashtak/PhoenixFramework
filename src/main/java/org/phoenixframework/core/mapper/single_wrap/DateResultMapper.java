package org.phoenixframework.core.mapper.single_wrap;

import org.phoenixframework.core.mapper.CustomResultMapper;
import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;

import java.sql.Date;

/**
 * @author Oleg Marchenko
 */

public class DateResultMapper extends CustomResultMapper<Date> {

    @Override
    protected Date map(ReadOnlyScrollableResult scrollableResult) {
        return scrollableResult.getDate(1);
    }
}
