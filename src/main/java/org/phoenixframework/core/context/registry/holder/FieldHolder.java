package org.phoenixframework.core.context.registry.holder;

import org.phoenixframework.core.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author Oleg Marchenko
 */

public class FieldHolder {

    private final Field field;

    public FieldHolder(Field field) {
        this.field = field;
    }

    public String getFieldName() {
        return field.getName();
    }

    public Class<?> getFieldType() {
        return field.getType();
    }

    public Object getFieldValue(Object object) {
        return ReflectionUtils.getValueFromField(object, field);
    }

    public void setFieldValue(Object object, Object value) {
        ReflectionUtils.setValueToField(object, field, value);
    }
}
