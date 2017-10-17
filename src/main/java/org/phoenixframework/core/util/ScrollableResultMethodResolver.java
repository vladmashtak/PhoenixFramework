package org.phoenixframework.core.util;

import org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult
 */

public final class ScrollableResultMethodResolver {
    private static final Map<Class<?>, Method> FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE = new HashMap<>();
    static {
        Method getBooleanMethod = ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getBoolean", String.class);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(boolean.class, getBooleanMethod);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(Boolean.class, getBooleanMethod);
        Method getByteMethod = ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getByte", String.class);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(byte.class, getByteMethod);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(Byte.class, getByteMethod);
        Method getShortMethod = ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getShort", String.class);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(short.class, getShortMethod);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(Short.class, getShortMethod);
        Method getIntegerMethod = ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getInt", String.class);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(int.class, getIntegerMethod);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(Integer.class, getIntegerMethod);
        Method getLongMethod = ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getLong", String.class);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(long.class, getLongMethod);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(Long.class, getLongMethod);
        Method getFloatMethod = ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getFloat", String.class);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(float.class, getFloatMethod);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(Float.class, getFloatMethod);
        Method getDoubleMethod = ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getDouble", String.class);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(double.class, getDoubleMethod);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(Double.class, getDoubleMethod);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(BigDecimal.class, ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getBigDecimal", String.class));
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(String.class, ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getString", String.class));
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(byte[].class, ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getBytes", String.class));
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(Date.class, ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getDate", String.class));
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(Time.class, ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getTime", String.class));
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(Timestamp.class, ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getTimestamp", String.class));
        FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.put(Object.class, ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getObject", String.class));
    }

    private static final Map<Class<?>, Method> FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE = new HashMap<>();
    static {
        Method getBooleanMethod = ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getBoolean", int.class);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(boolean.class, getBooleanMethod);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(Boolean.class, getBooleanMethod);
        Method getByteMethod = ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getByte", int.class);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(byte.class, getByteMethod);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(Byte.class, getByteMethod);
        Method getShortMethod = ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getShort", int.class);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(short.class, getShortMethod);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(Short.class, getShortMethod);
        Method getIntegerMethod = ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getInt", int.class);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(int.class, getIntegerMethod);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(Integer.class, getIntegerMethod);
        Method getLongMethod = ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getLong", int.class);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(long.class, getLongMethod);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(Long.class, getLongMethod);
        Method getFloatMethod = ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getFloat", int.class);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(float.class, getFloatMethod);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(Float.class, getFloatMethod);
        Method getDoubleMethod = ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getDouble", int.class);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(double.class, getDoubleMethod);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(Double.class, getDoubleMethod);
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(BigDecimal.class, ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getBigDecimal", int.class));
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(String.class, ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getString", int.class));
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(byte[].class, ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getBytes", int.class));
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(Date.class, ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getDate", int.class));
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(Time.class, ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getTime", int.class));
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(Timestamp.class, ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getTimestamp", int.class));
        FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.put(Object.class, ReflectionUtils.getMethodByName(ReadOnlyScrollableResult.class, "getObject", int.class));
    }

    public static Method getMethodByColumnLabel(Class<?> fieldType) {
        Method method = FIELD_TYPE_TO_METHOD_BY_COLUMN_LABEL_STORAGE.get(fieldType);
        if (method == null) {
            throw new IllegalStateException("Cannot resolve method by field type \"" + fieldType.getSimpleName() + "\"");
        }
        return method;
    }

    public static Method getMethodByColumnIndex(Class<?> fieldType) {
        Method method = FIELD_TYPE_TO_METHOD_BY_COLUMN_INDEX_STORAGE.get(fieldType);
        if (method == null) {
            throw new IllegalStateException("Cannot resolve method by field type \"" + fieldType.getSimpleName() + "\"");
        }
        return method;
    }

    private ScrollableResultMethodResolver() {
    }
}
