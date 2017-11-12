package org.phoenixframework.core.context.registry.extractor;

import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;
import org.phoenixframework.core.util.ReflectionUtils;
import org.phoenixframework.core.util.ScrollableResultMethodResolver;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

/**
 * @author Oleg Marchenko
 */

public class ValueExtractor {

    public Object extractFrom(ReadOnlyScrollableResult scrollableResult, String columnName, Class<?> columnType) {
        Method method = ScrollableResultMethodResolver.getMethodByColumnLabel(columnType);
        return ReflectionUtils.invokeMethod(scrollableResult, method, columnName);
    }

    public Map<String, Object> extractFrom(Object object, String columnName) {
        return Collections.singletonMap(columnName, object);
    }
}
