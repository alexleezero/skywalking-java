package org.apache.skywalking.apm.plugin.redisson.pt;

import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.StaticMethodsAroundInterceptor;
import org.apache.skywalking.apm.plugin.pt.commons.PressureTestContext;
import org.apache.skywalking.apm.plugin.pt.commons.enums.CacheShadowMode;
import org.apache.skywalking.apm.plugin.redisson.pt.model.ShadowRedissonRxClient;
import org.redisson.api.RedissonRxClient;

import java.lang.reflect.Method;

import static org.apache.skywalking.apm.plugin.redisson.pt.RedissonPTPluginConfig.Plugin.RedissonPT.CACHE_SHADOW_MODE;

/**
 * @author lijian
 * @since 2022/1/11
 */
public class RedissonPTRxInterceptor implements StaticMethodsAroundInterceptor {

	@Override
	public void beforeMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, MethodInterceptResult result) {

	}

	@Override
	public Object afterMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret) {
		if (PressureTestContext.isTest()) {
			if (CACHE_SHADOW_MODE == CacheShadowMode.SHADOW_KEY) {
				return new ShadowRedissonRxClient((RedissonRxClient) ret);
			}
		}
		return ret;
	}

	@Override
	public void handleMethodException(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t) {

	}
}
