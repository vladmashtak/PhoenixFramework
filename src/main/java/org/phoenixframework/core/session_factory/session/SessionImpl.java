package org.phoenixframework.core.session_factory.session;

import org.phoenixframework.core.context.PhoenixContext;
import org.phoenixframework.core.context.registry.metadata.NamedQueryMetadataHolder;
import org.phoenixframework.core.exception.PhoenixException;
import org.phoenixframework.core.mapper.ResultMapper;
import org.phoenixframework.core.session_factory.session.query.NamedQuery;
import org.phoenixframework.core.session_factory.session.query.NamedQueryImpl;
import org.phoenixframework.core.session_factory.session.query.Query;
import org.phoenixframework.core.session_factory.session.query.QueryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.session_factory.session.Session
 */

public class SessionImpl implements Session {

    private final Connection connection;
    private final PhoenixContext context;

    public SessionImpl(Connection connection) {
        this(connection, null);
    }

    public SessionImpl(Connection connection, PhoenixContext context) {
        this.connection = connection;
        this.context = context;
    }

    @Override
    public Transaction beginTransaction() {
        Transaction transaction = new TransactionImpl(connection);
        transaction.begin();
        return transaction;
    }

    @Override
    public Query createQuery(String sqlQuery) {
        try {
            return new QueryImpl(connection.prepareStatement(sqlQuery));
        } catch (SQLException e) {
            throw new PhoenixException("Cannot create query", e);
        }
    }

    @Override
    public <T> NamedQuery<T> createNamedQuery(Class<T> domainClass, String queryName) {
        return createNamedQuery(domainClass, queryName, domainClass);
    }

    @Override
    public <T> NamedQuery<T> createNamedQuery(Class<?> domainClass, String queryName, Class<T> returnType) {
        NamedQueryMetadataHolder namedQueryMetadata = context.getNamedQueryMetadata(domainClass, queryName);
        ResultMapper<T> mapper = context.getMapper(returnType);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(namedQueryMetadata.getQuery());
            return new NamedQueryImpl<>(preparedStatement, mapper);
        } catch (SQLException e) {
            throw new PhoenixException("Cannot create query", e);
        }
    }

    @Override
    public boolean isOpen() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            throw new PhoenixException(e);
        }
    }

    @Override
    public void close() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new PhoenixException("Cannot close session", e);
        }
    }
}
