package org.phoenixframework.core.session_factory.session;

import org.phoenixframework.core.context.registry.descriptor.DomainDescriptor;
import org.phoenixframework.core.exception.PhoenixException;
import org.phoenixframework.core.mapper.ResultMapper;
import org.phoenixframework.core.session_factory.SessionFactory;
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

    public SessionImpl(Connection connection) {
        this.connection = connection;
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
        DomainDescriptor domainDescriptor = SessionFactory.context().getDomain(domainClass);
        String query = domainDescriptor.getNamedQuery(queryName);
        ResultMapper<T> domainMapper = domainDescriptor.getDomainMapper();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            return new NamedQueryImpl<>(preparedStatement, domainMapper);
        } catch (SQLException e) {
            throw new PhoenixException("Cannot create query", e);
        }
    }

    @Override
    public <T> NamedQuery<T> createNamedQuery(Class<?> domainClass, String queryName, Class<T> returnType) {
        DomainDescriptor domainDescriptor = SessionFactory.context().getDomain(domainClass);
        String query = domainDescriptor.getNamedQuery(queryName);
        ResultMapper<T> mapper = SessionFactory.context().getStandardMapper(returnType);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
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
