package org.apache.skywalking.apm.plugin.db.pt.support;

import com.alibaba.druid.DbType;
import org.apache.skywalking.apm.plugin.db.pt.DatabasePTPluginConfig;
import org.apache.skywalking.apm.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijian
 * @since 2021/12/30
 */
public final class DBTool {

    private static volatile Map<String, String> WHITE_LIST_TABLE_MAP;

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

    /**
     * judge the table exists in the table's white list
     * @param tableName name of the table
     * @return exists in the table's while list
     */
    public static boolean whiteListExists(String tableName) {
        if (StringUtil.isEmpty(tableName)) {
            return false;
        }

        // if map not init, do init
        if (WHITE_LIST_TABLE_MAP == null) {
            synchronized (DBTool.class) {
                if (WHITE_LIST_TABLE_MAP == null) {
                    WHITE_LIST_TABLE_MAP = new HashMap<>();
                    if (!StringUtil.isEmpty(DatabasePTPluginConfig.Plugin.DBPT.WHITE_LIST_TABLES)) {
                        for (String tName : DatabasePTPluginConfig.Plugin.DBPT.WHITE_LIST_TABLES.split(",")) {
                            WHITE_LIST_TABLE_MAP.put(tName.toLowerCase(), tName.toLowerCase());
                        }
                    }
                }
            }
        }
        return WHITE_LIST_TABLE_MAP.containsKey(tableName.toLowerCase());
    }

}
