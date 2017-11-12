package org.phoenixframework.core.context.registry.descriptor;

import org.phoenixframework.core.exception.PhoenixException;
import org.phoenixframework.core.mapper.AliasResultMapper;
import org.phoenixframework.core.mapper.DataTransformer;
import org.phoenixframework.core.mapper.ResultMapper;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Oleg Marchenko
 */

public class DomainDescriptor {

    private final Class<?> domainType;
    private final DataTransformer domainTransformer;
    private final Map<String, String> namedQueries = new HashMap<>();
    private final Map<String, FieldDescriptor> fieldDescriptors = new LinkedHashMap<>();

    public DomainDescriptor(Class<?> domainType) {
        this(domainType, new AliasResultMapper<>(domainType));
    }

    public DomainDescriptor(Class<?> domainType, DataTransformer domainTransformer) {
        this.domainType = domainType;
        this.domainTransformer = domainTransformer;
    }

    public Class<?> getDomainType() {
        return domainType;
    }

    public DataTransformer getDomainTransformer() {
        return domainTransformer;
    }

    @SuppressWarnings("unchecked")
    public <T> ResultMapper<T> getDomainMapper() {
        return (ResultMapper<T>) domainTransformer;
    }

    public String getNamedQuery(String queryName) {
        String namedQuery = namedQueries.get(queryName);
        if (namedQuery == null) {
            throw new PhoenixException("Named query \"" + queryName + "\" for domain class \"" + domainType + "\" is not registered");
        }
        return namedQuery;
    }

    public void registerNamedQuery(String queryName, String query) {
        if (namedQueries.containsKey(queryName)) {
            throw new PhoenixException("Named query \"" + queryName + "\" for domain class \"" + domainType + "\" already registered");
        }
        namedQueries.put(queryName, query);
    }

    public Collection<FieldDescriptor> getFieldDescriptors() {
        return fieldDescriptors.values();
    }

    public void registerFieldDescriptor(FieldDescriptor fieldDescriptor) {
        fieldDescriptors.put(fieldDescriptor.getFieldName(), fieldDescriptor);
    }
}
