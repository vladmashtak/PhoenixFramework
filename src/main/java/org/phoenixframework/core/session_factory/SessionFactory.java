package org.phoenixframework.core.session_factory;

import org.phoenixframework.core.context.PhoenixContext;
import org.phoenixframework.core.exception.PhoenixException;
import org.phoenixframework.core.session_factory.session.Session;
import org.phoenixframework.core.session_factory.session.SessionImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.session_factory.session.Session
 */

public final class SessionFactory {
    private static final SessionFactory FACTORY_INSTANCE = new SessionFactory();

    /**
     * Returns session factory instance.
     *
     * @return session factory instance
     */
    public static SessionFactory instance() {
        return FACTORY_INSTANCE;
    }

    private static class PhoenixContextHolder {
        public static final PhoenixContext CONTEXT_INSTANCE = new PhoenixContext();
    }

    public static PhoenixContext context() {
        return PhoenixContextHolder.CONTEXT_INSTANCE;
    }

    private DataSource dataSource;

    private SessionFactory() {
    }

    /**
     * Register the data source in session factory.
     *
     * @param supplier data source supplier
     */
    public void registerDataSource(DataSourceSupplier supplier) {
        this.dataSource = supplier.getDataSource();
    }

    /**
     * Returns a new session.
     *
     * @return a new session
     */
    public Session openSession() {
        if (dataSource == null) {
            throw new PhoenixException("DataSource is not registered");
        }

        try {
            return openSession(dataSource.getConnection());
        } catch (SQLException e) {
            throw new PhoenixException("Cannot open session", e);
        }
    }

    /**
     * Returns a new session with custom connection.
     *
     * @param connection custom connection
     * @return a new session with custom connection
     */
    public Session openSession(Connection connection) {
        return new SessionImpl(connection);
    }
}
