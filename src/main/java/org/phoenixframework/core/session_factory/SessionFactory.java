package org.phoenixframework.core.session_factory;

import org.phoenixframework.core.session.Session;
import org.phoenixframework.core.session.SessionImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.session.Session
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

    private DataSource dataSource;

    private SessionFactory() {
    }

    /**
     * Register the data source in session factory.
     *
     * @param dataSourceSupplier data source supplier
     */
    public void registerDataSource(DataSourceSupplier dataSourceSupplier) {
        this.dataSource = dataSourceSupplier.getDataSource();
    }

    /**
     * Returns a new session.
     *
     * @return a new session
     */
    public Session openSession() {
        try {
            return openSession(dataSource.getConnection());
        } catch (SQLException e) {
            throw new IllegalStateException(e);
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
