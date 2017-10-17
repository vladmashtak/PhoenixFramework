package org.phoenixframework.core.context.registry.metadata;

import java.lang.reflect.Field;

/**
 * @author Oleg Marchenko
 */

public class FieldMetadataHolder {

    private final Field field;
    private final String columnLabel;
    private final boolean wrapper;

    public FieldMetadataHolder(Field field, String columnLabel) {
        this(field, columnLabel, false);
    }

    public FieldMetadataHolder(Field field, String columnLabel, boolean wrapper) {
        this.field = field;
        this.columnLabel = columnLabel;
        this.wrapper = wrapper;
    }

    public Field getField() {
        return field;
    }

    public String getFieldName() {
        return field.getName();
    }

    public Class<?> getFieldType() {
        return field.getType();
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public boolean isWrapper() {
        return wrapper;
    }
}
