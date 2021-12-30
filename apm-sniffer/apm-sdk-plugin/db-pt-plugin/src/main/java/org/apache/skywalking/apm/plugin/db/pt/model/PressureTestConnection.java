/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.apache.skywalking.apm.plugin.db.pt.model;

import com.alibaba.druid.DbType;
import org.apache.skywalking.apm.plugin.db.pt.support.DBTool;
import org.apache.skywalking.apm.plugin.db.pt.support.SQLParser;
import org.apache.skywalking.apm.plugin.pt.commons.enums.ShadowMode;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * jdbc connection delegation
 * @author lijian
 * @since 2021/12/30
 */
public class PressureTestConnection implements Connection {
    private final Connection originalConn;
    private final Connection shadowConn;
    private final ShadowMode shadowMode;

    public PressureTestConnection(Connection originalConn, Connection shadowConn) {
        this.originalConn = originalConn;
        this.shadowConn = shadowConn;
        shadowMode = this.shadowConn != null ? ShadowMode.DB : ShadowMode.TABLE;
    }

    @Override
    public Statement createStatement() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.createStatement();
        } else {
            return new PressureTestStatement(originalConn.createStatement());
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.prepareStatement(sql);
        } else {
            return new PressureTestPreparedStatement(originalConn.prepareStatement(sql));
        }
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.prepareCall(sql);
        } else {
            return new PressureTestCallableStatement(originalConn.prepareCall(sql));
        }
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.nativeSQL(sql);
        } else {
            DbType dbType = DBTool.toDbType(this.getMetaData().getURL());
            if (dbType == null) {
                throw new SQLException("unknown db type in delegate connection");
            }
            return originalConn.nativeSQL(SQLParser.convert2Shadow(sql, dbType));
        }
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            shadowConn.setAutoCommit(autoCommit);
        } else {
            originalConn.setAutoCommit(autoCommit);
        }
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.getAutoCommit();
        } else {
            return originalConn.getAutoCommit();
        }
    }

    @Override
    public void commit() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            shadowConn.commit();
        } else {
            originalConn.commit();
        }
    }

    @Override
    public void rollback() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            shadowConn.rollback();
        } else {
            originalConn.rollback();
        }
    }

    @Override
    public void close() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            shadowConn.close();
        } else {
            originalConn.close();
        }
    }

    @Override
    public boolean isClosed() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.isClosed();
        } else {
            return originalConn.isClosed();
        }
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.getMetaData();
        } else {
            return originalConn.getMetaData();
        }
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            shadowConn.setReadOnly(readOnly);
        } else {
            originalConn.setReadOnly(readOnly);
        }
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.isReadOnly();
        } else {
            return originalConn.isReadOnly();
        }
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            shadowConn.setCatalog(catalog);
        } else {
            originalConn.setCatalog(catalog);
        }
    }

    @Override
    public String getCatalog() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.getCatalog();
        } else {
            return originalConn.getCatalog();
        }
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            shadowConn.setTransactionIsolation(level);
        } else {
            originalConn.setTransactionIsolation(level);
        }
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.getTransactionIsolation();
        } else {
            return originalConn.getTransactionIsolation();
        }
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.getWarnings();
        } else {
            return originalConn.getWarnings();
        }
    }

    @Override
    public void clearWarnings() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            shadowConn.clearWarnings();
        } else {
            originalConn.clearWarnings();
        }
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.createStatement(resultSetType, resultSetConcurrency);
        } else {
            return new PressureTestStatement(createStatement(resultSetType, resultSetConcurrency));
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.prepareStatement(sql, resultSetType, resultSetConcurrency);
        } else {
            return new PressureTestPreparedStatement(originalConn.prepareStatement(sql, resultSetType, resultSetConcurrency));
        }
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.prepareCall(sql, resultSetType, resultSetConcurrency);
        } else {
            return new PressureTestCallableStatement(originalConn.prepareCall(sql, resultSetType, resultSetConcurrency));
        }
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.getTypeMap();
        } else {
            return originalConn.getTypeMap();
        }
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            shadowConn.setTypeMap(map);
        } else {
            originalConn.setTypeMap(map);
        }
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            shadowConn.setHoldability(holdability);
        } else {
            originalConn.setHoldability(holdability);
        }
    }

    @Override
    public int getHoldability() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.getHoldability();
        } else {
            return originalConn.getHoldability();
        }
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.setSavepoint();
        } else {
            return originalConn.setSavepoint();
        }
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.setSavepoint(name);
        } else {
            return originalConn.setSavepoint(name);
        }
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            shadowConn.rollback(savepoint);
        } else {
            originalConn.rollback(savepoint);
        }
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            shadowConn.releaseSavepoint(savepoint);
        } else {
            originalConn.releaseSavepoint(savepoint);
        }
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        } else {
            return new PressureTestStatement(originalConn.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability));
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        } else {
            return new PressureTestPreparedStatement(originalConn.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
        }
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        } else {
            return new PressureTestCallableStatement(originalConn.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.prepareStatement(sql, autoGeneratedKeys);
        } else {
            return new PressureTestPreparedStatement(originalConn.prepareStatement(sql, autoGeneratedKeys));
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.prepareStatement(sql, columnIndexes);
        } else {
            return new PressureTestPreparedStatement(originalConn.prepareStatement(sql, columnIndexes));
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.prepareStatement(sql, columnNames);
        } else {
            return new PressureTestPreparedStatement(originalConn.prepareStatement(sql, columnNames));
        }
    }

    @Override
    public Clob createClob() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.createClob();
        } else {
            return originalConn.createClob();
        }
    }

    @Override
    public Blob createBlob() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.createBlob();
        } else {
            return originalConn.createBlob();
        }
    }

    @Override
    public NClob createNClob() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.createNClob();
        } else {
            return originalConn.createNClob();
        }
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.createSQLXML();
        } else {
            return originalConn.createSQLXML();
        }
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.isValid(timeout);
        } else {
            return originalConn.isValid(timeout);
        }
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        if (shadowMode == ShadowMode.DB) {
            shadowConn.setClientInfo(name, value);
        } else {
            originalConn.setClientInfo(name, value);
        }
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        if (shadowMode == ShadowMode.DB) {
            shadowConn.setClientInfo(properties);
        } else {
            originalConn.setClientInfo(properties);
        }
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.getClientInfo(name);
        } else {
            return originalConn.getClientInfo(name);
        }
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.getClientInfo();
        } else {
            return originalConn.getClientInfo();
        }
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.createArrayOf(typeName, elements);
        } else {
            return originalConn.createArrayOf(typeName, elements);
        }
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.createStruct(typeName, attributes);
        } else {
            return originalConn.createStruct(typeName, attributes);
        }
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            shadowConn.setSchema(schema);
        } else {
            originalConn.setSchema(schema);
        }
    }

    @Override
    public String getSchema() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.getSchema();
        } else {
            return originalConn.getSchema();
        }
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            shadowConn.abort(executor);
        } else {
            originalConn.abort(executor);
        }
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            shadowConn.setNetworkTimeout(executor, milliseconds);
        } else {
            originalConn.setNetworkTimeout(executor, milliseconds);
        }
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.getNetworkTimeout();
        } else {
            return originalConn.getNetworkTimeout();
        }
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.unwrap(iface);
        } else {
            return originalConn.unwrap(iface);
        }
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        if (shadowMode == ShadowMode.DB) {
            return shadowConn.isWrapperFor(iface);
        } else {
            return originalConn.isWrapperFor(iface);
        }
    }
}
