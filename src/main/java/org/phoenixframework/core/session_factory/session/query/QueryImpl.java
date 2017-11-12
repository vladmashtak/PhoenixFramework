package org.phoenixframework.core.session_factory.session.query;

import org.phoenixframework.core.exception.PhoenixException;
import org.phoenixframework.core.session_factory.session.query.scrollable_result.CachedScrollableResult;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Collection;

/**
 * {@inheritDoc}
 *
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.session_factory.session.query.Query
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
            throw new PhoenixException("Cannot execute query", e);
        } finally {
            close();
        }
    }

    @Override
    public int executeUpdate() {
        try {
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PhoenixException("Cannot execute update", e);
        } finally {
            close();
        }
    }

    @Override
    public void addBatch() {
        try {
            preparedStatement.addBatch();
        } catch (SQLException e) {
            throw new PhoenixException("Cannot add new batch", e);
        }
    }

    @Override
    public int[] executeBatch() {
        try {
            return preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new PhoenixException("Cannot execute batch", e);
        } finally {
            close();
        }
    }

    @Override
    public boolean isActive() {
        try {
            return !preparedStatement.isClosed();
        } catch (SQLException e) {
            throw new PhoenixException(e);
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
            throw new PhoenixException("Cannot close query", e);
        }
    }

    @Override
    public Query setParameter(int paramIndex, boolean value) {
        try {
            preparedStatement.setBoolean(paramIndex, value);
        } catch (SQLException e) {
            throw new PhoenixException("Cannot set parameter", e);
        }
        return this;
    }

    @Override
    public Query setParameter(int paramIndex, byte value) {
        try {
            preparedStatement.setByte(paramIndex, value);
        } catch (SQLException e) {
            throw new PhoenixException("Cannot set parameter", e);
        }
        return this;
    }

    @Override
    public Query setParameter(int paramIndex, short value) {
        try {
            preparedStatement.setShort(paramIndex, value);
        } catch (SQLException e) {
            throw new PhoenixException("Cannot set parameter", e);
        }
        return this;
    }

    @Override
    public Query setParameter(int paramIndex, int value) {
        try {
            preparedStatement.setInt(paramIndex, value);
        } catch (SQLException e) {
            throw new PhoenixException("Cannot set parameter", e);
        }
        return this;
    }

    @Override
    public Query setParameter(int paramIndex, long value) {
        try {
            preparedStatement.setLong(paramIndex, value);
        } catch (SQLException e) {
            throw new PhoenixException("Cannot set parameter", e);
        }
        return this;
    }

    @Override
    public Query setParameter(int paramIndex, float value) {
        try {
            preparedStatement.setFloat(paramIndex, value);
        } catch (SQLException e) {
            throw new PhoenixException("Cannot set parameter", e);
        }
        return this;
    }

    @Override
    public Query setParameter(int paramIndex, double value) {
        try {
            preparedStatement.setDouble(paramIndex, value);
        } catch (SQLException e) {
            throw new PhoenixException("Cannot set parameter", e);
        }
        return this;
    }

    @Override
    public Query setParameter(int paramIndex, BigDecimal value) {
        try {
            preparedStatement.setBigDecimal(paramIndex, value);
        } catch (SQLException e) {
            throw new PhoenixException("Cannot set parameter", e);
        }
        return this;
    }

    @Override
    public Query setParameter(int paramIndex, String value) {
        try {
            preparedStatement.setString(paramIndex, value);
        } catch (SQLException e) {
            throw new PhoenixException("Cannot set parameter", e);
        }
        return this;
    }

    @Override
    public Query setParameter(int paramIndex, byte[] value) {
        try {
            preparedStatement.setBytes(paramIndex, value);
        } catch (SQLException e) {
            throw new PhoenixException("Cannot set parameter", e);
        }
        return this;
    }

    @Override
    public Query setParameter(int paramIndex, Date value) {
        try {
            preparedStatement.setDate(paramIndex, value);
        } catch (SQLException e) {
            throw new PhoenixException("Cannot set parameter", e);
        }
        return this;
    }

    @Override
    public Query setParameter(int paramIndex, Time value) {
        try {
            preparedStatement.setTime(paramIndex, value);
        } catch (SQLException e) {
            throw new PhoenixException("Cannot set parameter", e);
        }
        return this;
    }

    @Override
    public Query setParameter(int paramIndex, Timestamp value) {
        try {
            preparedStatement.setTimestamp(paramIndex, value);
        } catch (SQLException e) {
            throw new PhoenixException("Cannot set parameter", e);
        }
        return this;
    }

    @Override
    public Query setParameter(int paramIndex, Object value) {
        try {
            preparedStatement.setObject(paramIndex, value);
        } catch (SQLException e) {
            throw new PhoenixException("Cannot set parameter", e);
        }
        return this;
    }

    @Override
    public Query setParameters(Object... parameters) {
        for (int i = 0; i < parameters.length; i++) {
            setParameter(i + 1, parameters[i]);
        }
        return this;
    }

    @Override
    public Query setParameters(Collection<Object> parameterValues) {
        return setParameters(parameterValues.toArray());
    }
}
