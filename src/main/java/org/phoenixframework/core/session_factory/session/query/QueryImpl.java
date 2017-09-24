package org.phoenixframework.core.session_factory.session.query;

import org.phoenixframework.core.session_factory.session.query.scrollable_result.CachedScrollableResult;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Collection;

/**
 * @author Oleg Marchenko
 * @see Query
 */

public class QueryImpl implements Query {

    private final PreparedStatement preparedStatement;

    public QueryImpl(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    @Override
    public CachedScrollableResult execute() {
        try {
            try (ResultSet result = preparedStatement.executeQuery()) {
                return new CachedScrollableResult(result);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            close();
        }
    }

    @Override
    public int executeUpdate() {
        try {
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            close();
        }
    }

    @Override
    public void addBatch() {
        try {
            preparedStatement.addBatch();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int[] executeBatch() {
        try {
            return preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            close();
        }
    }

    @Override
    public boolean isActive() {
        try {
            return !preparedStatement.isClosed();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Releases this <code>Query</code> object's database and JDBC resources immediately.
     */
    protected void close() {
        try {
            if (!preparedStatement.isClosed()) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Query setBoolean(int paramIndex, boolean value) {
        try {
            preparedStatement.setBoolean(paramIndex, value);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return this;
    }

    @Override
    public Query setByte(int paramIndex, byte value) {
        try {
            preparedStatement.setByte(paramIndex, value);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return this;
    }

    @Override
    public Query setShort(int paramIndex, short value) {
        try {
            preparedStatement.setShort(paramIndex, value);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return this;
    }

    @Override
    public Query setInt(int paramIndex, int value) {
        try {
            preparedStatement.setInt(paramIndex, value);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return this;
    }

    @Override
    public Query setLong(int paramIndex, long value) {
        try {
            preparedStatement.setLong(paramIndex, value);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return this;
    }

    @Override
    public Query setFloat(int paramIndex, float value) {
        try {
            preparedStatement.setFloat(paramIndex, value);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return this;
    }

    @Override
    public Query setDouble(int paramIndex, double value) {
        try {
            preparedStatement.setDouble(paramIndex, value);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return this;
    }

    @Override
    public Query setBigDecimal(int paramIndex, BigDecimal value) {
        try {
            preparedStatement.setBigDecimal(paramIndex, value);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return this;
    }

    @Override
    public Query setString(int paramIndex, String value) {
        try {
            preparedStatement.setString(paramIndex, value);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return this;
    }

    @Override
    public Query setBytes(int paramIndex, byte[] value) {
        try {
            preparedStatement.setBytes(paramIndex, value);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return this;
    }

    @Override
    public Query setDate(int paramIndex, Date value) {
        try {
            preparedStatement.setDate(paramIndex, value);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return this;
    }

    @Override
    public Query setTime(int paramIndex, Time value) {
        try {
            preparedStatement.setTime(paramIndex, value);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return this;
    }

    @Override
    public Query setTimestamp(int paramIndex, Timestamp value) {
        try {
            preparedStatement.setTimestamp(paramIndex, value);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return this;
    }

    @Override
    public Query setObject(int paramIndex, Object value) {
        try {
            preparedStatement.setObject(paramIndex, value);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return this;
    }

    @Override
    public Query setParameters(Object... parameterValues) {
        for (int i = 0; i < parameterValues.length; i++) {
            setObject(i + 1, parameterValues[i]);
        }
        return this;
    }

    @Override
    public Query setParameters(final Collection<Object> parameterValues) {
        return setParameters(parameterValues.toArray());
    }
}
