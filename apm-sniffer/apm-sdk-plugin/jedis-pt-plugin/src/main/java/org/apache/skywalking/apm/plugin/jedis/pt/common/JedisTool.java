package org.apache.skywalking.apm.plugin.jedis.pt.common;

import org.apache.skywalking.apm.agent.core.context.ContextManager;
import org.apache.skywalking.apm.agent.core.logging.api.ILog;
import org.apache.skywalking.apm.agent.core.logging.api.LogManager;
import org.apache.skywalking.apm.plugin.cache.pt.commons.RedisTool;
import org.apache.skywalking.apm.plugin.jedis.pt.MethodStrategy;
import org.apache.skywalking.apm.plugin.pt.commons.enums.RedisConnMode;
import org.apache.skywalking.apm.plugin.pt.commons.exception.ConfigItemNotFoundException;
import org.apache.skywalking.apm.util.StringUtil;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.skywalking.apm.plugin.jedis.pt.JedisPTPluginConfig.Plugin.JedisPT.CONN_MODE;
import static org.apache.skywalking.apm.plugin.jedis.pt.JedisPTPluginConfig.Plugin.JedisPT.SHADOW_DB_PASSWORD;
import static org.apache.skywalking.apm.plugin.jedis.pt.JedisPTPluginConfig.Plugin.JedisPT.SHADOW_DB_SENTINEL_MASTER_ID;
import static org.apache.skywalking.apm.plugin.jedis.pt.JedisPTPluginConfig.Plugin.JedisPT.SHADOW_DB_URL;

/**
 * @author lijian
 * @since 2022/1/6
 */
public final class JedisTool {
	private final static ILog LOGGER = LogManager.getLogger(JedisTool.class);

	private volatile static JedisPool SHADOW_JEDIS_POOL;
	private volatile static JedisSentinelPool SHADOW_JEDIS_SENTINEL_POOL;
	private volatile static JedisCluster SHADOW_JEDIS_CLUSTER;
	public final static String DIRECT_INVOKE_KEY = "DIRECT_INVOKE";

	public static JedisPool getShadowJedisPool() {
		if (SHADOW_JEDIS_POOL == null) {
			synchronized (JedisTool.class) {
				if (SHADOW_JEDIS_POOL == null) {
					Set<RedisTool.RedisUrl> redisUrls = RedisToolManager.JEDIS_TOOL.getRedisUrls();
					RedisTool.RedisUrl redisUrl = redisUrls.stream().findFirst().get();
					JedisPoolConfig config = new JedisPoolConfig();

					if (StringUtil.isEmpty(SHADOW_DB_PASSWORD)) {
						SHADOW_JEDIS_POOL = new JedisPool(redisUrl.getHost(), redisUrl.getPort());
					} else {
						SHADOW_JEDIS_POOL = new JedisPool(config, redisUrl.getHost(), redisUrl.getPort(), 5000, SHADOW_DB_PASSWORD);
					}
				}
			}
		}
		return SHADOW_JEDIS_POOL;
	}

	public static JedisSentinelPool getShadowJedisSentinelPool() {
		if (SHADOW_JEDIS_SENTINEL_POOL == null) {
			synchronized (JedisTool.class) {
				if (SHADOW_JEDIS_SENTINEL_POOL == null) {
					Set<RedisTool.RedisUrl> redisUrls = RedisToolManager.JEDIS_TOOL.getRedisUrls();
					Set<String> sentinels = new HashSet<>();
					for (RedisTool.RedisUrl redisUrl : redisUrls) {
						sentinels.add(redisUrl.getHost() + ":" + redisUrl.getPort());
					}
					if (StringUtil.isEmpty(SHADOW_DB_PASSWORD)) {
						SHADOW_JEDIS_SENTINEL_POOL = new JedisSentinelPool(SHADOW_DB_SENTINEL_MASTER_ID, sentinels);
					} else {
						SHADOW_JEDIS_SENTINEL_POOL = new JedisSentinelPool(SHADOW_DB_SENTINEL_MASTER_ID, sentinels, SHADOW_DB_PASSWORD);
					}
				}
			}
		}
		return SHADOW_JEDIS_SENTINEL_POOL;
	}

	public static JedisCluster getShadowJedisCluster() {
		if (SHADOW_JEDIS_CLUSTER == null) {
			synchronized (JedisTool.class) {
				if (SHADOW_JEDIS_CLUSTER == null) {
					Set<RedisTool.RedisUrl> redisUrls = RedisToolManager.JEDIS_TOOL.getRedisUrls();
					Set<HostAndPort> nodes = new HashSet<>();
					for (RedisTool.RedisUrl redisUrl : redisUrls) {
						nodes.add(new HostAndPort(redisUrl.getHost(), redisUrl.getPort()));
					}
					JedisPoolConfig config = new JedisPoolConfig();
					if (StringUtil.isEmpty(SHADOW_DB_PASSWORD)) {
						SHADOW_JEDIS_CLUSTER = new JedisCluster(nodes);
					} else {
						SHADOW_JEDIS_CLUSTER = new JedisCluster(nodes, 5000, 10000, 5, SHADOW_DB_PASSWORD, config);
					}
				}
			}
		}
		return SHADOW_JEDIS_CLUSTER;
	}

