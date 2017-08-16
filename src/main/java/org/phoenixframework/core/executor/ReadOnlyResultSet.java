package org.phoenixframework.core.executor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author Oleg Marchenko
 */

public interface ReadOnlyResultSet {

    Boolean getBoolean(String columnLabel);

    boolean getBoolean(int columnIndex);

    Byte getByte(String columnLabel);

    byte getByte(int columnIndex);

    Short getShort(String columnLabel);

    short getShort(int columnIndex);

    Integer getInt(String columnLabel);

    int getInt(int columnIndex);

    Long getLong(String columnLabel);

    long getLong(int columnIndex);

    Float getFloat(String columnLabel);

    float getFloat(int columnIndex);

    Double getDouble(String columnLabel);

    double getDouble(int columnIndex);

    String getString(String columnLabel);

    String getString(int columnIndex);

    Object getObject(String columnLabel);

    Object getObject(int columnIndex);

    Time getTime(String columnLabel);

    Time getTime(int columnIndex);

    Timestamp getTimestamp(String columnLabel);

    Timestamp getTimestamp(int columnIndex);

    Date getDate(String columnLabel);

    Date getDate(int columnIndex);

    BigDecimal getBigDecimal(String columnLabel);

    BigDecimal getBigDecimal(int columnIndex);
}
