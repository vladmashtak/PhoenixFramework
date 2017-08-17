package org.phoenixframework.core.mapper;

import org.phoenixframework.core.annotation.FromColumn;
import org.phoenixframework.core.executor.ReadOnlyResultSet;
import org.phoenixframework.core.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.mapper.CustomResultMapper
 */

public class AliasResultMapper<T> extends CustomResultMapper<T> {
    private static final Map<Class<?>, Method> FIELD_TYPE_TO_RESULT_SET_METHOD = new HashMap<>();
    static {
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(boolean.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getBoolean", String.class));
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(byte.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getByte", String.class));
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(short.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getShort", String.class));
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(int.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getInt", String.class));
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(long.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getLong", String.class));
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(float.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getFloat", String.class));
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(double.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getDouble", String.class));
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(Boolean.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getBoolean", String.class));
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(Byte.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getByte", String.class));
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(Short.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getShort", String.class));
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(Integer.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getInt", String.class));
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(Long.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getLong", String.class));
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(Float.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getFloat", String.class));
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(Double.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getDouble", String.class));
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(Object.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getObject", String.class));
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(String.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getString", String.class));
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(Date.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getDate", String.class));
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(Time.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getTime", String.class));
        FIELD_TYPE_TO_RESULT_SET_METHOD.put(Timestamp.class, ReflectionUtils.getMethodByName(ReadOnlyResultSet.class, "getTimestamp", String.class));
    }

    private final Class<T> classType;
    private final Map<FieldWrapper, Method> fieldValueResolver;

    public AliasResultMapper(Class<T> classType) {
        this.classType = classType;

        Field[] fields = classType.getDeclaredFields();
        fieldValueResolver = new LinkedHashMap<>(fields.length + 1, 1.0F);
        for (Field field: fields) {
            Class<?> fieldType = field.getType();
            Method method = FIELD_TYPE_TO_RESULT_SET_METHOD.get(fieldType);
            if (method == null) {
                throw new IllegalStateException("Cannot resolve method by field \"" + field.getName() + "\" with type \"" + fieldType.getSimpleName() + "\"");
            }
            fieldValueResolver.put(FieldWrapper.create(field), method);
        }
    }

    @Override
    protected T map(ReadOnlyResultSet result) {
        T object = ReflectionUtils.newInstance(classType);
        for (FieldWrapper fieldWrapper: fieldValueResolver.keySet()) {
            Method method = fieldValueResolver.get(fieldWrapper);
            Object value = ReflectionUtils.invokeMethod(result, method, fieldWrapper.getColumnName());
            ReflectionUtils.setValueToField(object, fieldWrapper.getField(), value);
        }
        return object;
    }

    private static class FieldWrapper {
        private final Field field;
        private final String columnName;

        private FieldWrapper(Field field) {
            this.field = field;
            if (field.isAnnotationPresent(FromColumn.class)) {
                FromColumn fromColumn = field.getAnnotation(FromColumn.class);
                this.columnName = fromColumn.value();
            } else {
                this.columnName = field.getName();
            }
        }

        public Field getField() {
            return field;
        }

        public String getColumnName() {
            return columnName;
        }

        public static FieldWrapper create(Field field) {
            return new FieldWrapper(field);
        }
    }
}
