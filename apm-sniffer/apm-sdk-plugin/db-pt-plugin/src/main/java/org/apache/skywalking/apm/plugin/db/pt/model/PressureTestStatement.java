package org.apache.skywalking.apm.plugin.db.pt.model;

import com.alibaba.druid.DbType;
import org.apache.skywalking.apm.plugin.db.pt.support.DBTool;
import org.apache.skywalking.apm.plugin.db.pt.support.SQLParser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

/**
 * jdbc Statement delegation
 * @author lijian
 * @since 2021/12/30
 */
public class PressureTestStatement implements Statement {
    private final Statement origin;

    public PressureTestStatement(Statement statement) {
        this.origin = statement;
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
        origin.setFetchSize(rows);
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
        return origin.getMoreResults(current);
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
