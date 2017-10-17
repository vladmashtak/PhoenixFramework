package org.phoenixframework.core.context.registry.metadata;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.context.registry.metadata.FieldMetadataHolder
 */

public class DomainMetadataHolder {

    private final Class<?> domainClass;
    private final Map<String, FieldMetadataHolder> registry = new LinkedHashMap<>();

    public DomainMetadataHolder(Class<?> domainClass) {
        this.domainClass = domainClass;
    }

    public Class<?> getDomainClass() {
        return domainClass;
    }

    public Collection<FieldMetadataHolder> getAllFieldMetadata() {
        return registry.values();
    }

    public FieldMetadataHolder getFieldMetadata(String fieldName) {
        return registry.get(fieldName);
    }

    public void addFieldMetadata(FieldMetadataHolder fieldMetadataHolder) {
        registry.put(fieldMetadataHolder.getFieldName(), fieldMetadataHolder);
    }
}