	public static void checkRedisConnConf() {
		if (StringUtil.isEmpty(SHADOW_DB_URL)) {
			throw new ConfigItemNotFoundException("SHADOW_DB_URL not config");
		}
	}

	/**
	 * find strategy from specific strategies by method name and argument type
	 * @param strategies method strategy enums
	 * @param methodName target method name
	 * @param argumentsTypes target argument types
	 * @return method strategy which is found
	 */
	public static MethodStrategy findStrategy(MethodStrategy[] strategies, String methodName, Class<?>[] argumentsTypes) {
		if (StringUtil.isEmpty(methodName) || argumentsTypes == null || argumentsTypes.length == 0) {
			return null;
		}
		out: for (MethodStrategy methodStrategy : strategies) {
			// argument types must be same as it's length and sequence
			if (argumentsTypes.length != methodStrategy.getArgumentsTypes().length) {
				continue;
			}
			for (int i = 0; i < argumentsTypes.length; i++) {
				if (argumentsTypes[i] != methodStrategy.getArgumentsTypes()[i]) {
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

	public static InvokeResp executeByShadowDB(Method method, Object[] allArguments, Class<?>[] argumentsTypes)
			throws IllegalAccessException, InvocationTargetException {
		InvokeResp invokeResp = InvokeResp.fail();
		JedisTool.checkRedisConnConf();
		if (CONN_MODE == RedisConnMode.STANDALONE) {
			JedisPool shadowJedisPool = JedisTool.getShadowJedisPool();
			try (Jedis jedis = shadowJedisPool.getResource()) {
				Method shadowMethod = jedis.getClass().getDeclaredMethod(method.getName(), argumentsTypes);
				shadowMethod.setAccessible(true);
				invokeResp = InvokeResp.ok(shadowMethod.invoke(jedis, allArguments));
			} catch (NoSuchMethodException e) {
				LOGGER.warn("jedis shadow db execute image method error [standalone mode]: " + e.getMessage());
			}
		} else if (CONN_MODE == RedisConnMode.SENTINEL) {
			JedisSentinelPool shadowJedisSentinelPool = JedisTool.getShadowJedisSentinelPool();
			try (Jedis jedis = shadowJedisSentinelPool.getResource()) {
				Method shadowMethod = jedis.getClass().getDeclaredMethod(method.getName(), argumentsTypes);
				shadowMethod.setAccessible(true);
				invokeResp = InvokeResp.ok(shadowMethod.invoke(jedis, allArguments));
			} catch (NoSuchMethodException e) {
				LOGGER.warn("jedis shadow db execute image method error [sentinel mode]: " + e.getMessage());
			}
		} else if (CONN_MODE == RedisConnMode.CLUSTER) {
			JedisCluster shadowJedisCluster = JedisTool.getShadowJedisCluster();
			try {
				Method shadowMethod = shadowJedisCluster.getClass().getDeclaredMethod(method.getName(), argumentsTypes);
				shadowMethod.setAccessible(true);
				invokeResp = InvokeResp.ok(shadowMethod.invoke(shadowJedisCluster, allArguments));
			} catch (NoSuchMethodException e) {
				LOGGER.warn("jedis shadow db execute image method error [cluster mode]: " + e.getMessage());
			}
		} else {
			LOGGER.error("jedis shadow db config item[conn mode] error: " + CONN_MODE);
		}
		return invokeResp;
	}

	public static void setDirectInvoke() {
		ContextManager.getRuntimeContext().put(DIRECT_INVOKE_KEY, Boolean.TRUE.toString());
	}

	public static void clearDirectInvoke() {
		ContextManager.getRuntimeContext().remove(DIRECT_INVOKE_KEY);
	}

	public static boolean isDirectInvoke() {
		return Boolean.TRUE.toString().equals(ContextManager.getRuntimeContext().get(DIRECT_INVOKE_KEY));
	}

	public static class InvokeResp {
		private final boolean success;
		private final Object response;

		public InvokeResp(boolean success, Object response) {
			this.success = success;
			this.response = response;
		}

		public boolean isSuccess() {
			return success;
		}

		public Object getResponse() {
			return response;
		}

		public static InvokeResp ok(Object response) {
			return new InvokeResp(true, response);
		}

		public static InvokeResp fail(Object response) {
			return new InvokeResp(false, response);
		}

		public static InvokeResp fail() {
			return fail(null);
		}
	}
}
