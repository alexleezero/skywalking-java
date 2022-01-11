package org.apache.skywalking.apm.plugin.redisson.pt;

import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.StaticMethodsAroundInterceptor;
import org.apache.skywalking.apm.plugin.cache.pt.commons.RedisTool;
import org.apache.skywalking.apm.plugin.pt.commons.PressureTestContext;
import org.apache.skywalking.apm.plugin.pt.commons.enums.CacheShadowMode;
import org.apache.skywalking.apm.plugin.pt.commons.enums.RedisConnMode;
import org.apache.skywalking.apm.plugin.redisson.pt.common.RedisToolManager;
import org.apache.skywalking.apm.util.StringUtil;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;

import java.lang.reflect.Method;

import static org.apache.skywalking.apm.plugin.redisson.pt.RedissonPTPluginConfig.Plugin.RedissonPT.CACHE_SHADOW_MODE;
import static org.apache.skywalking.apm.plugin.redisson.pt.RedissonPTPluginConfig.Plugin.RedissonPT.CONN_MODE;
import static org.apache.skywalking.apm.plugin.redisson.pt.RedissonPTPluginConfig.Plugin.RedissonPT.SHADOW_DB_PASSWORD;
import static org.apache.skywalking.apm.plugin.redisson.pt.RedissonPTPluginConfig.Plugin.RedissonPT.SHADOW_DB_SENTINEL_MASTER_ID;

/**
 * @author lijian
 * @since 2022/1/11
 */
public class RedissonPTConfigInterceptor implements StaticMethodsAroundInterceptor {

	private static final String REDIS_PROTOCOL = "redis://";

	@Override
	public void beforeMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, MethodInterceptResult result) {
		if (PressureTestContext.isTest()) {
			if (CACHE_SHADOW_MODE == CacheShadowMode.SHADOW_DB) {
				//must exists
				Config shadowConfig = new Config();
				if (CONN_MODE == RedisConnMode.STANDALONE) {
					RedisTool.RedisUrl redisUrl = RedisToolManager.REDISSON_TOOL.getRedisUrls().stream().findAny().get();
					String address = REDIS_PROTOCOL + redisUrl.getHost() + ":" + redisUrl.getPort();
					SingleServerConfig singleServerConfig = shadowConfig.useSingleServer().setAddress(address);
					if (!StringUtil.isEmpty(SHADOW_DB_PASSWORD)) {
						singleServerConfig.setPassword(SHADOW_DB_PASSWORD);
					}
				} else if (CONN_MODE == RedisConnMode.SENTINEL) {
					SentinelServersConfig sentinelServersConfig = shadowConfig.useSentinelServers();
					sentinelServersConfig.setMasterName(SHADOW_DB_SENTINEL_MASTER_ID);
					RedisToolManager.REDISSON_TOOL.getRedisUrls().forEach(url -> {
						String address = REDIS_PROTOCOL + url.getHost() + ":" + url.getPort();
						sentinelServersConfig.addSentinelAddress(address);
					});
					if (!StringUtil.isEmpty(SHADOW_DB_PASSWORD)) {
						sentinelServersConfig.setPassword(SHADOW_DB_PASSWORD);
					}
				} else if (CONN_MODE == RedisConnMode.CLUSTER) {
					ClusterServersConfig clusterServersConfig = shadowConfig.useClusterServers();
					if (!StringUtil.isEmpty(SHADOW_DB_PASSWORD)) {
						clusterServersConfig.setPassword(SHADOW_DB_PASSWORD);
					}
					RedisToolManager.REDISSON_TOOL.getRedisUrls().forEach(url -> {
						String address = REDIS_PROTOCOL + url.getHost() + ":" + url.getPort();
						clusterServersConfig.addNodeAddress(address);
					});
				}
				allArguments[0] = shadowConfig;
			}
		}
	}

	@Override
	public Object afterMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret) {
		return ret;
	}

	@Override
	public void handleMethodException(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t) {

	}
}
