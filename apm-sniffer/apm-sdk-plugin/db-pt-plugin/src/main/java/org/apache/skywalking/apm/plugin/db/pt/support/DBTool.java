package org.apache.skywalking.apm.plugin.db.pt.support;

import com.alibaba.druid.DbType;
import org.apache.skywalking.apm.util.StringUtil;

/**
 * @author lijian
 * @since 2021/12/30
 */
public final class DBTool {

    /**
     * get dbType from jdbc url
     * @param url jdbc url
     * @return druid dbType
     */
    public static DbType toDbType(String url) {
        if (StringUtil.isEmpty(url)) {
            return null;
        }

        if (url.toLowerCase().contains("mysql")) {
            return DbType.mysql;
        } else if (url.toLowerCase().contains("sqlserver")) {
            return DbType.sqlserver;
        } else if (url.toLowerCase().contains("oracle")) {
            return DbType.oracle;
        } else if (url.toLowerCase().contains("sybase")) {
            return DbType.sybase;
        } else if (url.toLowerCase().contains("h2")) {
            return DbType.h2;
        } else if (url.toLowerCase().contains("db2")) {
            return DbType.db2;
        } else if (url.toLowerCase().contains("hsql")) {
            return DbType.hsql;
        } else if (url.toLowerCase().contains("postgresql")) {
            return DbType.postgresql;
        } else if (url.toLowerCase().contains("sqlite")) {
            return DbType.sqlite;
        } else {
            return null;
        }
    }

}
