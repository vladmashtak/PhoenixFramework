package org.phoenixframework.core.session_factory.session.query;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.session_factory.session.query.Query
 */

public interface NamedQuery<T> extends Query {

    /**
     * Returns unique mapping domain object.
     *
     * @return unique mapping domain object
     */
    T unique();

    /**
     * Returns list of mapping domain objects.
     *
     * @return list of mapping domain objects
     */
    List<T> list();

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setBoolean(int paramIndex, boolean value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setByte(int paramIndex, byte value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setShort(int paramIndex, short value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setInt(int paramIndex, int value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setLong(int paramIndex, long value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setFloat(int paramIndex, float value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setDouble(int paramIndex, double value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setBigDecimal(int paramIndex, BigDecimal value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setString(int paramIndex, String value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setBytes(int paramIndex, byte[] value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setDate(int paramIndex, Date value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setTime(int paramIndex, Time value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setTimestamp(int paramIndex, Timestamp value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setObject(int paramIndex, Object value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setParameters(Object... parameterValues);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setParameters(Collection<Object> parameterValues);
}
