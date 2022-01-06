package org.apache.skywalking.apm.plugin.lettuce.pt.v5;

import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.InstanceMethodsAroundInterceptor;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import org.apache.skywalking.apm.plugin.lettuce.pt.v5.common.LettuceTool;
import org.apache.skywalking.apm.plugin.pt.commons.PressureTestContext;
import org.apache.skywalking.apm.plugin.pt.commons.enums.CacheShadowMode;

import java.lang.reflect.Method;

/**
 * @author lijian
 * @since 2022/1/4
 */
public class LettucePTShadowConnInterceptor implements InstanceMethodsAroundInterceptor {

    @Override
    public void beforeMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, MethodInterceptResult result) throws Throwable {
        if (PressureTestContext.isTest() &&
                LettucePTPluginConfig.Plugin.LettucePT.CACHE_SHADOW_MODE == CacheShadowMode.SHADOW_DB) {
            // replace original redis connection
            allArguments[1] = LettuceTool.createShadowRedisURI();
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
