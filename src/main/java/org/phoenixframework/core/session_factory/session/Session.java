package org.phoenixframework.core.session_factory.session;

import org.phoenixframework.core.session_factory.session.query.Query;

/**
 * The <code>Session</code> presents advanced capabilities over a standard {@link java.sql.Connection}.
 *
 * Examples:
 * try (Session session = SessionFactory.instance().openSession()) {
 *     session
 *         .createQuery("...");
 * }
 *
 * try (Session session = SessionFactory.instance().openSession()) {
 *     session
 *         .createQuery("...");
 * } catch (Exception e) {
 *
 * }
 *
 * try (Session session = SessionFactory.instance().openSession()) {
 *     Transaction tx = session.beginTransaction();
 *     try {
 *         session
 *             .createQuery("...")
 *             .executeUpdate();
 *
 *         tx.commit();
 *     } catch (Exception e) {
 *         tx.rollback();
 *     }
 * }
 *
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.session_factory.session.query.Query
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
     * Creates a new named query for executing.
     *
     * @param queryName query name
     * @param domainClass registered domain class
     * @return a new named query for executing
     */
    Query createNamedQuery(String queryName, Class<?> domainClass);

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
