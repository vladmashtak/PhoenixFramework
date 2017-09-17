package org.phoenixframework.core.session;

/**
 * Allows to define unit of work.
 * A transaction is associated with a <code>Session</code> and is usually instantiated by
 * a call to {@link Session#beginTransaction()}.
 *
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.session.Session
 */

public interface Transaction {

    /**
     * Begin a new transaction.
     *
     * @throws IllegalStateException if the transaction cannot be started
     */
    void begin();

    /**
     * Flush the associated Session and end the unit of work.
     *
     * @throws IllegalStateException if the transaction cannot be committed
     */
    void commit();

    /**
     * Force the underlying transaction to roll back.
     *
     * @throws IllegalStateException if the transaction cannot be roll backed
     */
    void rollback();
}
