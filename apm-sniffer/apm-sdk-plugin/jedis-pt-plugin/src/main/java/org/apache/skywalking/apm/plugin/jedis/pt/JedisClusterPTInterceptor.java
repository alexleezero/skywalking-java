package org.apache.skywalking.apm.plugin.jedis.pt;

import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.InstanceMethodsAroundInterceptor;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import org.apache.skywalking.apm.plugin.jedis.pt.common.JedisTool;
import org.apache.skywalking.apm.plugin.pt.commons.PressureTestContext;
import org.apache.skywalking.apm.plugin.pt.commons.enums.CacheShadowMode;

import java.lang.reflect.Method;

import static org.apache.skywalking.apm.plugin.jedis.pt.JedisPTPluginConfig.Plugin.JedisPT.CACHE_SHADOW_MODE;

/**
 * @author lijian
 * @since 2022/1/7
 */
public class JedisClusterPTInterceptor implements InstanceMethodsAroundInterceptor {

	@Override
	public void beforeMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, MethodInterceptResult result) throws Throwable {
		if (PressureTestContext.isTest()) {
			if (CACHE_SHADOW_MODE == CacheShadowMode.SHADOW_DB) {
				if (!JedisTool.isDirectInvoke()) {
					JedisTool.setDirectInvoke();
					JedisTool.InvokeResp invokeResp = JedisTool.executeByShadowDB(method, allArguments, argumentsTypes);
					if (invokeResp.isSuccess()) {
						result.defineReturnValue(invokeResp.getResponse());
					}
				}
			} else if (CACHE_SHADOW_MODE == CacheShadowMode.SHADOW_KEY) {
				// judge the arguments, to convert the key to shadow key
				JedisClusterMethodStrategy strategy = JedisClusterMethodStrategy.getStrategy(method.getName(), argumentsTypes);
				if (strategy != null) {
					strategy.doArguments(allArguments);
				}
			}
		}
	}

	@Override
	public Object afterMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, Object ret) throws Throwable {
		JedisTool.clearDirectInvoke();
		return ret;
	}

	@Override
	public void handleMethodException(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, Throwable t) {
		JedisTool.clearDirectInvoke();
	}
}
