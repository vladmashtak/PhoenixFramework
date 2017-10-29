package org.phoenixframework.core.context.registry.metadata;

/**
 * @author Oleg Marchenko
 */

public class NamedQueryMetadataHolder {

    private final String queryName;
    private final String query;
    private final Class<?> returnType;

    public NamedQueryMetadataHolder(String queryName, String query, Class<?> returnType) {
        this.queryName = queryName;
        this.query = query;
        this.returnType = returnType;
    }

    public String getQueryName() {
        return queryName;
    }

    public String getQuery() {
        return query;
    }

    public Class<?> getReturnType() {
        return returnType;
    }
}
