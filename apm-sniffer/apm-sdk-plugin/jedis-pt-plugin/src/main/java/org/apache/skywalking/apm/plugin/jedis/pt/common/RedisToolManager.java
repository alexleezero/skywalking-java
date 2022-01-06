package org.apache.skywalking.apm.plugin.jedis.pt.common;

import org.apache.skywalking.apm.plugin.cache.pt.commons.RedisTool;
import static org.apache.skywalking.apm.plugin.jedis.pt.JedisPTPluginConfig.Plugin.JedisPT.*;

/**
 * @author lijian
 * @since 2022/1/6
 */
public class RedisToolManager {
	public static final RedisTool JEDIS_TOOL = RedisTool.builder().withConnMode(CONN_MODE)
			.withShadowKeySuffix(SHADOW_KEY_SUFFIX)
			.withWhiteKeys(WHITE_LIST_KEYS)
			.withShadowDBUrl(SHADOW_DB_URL)
			.withShadowDBPassword(SHADOW_DB_PASSWORD)
			.withShadowDBSentinelMasterId(SHADOW_DB_SENTINEL_MASTER_ID).build();
}
