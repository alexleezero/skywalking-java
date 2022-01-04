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
package org.apache.skywalking.apm.plugin.db.pt;

import org.apache.skywalking.apm.agent.core.boot.PluginConfig;
import org.apache.skywalking.apm.plugin.pt.commons.enums.ShadowMode;

/**
 * @author lijian
 * @since 2021/12/30
 */
public class DatabasePTPluginConfig {

    public static class Plugin {

        @PluginConfig(root = DatabasePTPluginConfig.class)
        public static class DBPT {
            public static ShadowMode shadowMode = ShadowMode.TABLE;
            public static String SHADOW_TABLE_SUFFIX = "_shadow";
            public static String SHADOW_DB_URL;
            public static String SHADOW_DB_USER_NAME;
            public static String SHADOW_DB_PASSWORD;
            public static String WHITE_LIST_TABLES;
        }
    }
}
