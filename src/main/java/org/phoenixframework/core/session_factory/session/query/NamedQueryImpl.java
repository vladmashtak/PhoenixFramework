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
 * {@inheritDoc}
 *
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.session_factory.session.query.QueryImpl
 * @see org.phoenixframework.core.session_factory.session.query.NamedQuery
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
    public NamedQuery<T> setParameter(final int paramIndex, final boolean value) {
        super.setParameter(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setParameter(final int paramIndex, final byte value) {
        super.setParameter(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setParameter(final int paramIndex, final short value) {
        super.setParameter(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setParameter(final int paramIndex, final int value) {
        super.setParameter(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setParameter(final int paramIndex, final long value) {
        super.setParameter(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setParameter(final int paramIndex, final float value) {
        super.setParameter(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setParameter(final int paramIndex, final double value) {
        super.setParameter(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setParameter(final int paramIndex, final BigDecimal value) {
        super.setParameter(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setParameter(final int paramIndex, final String value) {
        super.setParameter(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setParameter(final int paramIndex, final byte[] value) {
        super.setParameter(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setParameter(final int paramIndex, final Date value) {
        super.setParameter(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setParameter(final int paramIndex, final Time value) {
        super.setParameter(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setParameter(final int paramIndex, final Timestamp value) {
        super.setParameter(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setParameter(final int paramIndex, final Object value) {
        super.setParameter(paramIndex, value);
        return this;
    }

    @Override
    public NamedQuery<T> setParameters(final Object... parameters) {
        super.setParameters(parameters);
        return this;
    }

    @Override
    public NamedQuery<T> setParameters(final Collection<Object> parameterValues) {
        super.setParameters(parameterValues);
        return this;
    }
}
