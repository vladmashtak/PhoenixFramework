package org.phoenixframework.core.session_factory.session;

import org.phoenixframework.core.exception.PhoenixException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.session_factory.session.Transaction
 */

public class TransactionImpl implements Transaction {

    private final Connection connection;

    public TransactionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void begin() {
        try {
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            throw new PhoenixException("Cannot begin transaction", e);
        }
    }

    @Override
    public void commit() {
        try {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
        } catch (SQLException e) {
            throw new PhoenixException("Cannot commit transaction", e);
        }
    }

    @Override
    public void rollback() {
        try {
            if (!connection.getAutoCommit()) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new PhoenixException("Cannot rollback transaction", e);
        }
    }
}
