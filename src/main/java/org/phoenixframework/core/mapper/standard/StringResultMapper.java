package org.phoenixframework.core.mapper.standard;

import org.phoenixframework.core.mapper.CustomResultMapper;
import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;

/**
 * @author Oleg Marchenko
 */

public class StringResultMapper extends CustomResultMapper<String> {

    @Override
    protected String map(ReadOnlyScrollableResult scrollableResult) {
        return scrollableResult.getString(1);
    }
}
