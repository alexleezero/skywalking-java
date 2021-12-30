package org.apache.skywalking.apm.plugin.db.pt.model;

import com.alibaba.druid.DbType;
import org.apache.skywalking.apm.plugin.db.pt.support.DBTool;
import org.apache.skywalking.apm.plugin.db.pt.support.SQLParser;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * jdbc preparedStatement delegation
 * @author lijian
 * @since 2021/12/30
 */
public class PressureTestPreparedStatement implements PreparedStatement {
    private PreparedStatement origin;

    public PressureTestPreparedStatement(PreparedStatement preparedStatement) {
        this.origin = preparedStatement;
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        return origin.executeQuery();
    }

    @Override
    public int executeUpdate() throws SQLException {
        return origin.executeUpdate();
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        origin.setNull(parameterIndex, sqlType);
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        origin.setBoolean(parameterIndex, x);
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        origin.setByte(parameterIndex, x);
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        origin.setShort(parameterIndex, x);
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        origin.setInt(parameterIndex, x);
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        origin.setLong(parameterIndex, x);
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        origin.setFloat(parameterIndex, x);
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        origin.setDouble(parameterIndex, x);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        origin.setBigDecimal(parameterIndex, x);
    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        origin.setString(parameterIndex, x);
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        origin.setBytes(parameterIndex, x);
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        origin.setDate(parameterIndex, x);
    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        origin.setTime(parameterIndex, x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        origin.setTimestamp(parameterIndex, x);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        origin.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        origin.setUnicodeStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        origin.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void clearParameters() throws SQLException {
        origin.clearParameters();
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        origin.setObject(parameterIndex, x, targetSqlType);
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        origin.setObject(parameterIndex, x);
    }

    @Override
    public boolean execute() throws SQLException {
        return origin.execute();
    }

    @Override
    public void addBatch() throws SQLException {
        origin.addBatch();
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        origin.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        origin.setRef(parameterIndex, x);
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        origin.setBlob(parameterIndex, x);
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        origin.setClob(parameterIndex, x);
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        origin.setArray(parameterIndex, x);
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return origin.getMetaData();
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        origin.setDate(parameterIndex, x, cal);
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        origin.setTime(parameterIndex, x, cal);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        origin.setTimestamp(parameterIndex, x, cal);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        origin.setNull(parameterIndex, sqlType, typeName);
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        origin.setURL(parameterIndex, x);
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return origin.getParameterMetaData();
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        origin.setRowId(parameterIndex, x);
    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        origin.setNString(parameterIndex, value);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        origin.setNCharacterStream(parameterIndex, value, length);
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        origin.setNClob(parameterIndex, value);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        origin.setClob(parameterIndex, reader, length);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        origin.setBlob(parameterIndex, inputStream, length);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        origin.setNClob(parameterIndex, reader, length);
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        origin.setSQLXML(parameterIndex, xmlObject);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        origin.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        origin.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        origin.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        origin.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        origin.setAsciiStream(parameterIndex, x);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        origin.setBinaryStream(parameterIndex, x);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        origin.setCharacterStream(parameterIndex, reader);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        origin.setNCharacterStream(parameterIndex, value);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        origin.setClob(parameterIndex, reader);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        origin.setBlob(parameterIndex, inputStream);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        origin.setNClob(parameterIndex, reader);
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        DbType dbType = DBTool.toDbType(this.getConnection().getMetaData().getURL());
        if (dbType == null) {
            throw new SQLException("unknown db type");
        }
        return origin.executeQuery(SQLParser.convert2Shadow(sql, dbType));
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        DbType dbType = DBTool.toDbType(this.getConnection().getMetaData().getURL());
        if (dbType == null) {
            throw new SQLException("unknown db type");
        }
        return origin.executeUpdate(SQLParser.convert2Shadow(sql, dbType));
    }

    @Override
    public void close() throws SQLException {
        origin.close();
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return origin.getMaxFieldSize();
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        origin.setMaxFieldSize(max);
    }

    @Override
    public int getMaxRows() throws SQLException {
        return origin.getMaxRows();
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        origin.setMaxRows(max);
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        origin.setEscapeProcessing(enable);
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return origin.getQueryTimeout();
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        origin.setQueryTimeout(seconds);
    }

    @Override
    public void cancel() throws SQLException {
        origin.cancel();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return origin.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        origin.clearWarnings();
    }

    @Override
    public void setCursorName(String name) throws SQLException {
        origin.setCursorName(name);
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        DbType dbType = DBTool.toDbType(this.getConnection().getMetaData().getURL());
        if (dbType == null) {
            throw new SQLException("unknown db type");
        }
        return origin.execute(SQLParser.convert2Shadow(sql, dbType));
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return origin.getResultSet();
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return origin.getUpdateCount();
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return origin.getMoreResults();
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        origin.setFetchDirection(direction);
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return origin.getFetchDirection();
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        origin.setFetchDirection(rows);
    }

    @Override
    public int getFetchSize() throws SQLException {
        return origin.getFetchSize();
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return origin.getResultSetConcurrency();
    }

    @Override
    public int getResultSetType() throws SQLException {
        return origin.getResultSetType();
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        DbType dbType = DBTool.toDbType(this.getConnection().getMetaData().getURL());
        if (dbType == null) {
            throw new SQLException("unknown db type");
        }
        origin.addBatch(SQLParser.convert2Shadow(sql, dbType));
    }

    @Override
    public void clearBatch() throws SQLException {
        origin.clearBatch();
    }

    @Override
    public int[] executeBatch() throws SQLException {
        return origin.executeBatch();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return origin.getConnection();
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return origin.getMoreResults();
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return origin.getGeneratedKeys();
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        DbType dbType = DBTool.toDbType(this.getConnection().getMetaData().getURL());
        if (dbType == null) {
            throw new SQLException("unknown db type");
        }
        return origin.executeUpdate(SQLParser.convert2Shadow(sql, dbType), autoGeneratedKeys);
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        DbType dbType = DBTool.toDbType(this.getConnection().getMetaData().getURL());
        if (dbType == null) {
            throw new SQLException("unknown db type");
        }
        return origin.executeUpdate(SQLParser.convert2Shadow(sql, dbType), columnIndexes);
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        DbType dbType = DBTool.toDbType(this.getConnection().getMetaData().getURL());
        if (dbType == null) {
            throw new SQLException("unknown db type");
        }
        return origin.executeUpdate(SQLParser.convert2Shadow(sql, dbType), columnNames);
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        DbType dbType = DBTool.toDbType(this.getConnection().getMetaData().getURL());
        if (dbType == null) {
            throw new SQLException("unknown db type");
        }
        return origin.execute(SQLParser.convert2Shadow(sql, dbType), autoGeneratedKeys);
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        DbType dbType = DBTool.toDbType(this.getConnection().getMetaData().getURL());
        if (dbType == null) {
            throw new SQLException("unknown db type");
        }
        return origin.execute(SQLParser.convert2Shadow(sql, dbType), columnIndexes);
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        DbType dbType = DBTool.toDbType(this.getConnection().getMetaData().getURL());
        if (dbType == null) {
            throw new SQLException("unknown db type");
        }
        return origin.execute(SQLParser.convert2Shadow(sql, dbType), columnNames);
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return origin.getResultSetHoldability();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return origin.isClosed();
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        origin.setPoolable(poolable);
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return origin.isPoolable();
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        origin.closeOnCompletion();
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return origin.isCloseOnCompletion();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return origin.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return origin.isWrapperFor(iface);
    }
}
