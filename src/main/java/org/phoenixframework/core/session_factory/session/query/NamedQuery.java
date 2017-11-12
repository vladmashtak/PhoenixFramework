package org.phoenixframework.core.session_factory.session.query;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * The <code>NamedQuery</code> represents a parametrized query for specific type or domain class.
 *
 * @param <T> the specific type or domain class
 *
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
    NamedQuery<T> setParameter(int paramIndex, boolean value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setParameter(int paramIndex, byte value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setParameter(int paramIndex, short value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setParameter(int paramIndex, int value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setParameter(int paramIndex, long value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setParameter(int paramIndex, float value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setParameter(int paramIndex, double value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setParameter(int paramIndex, BigDecimal value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setParameter(int paramIndex, String value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setParameter(int paramIndex, byte[] value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setParameter(int paramIndex, Date value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setParameter(int paramIndex, Time value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setParameter(int paramIndex, Timestamp value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setParameter(int paramIndex, Object value);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setParameters(Object... parameters);

    /**
     * {@inheritDoc}
     */
    @Override
    NamedQuery<T> setParameters(Collection<Object> parameterValues);
}
