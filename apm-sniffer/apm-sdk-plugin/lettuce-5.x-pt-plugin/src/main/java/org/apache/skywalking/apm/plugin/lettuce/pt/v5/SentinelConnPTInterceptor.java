package org.apache.skywalking.apm.plugin.lettuce.pt.v5;

import io.lettuce.core.sentinel.api.async.RedisSentinelAsyncCommands;
import io.lettuce.core.sentinel.api.reactive.RedisSentinelReactiveCommands;
import io.lettuce.core.sentinel.api.sync.RedisSentinelCommands;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.InstanceMethodsAroundInterceptor;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import org.apache.skywalking.apm.plugin.lettuce.pt.v5.model.RedisSentinelAsyncCommandsDelegate;
import org.apache.skywalking.apm.plugin.lettuce.pt.v5.model.RedisSentinelCommandsDelegate;
import org.apache.skywalking.apm.plugin.lettuce.pt.v5.model.RedisSentinelReactiveCommandsDelegate;
import org.apache.skywalking.apm.plugin.pt.commons.PressureTestContext;

import java.lang.reflect.Method;

/**
 * @author lijian
 * @since 2022/1/4
 */
public class SentinelConnPTInterceptor implements InstanceMethodsAroundInterceptor {

    @Override
    public void beforeMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, MethodInterceptResult result) throws Throwable {

    }

    @Override
    public Object afterMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, Object ret) throws Throwable {
        if (PressureTestContext.isTest()) {
            if (method.getName().equals("sync")) {
                   return new RedisSentinelCommandsDelegate((RedisSentinelCommands) ret);
            } else if (method.getName().equals("async")) {
                return new RedisSentinelAsyncCommandsDelegate((RedisSentinelAsyncCommands) ret);
            } else if (method.getName().equals("reactive")) {
                return new RedisSentinelReactiveCommandsDelegate((RedisSentinelReactiveCommands) ret);
            }
        }
        return ret;
    }

    @Override
    public void handleMethodException(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, Throwable t) {

    }
}
