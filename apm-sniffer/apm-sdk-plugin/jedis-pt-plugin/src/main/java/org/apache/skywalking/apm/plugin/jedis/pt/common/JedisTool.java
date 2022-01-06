package org.apache.skywalking.apm.plugin.jedis.pt.common;

import org.apache.skywalking.apm.plugin.cache.pt.commons.RedisTool;
import org.apache.skywalking.apm.plugin.jedis.pt.MethodStrategy;
import org.apache.skywalking.apm.plugin.pt.commons.exception.ConfigItemNotFoundException;
import org.apache.skywalking.apm.util.StringUtil;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.skywalking.apm.plugin.jedis.pt.JedisPTPluginConfig.Plugin.JedisPT.SHADOW_DB_PASSWORD;
import static org.apache.skywalking.apm.plugin.jedis.pt.JedisPTPluginConfig.Plugin.JedisPT.SHADOW_DB_SENTINEL_MASTER_ID;
import static org.apache.skywalking.apm.plugin.jedis.pt.JedisPTPluginConfig.Plugin.JedisPT.SHADOW_DB_URL;

/**
 * @author lijian
 * @since 2022/1/6
 */
public final class JedisTool {

	public static JedisPool getShadowJedisPool() {
		Set<RedisTool.RedisUrl> redisUrls = RedisToolManager.JEDIS_TOOL.getRedisUrls();
		RedisTool.RedisUrl redisUrl = redisUrls.stream().findFirst().get();
		JedisPoolConfig config = new JedisPoolConfig();
		return new JedisPool(config, redisUrl.getHost(), redisUrl.getPort(), 5000, SHADOW_DB_PASSWORD);
	}

	public static JedisSentinelPool getShadowJedisSentinelPool() {
		Set<RedisTool.RedisUrl> redisUrls = RedisToolManager.JEDIS_TOOL.getRedisUrls();
		Set<String> sentinels = new HashSet<>();
		for (RedisTool.RedisUrl redisUrl : redisUrls) {
			sentinels.add(redisUrl.getHost() + ":" + redisUrl.getPort());
		}
		return new JedisSentinelPool(SHADOW_DB_SENTINEL_MASTER_ID, sentinels, SHADOW_DB_PASSWORD);
	}

	public static JedisCluster getShadowJedisCluster() {
		Set<RedisTool.RedisUrl> redisUrls = RedisToolManager.JEDIS_TOOL.getRedisUrls();
		Set<HostAndPort> nodes = new HashSet<>();
		for (RedisTool.RedisUrl redisUrl : redisUrls) {
			nodes.add(new HostAndPort(redisUrl.getHost(), redisUrl.getPort()));
		}
		JedisPoolConfig config = new JedisPoolConfig();
		return new JedisCluster(nodes, 5000, 10000, 5, SHADOW_DB_PASSWORD, config);
	}

	public static void checkRedisConnConf() {
		if (StringUtil.isEmpty(SHADOW_DB_URL)) {
			throw new ConfigItemNotFoundException("SHADOW_DB_URL not config");
		}
	}

	public static MethodStrategy findStrategy(MethodStrategy[] strategies, String methodName, Class<?>[] argumentsTypes) {
		if (StringUtil.isEmpty(methodName) || argumentsTypes == null || argumentsTypes.length == 0) {
			return null;
		}
		out: for (MethodStrategy methodStrategy : strategies) {
			if (argumentsTypes.length != methodStrategy.getArgumentsTypes().length) {
				continue;
			}
			for (Class<?> argumentsType : argumentsTypes) {
				boolean typeExists = false;
				for (Class<?> selfType : methodStrategy.getArgumentsTypes()) {
					if (selfType == argumentsType) {
						typeExists = true;
						break;
					}
				}
				if (!typeExists) {
					continue out;
				}
			}
			if (methodStrategy.getMethodName().equals(methodName)) {
				return methodStrategy;
			}
		}
		return null;
	}

	public static String strategy2String(String methodName, Class<?>[] argumentsTypes) {
		String classString = Arrays.stream(argumentsTypes).map(Class::getSimpleName).collect(Collectors.joining("_"));
		return methodName + "_" + classString;
	}
}
