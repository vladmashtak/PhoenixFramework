package org.phoenixframework.core.session;

import org.phoenixframework.core.executor.CachedResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.session.Session
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
    public CachedResultSet executeQuery(String query, Object... params) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setParameters(preparedStatement, params);
            try (ResultSet result = preparedStatement.executeQuery()) {
                return new CachedResultSet(result);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int executeUpdate(String query, Object... params) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setParameters(preparedStatement, params);
            return preparedStatement.executeUpdate();
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

    private void setParameters(PreparedStatement preparedStatement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
    }
}
