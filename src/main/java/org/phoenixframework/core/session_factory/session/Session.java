package org.phoenixframework.core.session_factory.session;

import org.phoenixframework.core.session_factory.session.query.NamedQuery;
import org.phoenixframework.core.session_factory.session.query.Query;

/**
 * The <code>Session</code> presents advanced capabilities over a standard {@link java.sql.Connection}.
 *
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.session_factory.session.query.Query
 * @see org.phoenixframework.core.session_factory.session.query.NamedQuery
 * @see org.phoenixframework.core.session_factory.session.Transaction
 */

public interface Session extends AutoCloseable {

    /**
     * Begin a unit of work and return the associated {@link Transaction} object.
     *
     * @return the associated {@link Transaction} object
     */
    Transaction beginTransaction();

    /**
     * Creates a new query for executing.
     *
     * @param sqlQuery SQL query to execute
     * @return a new query for executing
     */
    Query createQuery(String sqlQuery);

    /**
     * Creates a new named query with return type as domain class.
     *
     * @param domainClass registered domain class
     * @param queryName registered query name
     * @return a new named query with return type as domain class
     */
    <T> NamedQuery<T> createNamedQuery(Class<T> domainClass, String queryName);

    /**
     * Creates a new named query for domain class with a custom return type.
     *
     * @param domainClass registered domain class
     * @param queryName registered query name
     * @param returnType custom return type
     * @return a new named query for domain class with a custom return type
     */
    <T> NamedQuery<T> createNamedQuery(Class<?> domainClass, String queryName, Class<T> returnType);

    /**
     * Returns <tt>true</tt> if the session is still open.
     *
     * @return <tt>true</tt> if the session is still open
     */
    boolean isOpen();

    /**
     * End the session by releasing the JDBC connection and cleaning up.
     */
    @Override
    void close();
}
