package org.phoenixframework.core.mapper.single_wrap;

import org.phoenixframework.core.mapper.CustomResultMapper;
import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;

import java.math.BigDecimal;

/**
 * @author Oleg Marchenko
 */

public class BigDecimalResultMapper extends CustomResultMapper<BigDecimal> {

    @Override
    protected BigDecimal map(ReadOnlyScrollableResult scrollableResult) {
        return scrollableResult.getBigDecimal(1);
    }
}
