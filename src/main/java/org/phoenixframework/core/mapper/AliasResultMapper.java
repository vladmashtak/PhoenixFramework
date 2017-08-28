package org.phoenixframework.core.mapper;

import org.phoenixframework.core.annotation.FromColumn;
import org.phoenixframework.core.annotation.Transient;
import org.phoenixframework.core.executor.ReadOnlyResultSet;
import org.phoenixframework.core.provider.ResultSetMethodProvider;
import org.phoenixframework.core.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.mapper.CustomResultMapper
 */

public class AliasResultMapper<T> extends CustomResultMapper<T> {

    private final Class<T> classType;
    private final Map<FieldWrapper, Method> fieldValueFromMethodResolver;

    public AliasResultMapper(Class<T> classType) {
        this.classType = classType;

        Field[] fields = classType.getFields();
        this.fieldValueFromMethodResolver = new LinkedHashMap<>(fields.length + 1, 1.0F);
        analyzeClassFields(fields);
    }

    @Override
    protected T map(ReadOnlyResultSet result) {
        T object = ReflectionUtils.newInstance(classType);
        for (Map.Entry<FieldWrapper, Method> fieldValueFromMethod: fieldValueFromMethodResolver.entrySet()) {
            FieldWrapper fieldWrapper = fieldValueFromMethod.getKey();
            Method method = fieldValueFromMethod.getValue();
            Object value = ReflectionUtils.invokeMethod(result, method, fieldWrapper.getColumnLabel());
            ReflectionUtils.setValueToField(object, fieldWrapper.getField(), value);
        }
        return object;
    }

    private void analyzeClassFields(Field[] fields) {
        for (Field field: fields) {
            if (!field.isAnnotationPresent(Transient.class)) {
                String columnLabel = field.getName();
                if (field.isAnnotationPresent(FromColumn.class)) {
                    FromColumn fromColumn = field.getAnnotation(FromColumn.class);
                    columnLabel = fromColumn.value();
                }
                Method method = ResultSetMethodProvider.getMethod(field.getType());
                fieldValueFromMethodResolver.put(new FieldWrapper(field, columnLabel), method);
            }
        }
    }

    private static class FieldWrapper {

        private final Field field;
        private final String columnLabel;

        public FieldWrapper(Field field, String columnLabel) {
            this.field = field;
            this.columnLabel = columnLabel;
        }

        public Field getField() {
            return field;
        }

        public String getColumnLabel() {
            return columnLabel;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final FieldWrapper that = (FieldWrapper) o;
            return field.equals(that.field) && columnLabel.equals(that.columnLabel);

        }

        @Override
        public int hashCode() {
            int result = field.hashCode();
            result = 31 * result + columnLabel.hashCode();
            return result;
        }
    }
}
