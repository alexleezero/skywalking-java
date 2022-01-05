package org.apache.skywalking.apm.plugin.db.pt.support;

import com.alibaba.druid.DbType;
import org.apache.skywalking.apm.plugin.pt.commons.util.StrUtil;
import org.apache.skywalking.apm.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import static org.apache.skywalking.apm.plugin.db.pt.DatabasePTPluginConfig.Plugin.DBPT.WHITE_LIST_TABLES;

/**
 * @author lijian
 * @since 2021/12/30
 */
public final class DBTool {

    private static volatile List<String> TABLE_WHITE_LIST;

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

        // if list not init, do init
        initList();

        for (String tNameWildcard : TABLE_WHITE_LIST) {
            boolean matches = StrUtil.wildcardMatch(tNameWildcard, tableName);
            if (matches) {
                return true;
            }
        }
        return false;
    }

    private static void initList() {
        if (TABLE_WHITE_LIST == null) {
            synchronized (DBTool.class) {
                if (TABLE_WHITE_LIST == null) {
                    TABLE_WHITE_LIST = new ArrayList<>();
                    if (!StringUtil.isEmpty(WHITE_LIST_TABLES)) {
                        for (String tName : WHITE_LIST_TABLES.split(",")) {
                            TABLE_WHITE_LIST.add(tName.toLowerCase());
                        }
                    }
                }
            }
        }
    }

}
