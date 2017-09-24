package org.phoenixframework.core.session_factory.session.query.scrollable_result;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Caches data from the result set providing the same interaction interface.
 *
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.session_factory.session.query.scrollable_result.ReadOnlyScrollableResult
 * @see java.sql.ResultSet
 */

public class CachedScrollableResult implements ReadOnlyScrollableResult {
    private static int DEFAULT_ROWS_CAPACITY = 64;
    private static int BEFORE_FIRST_ROW_INDEX = -1;

    private final List<List<Object>> cachedResultByIndex;
    private final Map<String, Integer> columnLabelToIndexRegistry;
    private int cursorIndex = BEFORE_FIRST_ROW_INDEX;
    private int rowCount;

    /**
     * @param result inbound result set
     * @throws SQLException if a database access error occurs
     */
    public CachedScrollableResult(ResultSet result) throws SQLException {
        ResultSetMetaData metaData = result.getMetaData();
        int columnCount = metaData.getColumnCount();

        cachedResultByIndex = new ArrayList<>(columnCount);
        columnLabelToIndexRegistry = new HashMap<>(columnCount + 1, 1.0F);
        for (int i = 1; i <= columnCount; i++) {
            cachedResultByIndex.add(new ArrayList<>(DEFAULT_ROWS_CAPACITY));
                    
            String columnLabel = metaData.getColumnName(i).toLowerCase();
            columnLabelToIndexRegistry.put(columnLabel, i - 1);
        }
        
        while (result.next()) {
            for (int i = 1; i <= columnCount; i++) {
                cachedResultByIndex.get(i - 1).add(result.getObject(i));
            }
            rowCount++;
        }
    }

    /**
     * Moves the cursor to the previous row and returns <tt>true</tt> if the cursor after the first row.
     *
     * @return <tt>true</tt> if the cursor after the first row
     */
    public boolean previous() {
        --cursorIndex;
        if (cursorIndex < BEFORE_FIRST_ROW_INDEX) {
            cursorIndex = BEFORE_FIRST_ROW_INDEX;
        }
        return cursorIndex > BEFORE_FIRST_ROW_INDEX;
    }

    /**
     * Moves the cursor to the next row and returns <tt>true</tt> if the cursor before the last row.
     *
     * @return <tt>true</tt> if the cursor before the last row
     */
    public boolean next() {
        ++cursorIndex;
        if (cursorIndex > rowCount) {
            cursorIndex = rowCount;
        }
        return cursorIndex < rowCount;
    }

    /**
     * Returns <tt>true</tt> if this result set contains no rows.
     *
     * @return <tt>true</tt> if this result set contains no rows
     */
    public boolean isEmpty() {
        return rowCount == 0;
    }

    /**
     * Returns <tt>true</tt> if the cursor is before the first row.
     *
     * @return <tt>true</tt> if the cursor is before the first row
     */
    public boolean isBeforeFirst() {
        return cursorIndex == BEFORE_FIRST_ROW_INDEX;
    }

    /**
     * Returns <tt>true</tt> if the cursor is after the last row.
     *
     * @return <tt>true</tt> if the cursor is after the last row
     */
    public boolean isAfterLast() {
        return cursorIndex == rowCount;
    }

    /**
     * Returns <tt>true</tt> if the cursor is on the first row.
     *
     * @return <tt>true</tt> if the cursor is on the first row
     */
    public boolean isFirst() {
        return cursorIndex == 0;
    }

    /**
     * Returns <tt>true</tt> if the cursor is on the last row.
     *
     * @return <tt>true</tt> if the cursor is on the last row
     */
    public boolean isLast() {
        return cursorIndex == rowCount - 1;
    }

    /**
     * Moves the cursor to the front of result set, just before the first row.
     */
    public void beforeFirst() {
        cursorIndex = BEFORE_FIRST_ROW_INDEX;
    }

    /**
     * Moves the cursor to the back of result set, just after the last row.
     */
    public void afterLast() {
        cursorIndex = rowCount;
    }

