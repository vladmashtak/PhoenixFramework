package org.phoenixframework.core.session_factory.session;

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
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean isOpen() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
