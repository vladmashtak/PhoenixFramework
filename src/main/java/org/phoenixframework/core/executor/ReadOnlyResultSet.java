package org.phoenixframework.core.executor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Interface provides read-only access for result set by column label.
 *
 * @author Oleg Marchenko
 */

public interface ReadOnlyResultSet {

    /**
     * Returns the value of {@code Boolean} object from column by label.
     *
     * @return the value of {@code Boolean} object, or
     *         {@code null} if the result set does not contain a column with this label
     * @throws ClassCastException if the column is of an inappropriate type
     */
    Boolean getBoolean(String columnLabel);

    /**
     * Returns the value of {@code Byte} object from column by label.
     *
     * @return the value of {@code Byte} object, or
     *         {@code null} if the result set does not contain a column with this label
     * @throws ClassCastException if the column is of an inappropriate type
     */
    Byte getByte(String columnLabel);

    /**
     * Returns the value of {@code Short} object from column by label.
     *
     * @return the value of {@code Short} object, or
     *         {@code null} if the result set does not contain a column with this label
     * @throws ClassCastException if the column is of an inappropriate type
     */
    Short getShort(String columnLabel);

    /**
     * Returns the value of {@code Integer} object from column by label.
     *
     * @return the value of {@code Integer} object, or
     *         {@code null} if the result set does not contain a column with this label
     * @throws ClassCastException if the column is of an inappropriate type
     */
    Integer getInt(String columnLabel);

    /**
     * Returns the value of {@code Long} object from column by label.
     *
     * @return the value of {@code Long} object, or
     *         {@code null} if the result set does not contain a column with this label
     * @throws ClassCastException if the column is of an inappropriate type
     */
    Long getLong(String columnLabel);

    /**
     * Returns the value of {@code Float} object from column by label.
     *
     * @return the value of {@code Float} object, or
     *         {@code null} if the result set does not contain a column with this label
     * @throws ClassCastException if the column is of an inappropriate type
     */
    Float getFloat(String columnLabel);

    /**
     * Returns the value of {@code Double} object from column by label.
     *
     * @return the value of {@code Double} object, or
     *         {@code null} if the result set does not contain a column with this label
     * @throws ClassCastException if the column is of an inappropriate type
     */
    Double getDouble(String columnLabel);

    /**
     * Returns the value of {@code String} object from column by label.
     *
     * @return the value of {@code String} object, or
     *         {@code null} if the result set does not contain a column with this label
     * @throws ClassCastException if the column is of an inappropriate type
     */
    String getString(String columnLabel);

    /**
     * Returns the value of {@code Object} from column by label.
     *
     * @return the value of {@code Object}, or
     *         {@code null} if the result set does not contain a column with this label
     * @throws ClassCastException if the column is of an inappropriate type
     */
    Object getObject(String columnLabel);

    /**
     * Returns the value of {@code Time} object from column by label.
     *
     * @return the value of {@code Time} object, or
     *         {@code null} if the result set does not contain a column with this label
     * @throws ClassCastException if the column is of an inappropriate type
     */
    Time getTime(String columnLabel);

    /**
     * Returns the value of {@code Timestamp} object from column by label.
     *
     * @return the value of {@code Timestamp} object, or
     *         {@code null} if the result set does not contain a column with this label
     * @throws ClassCastException if the column is of an inappropriate type
     */
    Timestamp getTimestamp(String columnLabel);

    /**
     * Returns the value of {@code Date} object from column by label.
     *
     * @return the value of {@code Date} object, or
     *         {@code null} if the result set does not contain a column with this label
     * @throws ClassCastException if the column is of an inappropriate type
     */
    Date getDate(String columnLabel);

    /**
     * Returns the value of {@code BigDecimal} object from column by label.
     *
     * @return the value of {@code BigDecimal} object, or
     *         {@code null} if the result set does not contain a column with this label
     * @throws ClassCastException if the column is of an inappropriate type
     */
    BigDecimal getBigDecimal(String columnLabel);
}
