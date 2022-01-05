package org.apache.skywalking.apm.plugin.lettuce.pt.v5.common;

import org.apache.skywalking.apm.plugin.cache.pt.commons.RedisTool;

import static org.apache.skywalking.apm.plugin.lettuce.pt.v5.LettucePTPluginConfig.Plugin.LettucePT.CONN_MODE;
import static org.apache.skywalking.apm.plugin.lettuce.pt.v5.LettucePTPluginConfig.Plugin.LettucePT.SHADOW_DB_PASSWORD;
import static org.apache.skywalking.apm.plugin.lettuce.pt.v5.LettucePTPluginConfig.Plugin.LettucePT.SHADOW_DB_SENTINEL_MASTER_ID;
import static org.apache.skywalking.apm.plugin.lettuce.pt.v5.LettucePTPluginConfig.Plugin.LettucePT.SHADOW_DB_URL;
import static org.apache.skywalking.apm.plugin.lettuce.pt.v5.LettucePTPluginConfig.Plugin.LettucePT.SHADOW_KEY_SUFFIX;
import static org.apache.skywalking.apm.plugin.lettuce.pt.v5.LettucePTPluginConfig.Plugin.LettucePT.WHITE_LIST_KEYS;

/**
 * @author lijian
 * @since 2022/1/4
 */
public final class RedisToolManager {

    public static final RedisTool LETTUCE_TOOL = RedisTool.builder()
            .withConnMode(CONN_MODE)
            .withWhiteKeys(WHITE_LIST_KEYS)
            .withShadowDBUrl(SHADOW_DB_URL)
            .withShadowDBPassword(SHADOW_DB_PASSWORD)
            .withShadowKeySuffix(SHADOW_KEY_SUFFIX)
            .withShadowDBSentinelMasterId(SHADOW_DB_SENTINEL_MASTER_ID).build();

}
