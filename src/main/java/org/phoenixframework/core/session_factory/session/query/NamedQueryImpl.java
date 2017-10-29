package org.phoenixframework.core.session_factory.session.query;

import org.phoenixframework.core.mapper.ResultMapper;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * @author Oleg Marchenko
 */

public class NamedQueryImpl<T> extends QueryImpl implements NamedQuery<T> {

    private final ResultMapper<T> mapper;

    public NamedQueryImpl(PreparedStatement preparedStatement, ResultMapper<T> mapper) {
        super(preparedStatement);
        this.mapper = mapper;
    }

    @Override
    public T unique() {
        return mapper.unique(execute());
    }

    @Override
    public List<T> list() {
        return mapper.list(execute());
    }

    @Override
    public NamedQuery<T> setBoolean(final int paramIndex, final boolean value) {
        super.setBoolean(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setByte(final int paramIndex, final byte value) {
        super.setByte(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setShort(final int paramIndex, final short value) {
        super.setShort(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setInt(final int paramIndex, final int value) {
        super.setInt(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setLong(final int paramIndex, final long value) {
        super.setLong(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setFloat(final int paramIndex, final float value) {
        super.setFloat(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setDouble(final int paramIndex, final double value) {
        super.setDouble(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setBigDecimal(final int paramIndex, final BigDecimal value) {
        super.setBigDecimal(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setString(final int paramIndex, final String value) {
        super.setString(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setBytes(final int paramIndex, final byte[] value) {
        super.setBytes(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setDate(final int paramIndex, final Date value) {
        super.setDate(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setTime(final int paramIndex, final Time value) {
        super.setTime(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setTimestamp(final int paramIndex, final Timestamp value) {
        super.setTimestamp(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setObject(final int paramIndex, final Object value) {
        super.setObject(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setParameters(final Object... parameterValues) {
        super.setParameters(parameterValues);
        return this;
    }

    @Override
    public NamedQuery<T> setParameters(final Collection<Object> parameterValues) {
        super.setParameters(parameterValues);
        return this;
    }
}
