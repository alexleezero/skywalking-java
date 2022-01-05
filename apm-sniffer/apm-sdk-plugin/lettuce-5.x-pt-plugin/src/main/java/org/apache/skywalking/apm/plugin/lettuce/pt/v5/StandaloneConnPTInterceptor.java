package org.apache.skywalking.apm.plugin.lettuce.pt.v5;

import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.InstanceMethodsAroundInterceptor;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import org.apache.skywalking.apm.plugin.lettuce.pt.v5.model.RedisAsyncCommandsDelegate;
import org.apache.skywalking.apm.plugin.lettuce.pt.v5.model.RedisCommandsDelegate;
import org.apache.skywalking.apm.plugin.lettuce.pt.v5.model.RedisReactiveCommandsDelegate;
import org.apache.skywalking.apm.plugin.pt.commons.PressureTestContext;

import java.lang.reflect.Method;

/**
 * @author lijian
 * @since 2022/1/4
 */
public class StandaloneConnPTInterceptor implements InstanceMethodsAroundInterceptor {

    @Override
    public void beforeMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, MethodInterceptResult result) throws Throwable {

    }

    @Override
    public Object afterMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, Object ret) throws Throwable {
        if (PressureTestContext.isTest()) {
            if (method.getName().equals("sync")) {
                   return new RedisCommandsDelegate((RedisCommands) ret);
            } else if (method.getName().equals("async")) {
                return new RedisAsyncCommandsDelegate((RedisAsyncCommands) ret);
            } else if (method.getName().equals("reactive")) {
                return new RedisReactiveCommandsDelegate((RedisReactiveCommands) ret);
            }
        }
        return ret;
    }

    @Override
    public void handleMethodException(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, Throwable t) {

    }
}
