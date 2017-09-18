package org.phoenixframework.core.session;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.session.Session
 */

public final class SessionFactory {

    private final DataSource dataSource;

    public SessionFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Session openSession() {
        try {
            return openSession(dataSource.getConnection());
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static SessionFactory create(DataSource dataSource) {
        return new SessionFactory(dataSource);
    }

    public static Session openSession(Connection connection) {
        return new SessionImpl(connection);
    }
}
