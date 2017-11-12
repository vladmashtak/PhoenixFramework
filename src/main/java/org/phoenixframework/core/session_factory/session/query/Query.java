package org.phoenixframework.core.session_factory.session.query;

import org.phoenixframework.core.session_factory.session.query.scrollable_result.CachedScrollableResult;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * The <code>Query</code> presents advanced capabilities over a standard {@link java.sql.PreparedStatement}.
 *
 * @author Oleg Marchenko
 */

public interface Query {

    /**
     * Executes the SQL query and returns the {@link CachedScrollableResult} object generated by the query.
     *
     * @return a <code>CachedScrollableResult</code> object that contains the data produced by the
     *         query; never <code>null</code>
     */
    CachedScrollableResult execute();

    /**
     * Executes the SQL query, which must be an SQL Data Manipulation Language (DML) statement,
     * such as <code>INSERT</code>, <code>UPDATE</code> or <code>DELETE</code>;
     * or an SQL statement that returns nothing, such as a DDL statement.
     *
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 for SQL statements that return nothing
     */
    int executeUpdate();

    /**
     * Adds a set of parameters to this <code>Query</code> object's batch of commands.
     */
    void addBatch();

    /**
     * Submits a batch of commands to the database for execution and
     * if all commands execute successfully, returns an array of update counts.
     *
     * @return an array of update counts containing one element for each
     *         command in the batch
     */
    int[] executeBatch();

    /**
     * Returns <tt>true</tt> if the query is still active.
     *
     * @return <tt>true</tt> if the query is still active
     */
    boolean isActive();

    /**
     * Sets the designated parameter to the given Java <code>boolean</code> value.
     *
     * @param paramIndex the index of the parameter which must begin with 1
     * @param value the parameter value
     * @return instance of this query
     */
    Query setParameter(int paramIndex, boolean value);

    /**
     * Sets the designated parameter to the given Java <code>byte</code> value.
     *
     * @param paramIndex the index of the parameter which must begin with 1
     * @param value the parameter value
     * @return instance of this query
     */
    Query setParameter(int paramIndex, byte value);

    /**
     * Sets the designated parameter to the given Java <code>short</code> value.
     *
     * @param paramIndex the index of the parameter which must begin with 1
     * @param value the parameter value
     * @return instance of this query
     */
    Query setParameter(int paramIndex, short value);

    /**
     * Sets the designated parameter to the given Java <code>int</code> value.
     *
     * @param paramIndex the index of the parameter which must begin with 1
     * @param value the parameter value
     * @return instance of this query
     */
    Query setParameter(int paramIndex, int value);

    /**
     * Sets the designated parameter to the given Java <code>long</code> value.
     *
     * @param paramIndex the index of the parameter which must begin with 1
     * @param value the parameter value
     * @return instance of this query
     */
    Query setParameter(int paramIndex, long value);

    /**
     * Sets the designated parameter to the given Java <code>float</code> value.
     *
     * @param paramIndex the index of the parameter which must begin with 1
     * @param value the parameter value
     * @return instance of this query
     */
    Query setParameter(int paramIndex, float value);

    /**
     * Sets the designated parameter to the given Java <code>double</code> value.
     *
     * @param paramIndex the index of the parameter which must begin with 1
     * @param value the parameter value
     * @return instance of this query
     */
    Query setParameter(int paramIndex, double value);

    /**
     * Sets the designated parameter to the given Java <code>java.math.BigDecimal</code> value.
     *
     * @param paramIndex the index of the parameter which must begin with 1
     * @param value the parameter value
     * @return instance of this query
     */
    Query setParameter(int paramIndex, BigDecimal value);

    /**
     * Sets the designated parameter to the given Java <code>String</code> value.
     *
     * @param paramIndex the index of the parameter which must begin with 1
     * @param value the parameter value
     * @return instance of this query
     */
    Query setParameter(int paramIndex, String value);

    /**
     * Sets the designated parameter to the given Java array of bytes.
     *
     * @param paramIndex the index of the parameter which must begin with 1
     * @param value the parameter value
     * @return instance of this query
     */
    Query setParameter(int paramIndex, byte[] value);

    /**
     * Sets the designated parameter to the given Java <code>java.sql.Date</code> value.
     *
     * @param paramIndex the index of the parameter which must begin with 1
     * @param value the parameter value
     * @return instance of this query
     */
    Query setParameter(int paramIndex, Date value);

    /**
     * Sets the designated parameter to the given Java <code>java.sql.Time</code> value.
     *
     * @param paramIndex the index of the parameter which must begin with 1
     * @param value the parameter value
     * @return instance of this query
     */
    Query setParameter(int paramIndex, Time value);

    /**
     * Sets the designated parameter to the given Java <code>java.sql.Timestamp</code> value.
     *
     * @param paramIndex the index of the parameter which must begin with 1
     * @param value the parameter value
     * @return instance of this query
     */
    Query setParameter(int paramIndex, Timestamp value);

    /**
     * Sets the value of the designated parameter with the given object.
     *
     * @param paramIndex the index of the parameter which must begin with 1
     * @param value the parameter value
     * @return instance of this query
     */
    Query setParameter(int paramIndex, Object value);

    /**
     * Sets the values of the designated parameter with the given object.
     *
     * @param parameters the parameter values
     * @return instance of this query
     */
    Query setParameters(Object... parameters);

    /**
     * Sets the collection of values of the designated parameter with the given object.
     *
     * @param parameterValues the parameter values
     * @return instance of this query
     */
    Query setParameters(Collection<Object> parameterValues);
}
