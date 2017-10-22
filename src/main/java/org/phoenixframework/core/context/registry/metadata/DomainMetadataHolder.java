package org.phoenixframework.core.context.registry.metadata;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.context.registry.metadata.FieldMetadataHolder
 */

public class DomainMetadataHolder {

    private final Class<?> domainClass;
    private final Map<String, FieldMetadataHolder> fieldMetadataRegistry = new LinkedHashMap<>();
    private final Map<String, String> namedQueryRegistry = new HashMap<>();

    public DomainMetadataHolder(Class<?> domainClass) {
        this.domainClass = domainClass;
    }

    public Class<?> getDomainClass() {
        return domainClass;
    }

    public Collection<FieldMetadataHolder> getAllFieldMetadata() {
        return fieldMetadataRegistry.values();
    }

    public FieldMetadataHolder getFieldMetadata(String fieldName) {
        return fieldMetadataRegistry.get(fieldName);
    }

    public void addFieldMetadata(FieldMetadataHolder fieldMetadataHolder) {
        fieldMetadataRegistry.put(fieldMetadataHolder.getFieldName(), fieldMetadataHolder);
    }

    public String getNamedQuery(String queryName) {
        return namedQueryRegistry.get(queryName);
    }

    public void registerNamedQuery(String queryName, String query) {
        namedQueryRegistry.put(queryName, query);
    }
}
