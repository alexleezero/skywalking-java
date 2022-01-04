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
package org.apache.skywalking.apm.plugin.db.pt.support;

import org.apache.skywalking.apm.plugin.db.pt.DatabasePTPluginConfig;
import org.apache.skywalking.apm.plugin.pt.commons.enums.ShadowMode;
import org.apache.skywalking.apm.plugin.pt.commons.exception.ConfigItemNotFoundException;
import org.apache.skywalking.apm.plugin.pt.commons.exception.ConnInitException;
import org.apache.skywalking.apm.util.StringUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author lijian
 * @since 2021/12/30
 */
public class ShadowConnInitializer {
    private volatile static Connection shadowConn;

    public static Connection getShadowConn() {
        if (DatabasePTPluginConfig.Plugin.DBPT.SHADOW_MODE == ShadowMode.TABLE) {
            return null;
        }
        if (shadowConn == null) {
            synchronized (ShadowConnInitializer.class) {
                if (shadowConn == null) {
                    checkConfig();
                    shadowConn = initConn();
                }
            }
        }
        return shadowConn;
    }

    private static void checkConfig() {
        if (StringUtil.isEmpty(DatabasePTPluginConfig.Plugin.DBPT.SHADOW_DB_URL)) {
            throw new ConfigItemNotFoundException("shadow conn's url not found");
        }
        if (StringUtil.isEmpty(DatabasePTPluginConfig.Plugin.DBPT.SHADOW_DB_USER_NAME)) {
            throw new ConfigItemNotFoundException("shadow conn's userName not found");
        }
        if (StringUtil.isEmpty(DatabasePTPluginConfig.Plugin.DBPT.SHADOW_DB_PASSWORD)) {
            throw new ConfigItemNotFoundException("shadow conn's password not found");
        }
    }

    private static Connection initConn() {
        Connection shadowConn;
        try {
            shadowConn = DriverManager.getConnection(
                    DatabasePTPluginConfig.Plugin.DBPT.SHADOW_DB_URL,
                    DatabasePTPluginConfig.Plugin.DBPT.SHADOW_DB_USER_NAME,
                    DatabasePTPluginConfig.Plugin.DBPT.SHADOW_DB_PASSWORD);
        } catch (SQLException e) {
            throw new ConnInitException(e.getMessage());
        }
        return shadowConn;
    }
}
