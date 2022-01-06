package org.apache.skywalking.apm.plugin.jedis.pt;

import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.InstanceMethodsAroundInterceptor;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import org.apache.skywalking.apm.plugin.jedis.pt.common.JedisTool;
import org.apache.skywalking.apm.plugin.pt.commons.PressureTestContext;
import org.apache.skywalking.apm.plugin.pt.commons.enums.CacheShadowMode;
import org.apache.skywalking.apm.plugin.pt.commons.enums.RedisConnMode;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.Method;

import static org.apache.skywalking.apm.plugin.jedis.pt.JedisPTPluginConfig.Plugin.JedisPT.CACHE_SHADOW_MODE;
import static org.apache.skywalking.apm.plugin.jedis.pt.JedisPTPluginConfig.Plugin.JedisPT.CONN_MODE;

/**
 * @author lijian
 * @since 2022/1/6
 */
public class JedisPTInterceptor implements InstanceMethodsAroundInterceptor {

	@Override
	public void beforeMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, MethodInterceptResult result) throws Throwable {
		if (PressureTestContext.isTest()) {
			if (CACHE_SHADOW_MODE == CacheShadowMode.SHADOW_DB) {
				JedisTool.checkRedisConnConf();
				if (CONN_MODE == RedisConnMode.STANDALONE) {
					JedisPool shadowJedisPool = JedisTool.getShadowJedisPool();
					try (Jedis jedis = shadowJedisPool.getResource()) {
						// todo reflect the same method and set the result
					}
				} else if (CONN_MODE == RedisConnMode.SENTINEL) {

				} else if (CONN_MODE == RedisConnMode.CLUSTER) {

				}
			} else if (CACHE_SHADOW_MODE == CacheShadowMode.SHADOW_KEY) {
				// judge the arguments, to convert the key to shadow key
				JedisMethodStrategy strategy = JedisMethodStrategy.getStrategy(method.getName(), argumentsTypes);
				if (strategy != null) {
					strategy.doArguments(allArguments);
				}
			}
		}
	}

	@Override
	public Object afterMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, Object ret) throws Throwable {
		return ret;
	}

	@Override
	public void handleMethodException(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, Throwable t) {

	}
}
