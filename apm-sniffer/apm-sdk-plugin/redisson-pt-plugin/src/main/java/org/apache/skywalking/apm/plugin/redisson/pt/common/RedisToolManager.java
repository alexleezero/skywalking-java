package org.apache.skywalking.apm.plugin.redisson.pt.common;

import org.apache.skywalking.apm.plugin.cache.pt.commons.RedisTool;
import static org.apache.skywalking.apm.plugin.redisson.pt.RedissonPTPluginConfig.Plugin.RedissonPT.*;

/**
 * @author lijian
 * @since 2022/1/11
 */
public final class RedisToolManager {
	public static final RedisTool REDISSON_TOOL = RedisTool.builder().withConnMode(CONN_MODE)
			.withShadowKeySuffix(SHADOW_KEY_SUFFIX)
			.withWhiteKeys(WHITE_LIST_KEYS)
			.withShadowDBUrl(SHADOW_DB_URL)
			.withShadowDBPassword(SHADOW_DB_PASSWORD)
			.withShadowDBSentinelMasterId(SHADOW_DB_SENTINEL_MASTER_ID).build();
}
