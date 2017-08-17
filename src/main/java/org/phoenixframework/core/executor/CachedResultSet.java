package org.phoenixframework.core.executor;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Oleg Marchenko
 * @see org.phoenixframework.core.executor.ReadOnlyResultSet
 */

public class CachedResultSet implements ReadOnlyResultSet {
    private static final int DEFAULT_ROWS_CAPACITY = 64;
    private static final int BEFORE_FIRST_ROW_INDEX = -1;

    private final Map<String, List<Object>> cachedResultByLabel;
    private int cursorIndex = BEFORE_FIRST_ROW_INDEX;
    private int rowCount;

    public CachedResultSet(ResultSet result) throws SQLException {
        ResultSetMetaData metaData = result.getMetaData();
        int columnCount = metaData.getColumnCount() + 1;

        cachedResultByLabel = new HashMap<>(columnCount, 1.0F);
        for (int i = 1; i < columnCount; i++) {
            String columnLabel = metaData.getColumnName(i).toLowerCase();
            cachedResultByLabel.put(columnLabel, new ArrayList<>(DEFAULT_ROWS_CAPACITY));
        }

        while (result.next()) {
            for (int i = 1; i < columnCount; i++) {
                String columnLabel = metaData.getColumnName(i).toLowerCase();
                cachedResultByLabel.get(columnLabel).add(result.getObject(i));
            }
            rowCount++;
        }
    }

    public boolean previous() {
        --cursorIndex;
        if (cursorIndex < BEFORE_FIRST_ROW_INDEX) {
            cursorIndex = BEFORE_FIRST_ROW_INDEX;
        }
        return cursorIndex > BEFORE_FIRST_ROW_INDEX;
    }

    public boolean next() {
        ++cursorIndex;
        if (cursorIndex > rowCount) {
            cursorIndex = rowCount;
        }
        return cursorIndex < rowCount;
    }

    public boolean isEmpty() {
        return rowCount == 0;
    }

    public boolean isBeforeFirst() {
        return cursorIndex == BEFORE_FIRST_ROW_INDEX;
    }

    public boolean isAfterLast() {
        return cursorIndex == rowCount;
    }

    public boolean isFirst() {
        return cursorIndex == 0;
    }

    public boolean isLast() {
        return cursorIndex == rowCount - 1;
    }

    public void beforeFirst() {
        cursorIndex = BEFORE_FIRST_ROW_INDEX;
    }

    public void afterLast() {
        cursorIndex = rowCount;
    }

    public boolean first() {
        cursorIndex = 0;
        return cursorIndex < rowCount;
    }

    public boolean last() {
        cursorIndex = rowCount - 1;
        return cursorIndex < rowCount;
    }

    public int getRow() {
        return cursorIndex;
    }

    public int getRowCount() {
        return rowCount;
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
    public String getString(String columnLabel) {
        return (String) getObject(columnLabel);
    }

    @Override
    public Object getObject(String columnLabel) {
        List<Object> columnRows = cachedResultByLabel.get(columnLabel.toLowerCase());
        return columnRows == null ? null : columnRows.get(cursorIndex);
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
    public Date getDate(String columnLabel) {
        return (Date) getObject(columnLabel);
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel) {
        return (BigDecimal) getObject(columnLabel);
    }
}
