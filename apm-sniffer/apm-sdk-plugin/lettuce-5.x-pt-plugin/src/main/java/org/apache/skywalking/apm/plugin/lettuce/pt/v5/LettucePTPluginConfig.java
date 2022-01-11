package org.apache.skywalking.apm.plugin.lettuce.pt.v5;

import org.apache.skywalking.apm.agent.core.boot.PluginConfig;
import org.apache.skywalking.apm.plugin.pt.commons.enums.CacheShadowMode;
import org.apache.skywalking.apm.plugin.pt.commons.enums.RedisConnMode;

/**
 * @author lijian
 * @since 2022/1/4
 */
public class LettucePTPluginConfig {

    public static class Plugin {
        @PluginConfig(root = LettucePTPluginConfig.class)
        public static class LettucePT {
            public static CacheShadowMode CACHE_SHADOW_MODE = CacheShadowMode.SHADOW_KEY;
            public static RedisConnMode CONN_MODE = RedisConnMode.STANDALONE;
            public static String WHITE_LIST_KEYS;
            public static String SHADOW_KEY_SUFFIX = "_shadow";
            public static String SHADOW_DB_URL;
            public static String SHADOW_DB_SENTINEL_MASTER_ID;
            public static String SHADOW_DB_PASSWORD;
        }
    }
}