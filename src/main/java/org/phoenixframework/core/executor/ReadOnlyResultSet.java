package org.phoenixframework.core.executor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Interface provides read-only access for cached result set by column index or label.
 *
 * @author Oleg Marchenko
 * @see java.sql.ResultSet
 */

public interface ReadOnlyResultSet {

    /**
     * Returns the value of {@code boolean} type from column by index.
     *
     * @return the value of {@code boolean} type
     * @throws IndexOutOfBoundsException if the column index is not valid
     * @throws ClassCastException if the column is of an inappropriate type
     */
    boolean getBoolean(int columnIndex);

    /**
     * Returns the value of {@code byte} type from column by index.
     *
     * @return the value of {@code byte} type
     * @throws IndexOutOfBoundsException if the column index is not valid
     * @throws ClassCastException if the column is of an inappropriate type
     */
    byte getByte(int columnIndex);

    /**
     * Returns the value of {@code short} type from column by index.
     *
     * @return the value of {@code short} type
     * @throws IndexOutOfBoundsException if the column index is not valid
     * @throws ClassCastException if the column is of an inappropriate type
     */
    short getShort(int columnIndex);

    /**
     * Returns the value of {@code int} type from column by index.
     *
     * @return the value of {@code int} type
     * @throws IndexOutOfBoundsException if the column index is not valid
     * @throws ClassCastException if the column is of an inappropriate type
     */
    int getInt(int columnIndex);

    /**
     * Returns the value of {@code long} type from column by index.
     *
     * @return the value of {@code long} type
     * @throws IndexOutOfBoundsException if the column index is not valid
     * @throws ClassCastException if the column is of an inappropriate type
     */
    long getLong(int columnIndex);

    /**
     * Returns the value of {@code float} type from column by index.
     *
     * @return the value of {@code float} type
     * @throws IndexOutOfBoundsException if the column index is not valid
     * @throws ClassCastException if the column is of an inappropriate type
     */
    float getFloat(int columnIndex);

    /**
     * Returns the value of {@code double} type from column by index.
     *
     * @return the value of {@code double} type from column
     * @throws IndexOutOfBoundsException if the column index is not valid
     * @throws ClassCastException if the column is of an inappropriate type
     */
    double getDouble(int columnIndex);

    /**
     * Returns the value of {@code String} object from column by index.
     *
     * @return the value of {@code String} object
     * @throws IndexOutOfBoundsException if the column index is not valid
     * @throws ClassCastException if the column is of an inappropriate type
     */
    String getString(int columnIndex);

    /**
     * Returns the value of {@code Object} from column by index.
     *
     * @return the value of {@code Object}
     * @throws IndexOutOfBoundsException if the column index is not valid
     * @throws ClassCastException if the column is of an inappropriate type
     */
    Object getObject(int columnIndex);

    /**
     * Returns the value of {@code Time} object from column by index.
     *
     * @return the value of {@code Time} object
     * @throws IndexOutOfBoundsException if the column index is not valid
     * @throws ClassCastException if the column is of an inappropriate type
     */
    Time getTime(int columnIndex);

    /**
     * Returns the value of {@code Timestamp} object from column by index.
     *
     * @return the value of {@code Timestamp} object
     * @throws IndexOutOfBoundsException if the column index is not valid
     * @throws ClassCastException if the column is of an inappropriate type
     */
    Timestamp getTimestamp(int columnIndex);

    /**
     * Returns the value of {@code Date} object from column by index.
     *
     * @return the value of {@code Date} object
     * @throws IndexOutOfBoundsException if the column index is not valid
     * @throws ClassCastException if the column is of an inappropriate type
     */
    Date getDate(int columnIndex);

    /**
     * Returns the value of {@code BigDecimal} object from column by index.
     *
     * @return the value of {@code BigDecimal} object
     * @throws IndexOutOfBoundsException if the column index is not valid
     * @throws ClassCastException if the column is of an inappropriate type
     */
    BigDecimal getBigDecimal(int columnIndex);

    /**
     * Returns the value of {@code byte} array from column by index.
     *
     * @return the value of {@code byte} array
     * @throws IndexOutOfBoundsException if the column index is not valid
     * @throws ClassCastException if the column is of an inappropriate type
     */
    byte[] getBytes(int columnIndex);

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

    /**
     * Returns the value of {@code byte} array from column by label.
     *
     * @return the value of {@code byte} array, or
     *         {@code null} if the result set does not contain a column with this label
     * @throws ClassCastException if the column is of an inappropriate type
     */
    byte[] getBytes(String columnLabel);
}
