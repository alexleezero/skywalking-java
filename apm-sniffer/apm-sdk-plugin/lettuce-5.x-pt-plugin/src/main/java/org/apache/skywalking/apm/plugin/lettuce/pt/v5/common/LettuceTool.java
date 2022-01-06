package org.apache.skywalking.apm.plugin.lettuce.pt.v5.common;

import io.lettuce.core.RedisURI;
import org.apache.skywalking.apm.plugin.cache.pt.commons.RedisTool;
import org.apache.skywalking.apm.plugin.pt.commons.enums.RedisConnMode;
import org.apache.skywalking.apm.plugin.pt.commons.exception.ConfigItemErrorException;
import org.apache.skywalking.apm.plugin.pt.commons.exception.ConfigItemNotFoundException;
import org.apache.skywalking.apm.util.StringUtil;

import java.util.Set;

import static org.apache.skywalking.apm.plugin.lettuce.pt.v5.LettucePTPluginConfig.Plugin.LettucePT.*;

/**
 * @author lijian
 * @since 2022/1/6
 */
public final class LettuceTool {

	public static RedisURI createShadowRedisURI() {
		checkRedisConnConf();
		if (CONN_MODE == RedisConnMode.STANDALONE) {
			return buildStandaloneClient();
		} else if (CONN_MODE == RedisConnMode.SENTINEL) {
			return buildSentinelClient();
		} else if (CONN_MODE == RedisConnMode.CLUSTER) {
			return buildClusterClient();
		} else {
			throw new ConfigItemErrorException("redis shadow mode item config error, please check it!");
		}
	}

	private static RedisURI buildClusterClient() {
		Set<RedisTool.RedisUrl> redisUrls = RedisToolManager.LETTUCE_TOOL.getRedisUrls();
		RedisURI.Builder builder = RedisURI.builder().withPassword(SHADOW_DB_PASSWORD);
		for (RedisTool.RedisUrl redisUrl : redisUrls) {
			builder.withHost(redisUrl.getHost()).withPort(redisUrl.getPort());
		}
		return builder.build();
	}

	private static RedisURI buildSentinelClient() {
		RedisURI.Builder builder = RedisURI.builder();
		checkRedisSentinelConnConf();
		builder.withSentinelMasterId(SHADOW_DB_SENTINEL_MASTER_ID);
		builder.withPassword(SHADOW_DB_PASSWORD);
		Set<RedisTool.RedisUrl> redisUrls = RedisToolManager.LETTUCE_TOOL.getRedisUrls();
		for (RedisTool.RedisUrl redisUrl : redisUrls) {
			builder.withSentinel(redisUrl.getHost(), redisUrl.getPort());
		}
		return builder.build();
	}

	private static RedisURI buildStandaloneClient() {
		Set<RedisTool.RedisUrl> redisUrls = RedisToolManager.LETTUCE_TOOL.getRedisUrls();
		// must exists
		RedisTool.RedisUrl redisUrl = redisUrls.stream().findFirst().get();
		return RedisURI.builder().withPassword(SHADOW_DB_PASSWORD)
				.withHost(redisUrl.getHost()).withPort(redisUrl.getPort()).build();
	}

	private static void checkRedisConnConf() {
		if (StringUtil.isEmpty(SHADOW_DB_URL)) {
			throw new ConfigItemNotFoundException("SHADOW_DB_URL not config");
		}
	}

	private static void checkRedisSentinelConnConf() {
		if (StringUtil.isEmpty(SHADOW_DB_SENTINEL_MASTER_ID)) {
			throw new ConfigItemNotFoundException("redis sentinel mode: SHADOW_DB_SENTINEL_MASTER_ID not config");
		}
	}
}
