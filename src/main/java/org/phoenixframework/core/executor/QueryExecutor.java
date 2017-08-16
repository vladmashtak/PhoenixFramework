package org.phoenixframework.core.executor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Oleg Marchenko
 */

public class QueryExecutor {

    private final DataSource dataSource;

    public QueryExecutor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CachedResultSet executeQuery(String query, Object... params) {
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                setParameters(preparedStatement, params);
                try (ResultSet result = preparedStatement.executeQuery()) {
                    return new CachedResultSet(result);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot execute query", e);
        }
    }

    public int executeUpdate(String query, Object... params) {
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                setParameters(preparedStatement, params);
                return preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot execute update", e);
        }
    }

    private void setParameters(PreparedStatement preparedStatement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
    }
}
