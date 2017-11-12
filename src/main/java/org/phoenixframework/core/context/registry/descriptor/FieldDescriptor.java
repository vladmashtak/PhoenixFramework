package org.phoenixframework.core.context.registry.descriptor;

import org.phoenixframework.core.context.registry.extractor.ValueExtractor;
import org.phoenixframework.core.context.registry.holder.FieldHolder;

/**
 * @author Oleg Marchenko
 */

public class FieldDescriptor {

    private final FieldHolder fieldHolder;
    private final String columnAlias;
    private final ValueExtractor valueExtractor;

    public FieldDescriptor(FieldHolder fieldHolder, String columnAlias, ValueExtractor valueExtractor) {
        this.fieldHolder = fieldHolder;
        this.columnAlias = columnAlias;
        this.valueExtractor = valueExtractor;
    }

    public String getFieldName() {
        return fieldHolder.getFieldName();
    }

    public Class<?> getFieldType() {
        return fieldHolder.getFieldType();
    }

    public Object getFieldValue(Object object) {
        return fieldHolder.getFieldValue(object);
    }

    public void setFieldValue(Object object, Object value) {
        fieldHolder.setFieldValue(object, value);
    }

    public String getColumnAlias() {
        return columnAlias;
    }

    public ValueExtractor getValueExtractor() {
        return valueExtractor;
    }
}
