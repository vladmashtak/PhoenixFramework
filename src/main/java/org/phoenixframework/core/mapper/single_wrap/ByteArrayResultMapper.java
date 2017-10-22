package org.phoenixframework.core.mapper.single_wrap;

import org.phoenixframework.core.mapper.CustomResultMapper;
import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;

/**
 * @author Oleg Marchenko
 */

public class ByteArrayResultMapper extends CustomResultMapper<byte[]> {

    @Override
    protected byte[] map(ReadOnlyScrollableResult scrollableResult) {
        return scrollableResult.getBytes(1);
    }
}
