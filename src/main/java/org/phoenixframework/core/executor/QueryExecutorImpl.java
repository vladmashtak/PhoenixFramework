package org.phoenixframework.core.executor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.executor.QueryExecutor
 */

public class QueryExecutorImpl implements QueryExecutor {

    private final DataSource dataSource;

    public QueryExecutorImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
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

    @Override
    public CachedResultSet executeQuery(String query, Collection<Object> params) {
        return executeQuery(query, params.toArray());
    }

    @Override
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

    @Override
    public int executeUpdate(String query, Collection<Object> params) {
        return executeUpdate(query, params.toArray());
    }

    private void setParameters(PreparedStatement preparedStatement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
    }
}