    /**
     * Moves the cursor to the first row and returns <tt>true</tt> if the cursor is on a valid row.
     *
     * @return <tt>true</tt> if the cursor is on a valid row
     */
    public boolean first() {
        cursorIndex = 0;
        return cursorIndex < rowCount;
    }

    /**
     * Moves the cursor to the last row and returns <tt>true</tt> if the cursor is on a valid row.
     *
     * @return <tt>true</tt> if the cursor is on a valid row
     */
    public boolean last() {
        cursorIndex = rowCount - 1;
        return cursorIndex < rowCount;
    }

    /**
     * Returns the current row number. The first row is number 1 and las row is number {@link #getRowCount()} - 1.
     *
     * @return the current row number
     */
    public int getRow() {
        return cursorIndex;
    }

    /**
     * Returns the number of rows in this result set.
     *
     * @return the number of rows in this result set
     */
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public boolean getBoolean(int columnIndex) {
        return (Boolean) getObject(columnIndex);
    }

    @Override
    public byte getByte(int columnIndex) {
        return (Byte) getObject(columnIndex);
    }

    @Override
    public short getShort(int columnIndex) {
        return (Short) getObject(columnIndex);
    }

    @Override
    public int getInt(int columnIndex) {
        return (Integer) getObject(columnIndex);
    }

    @Override
    public long getLong(int columnIndex) {
        return (Long) getObject(columnIndex);
    }

    @Override
    public float getFloat(int columnIndex) {
        return (Float) getObject(columnIndex);
    }

    @Override
    public double getDouble(int columnIndex) {
        return (Double) getObject(columnIndex);
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex) {
        return (BigDecimal) getObject(columnIndex);
    }

    @Override
    public String getString(int columnIndex) {
        return (String) getObject(columnIndex);
    }

    @Override
    public byte[] getBytes(final int columnIndex) {
        return (byte[]) getObject(columnIndex);
    }

    @Override
    public Date getDate(int columnIndex) {
        return (Date) getObject(columnIndex);
    }

    @Override
    public Time getTime(int columnIndex) {
        return (Time) getObject(columnIndex);
    }

    @Override
    public Timestamp getTimestamp(int columnIndex) {
        return (Timestamp) getObject(columnIndex);
    }

    @Override
    public Object getObject(int columnIndex) {
        return cachedResultByIndex.get(columnIndex - 1).get(cursorIndex);
    }

    @Override
    public Boolean getBoolean(String columnLabel) {
        return (Boolean) getObject(columnLabel);
    }

    @Override
    public Byte getByte(String columnLabel) {
        return (Byte) getObject(columnLabel);
    }

    @Override
    public Short getShort(String columnLabel) {
        return (Short) getObject(columnLabel);
    }

    @Override
    public Integer getInt(String columnLabel) {
        return (Integer) getObject(columnLabel);
    }

    @Override
    public Long getLong(String columnLabel) {
        return (Long) getObject(columnLabel);
    }

    @Override
    public Float getFloat(String columnLabel) {
        return (Float) getObject(columnLabel);
    }

    @Override
    public Double getDouble(String columnLabel) {
        return (Double) getObject(columnLabel);
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel) {
        return (BigDecimal) getObject(columnLabel);
    }

    @Override
    public String getString(String columnLabel) {
        return (String) getObject(columnLabel);
    }

    @Override
    public byte[] getBytes(final String columnLabel) {
        return (byte[]) getObject(columnLabel);
    }

    @Override
    public Date getDate(String columnLabel) {
        return (Date) getObject(columnLabel);
    }

    @Override
    public Time getTime(String columnLabel) {
        return (Time) getObject(columnLabel);
    }

    @Override
    public Timestamp getTimestamp(String columnLabel) {
        return (Timestamp) getObject(columnLabel);
    }

    @Override
    public Object getObject(String columnLabel) {
        Integer columnIndex = columnLabelToIndexRegistry.get(columnLabel.toLowerCase());
        if (columnIndex == null)
        {
            return null;
        }
        return getObject(columnIndex + 1);
    }
}
