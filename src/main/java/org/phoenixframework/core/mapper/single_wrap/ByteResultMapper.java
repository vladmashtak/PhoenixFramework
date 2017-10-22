package org.phoenixframework.core.mapper.single_wrap;

import org.phoenixframework.core.mapper.CustomResultMapper;
import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;

/**
 * @author Oleg Marchenko
 */

public class ByteResultMapper extends CustomResultMapper<Byte> {

    @Override
    protected Byte map(ReadOnlyScrollableResult scrollableResult) {
        return scrollableResult.getByte(1);
    }
}
