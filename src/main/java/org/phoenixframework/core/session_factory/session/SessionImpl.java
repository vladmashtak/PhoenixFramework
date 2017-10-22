package org.phoenixframework.core.session_factory.session;

import org.phoenixframework.core.context.PhoenixContext;
import org.phoenixframework.core.context.registry.metadata.DomainMetadataHolder;
import org.phoenixframework.core.exception.PhoenixException;
import org.phoenixframework.core.session_factory.session.query.Query;
import org.phoenixframework.core.session_factory.session.query.QueryImpl;

import java.sql.Connection;
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
    public Query createNamedQuery(String queryName, Class<?> domainClass) {
        DomainMetadataHolder domainMetadata = context.getDomainMetadata(domainClass);
        if (domainMetadata == null) {
            throw new PhoenixException("Domain object for class \"" + domainClass.getSimpleName() + "\" is not registered");
        }

        String sqlQuery = domainMetadata.getNamedQuery(queryName);
        if (sqlQuery == null) {
            throw new PhoenixException("Named query \"" + queryName + "\" for domain object \"" + domainClass.getSimpleName() + "\" is not registered");
        }

        try {
            return new QueryImpl(connection.prepareStatement(sqlQuery));
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
