package org.apache.skywalking.apm.plugin.lettuce.pt.v5;

import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import io.lettuce.core.cluster.api.reactive.RedisAdvancedClusterReactiveCommands;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.InstanceMethodsAroundInterceptor;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import org.apache.skywalking.apm.plugin.lettuce.pt.v5.model.RedisAdvancedClusterAsyncCommandsDelegate;
import org.apache.skywalking.apm.plugin.lettuce.pt.v5.model.RedisAdvancedClusterCommandsDelegate;
import org.apache.skywalking.apm.plugin.lettuce.pt.v5.model.RedisAdvancedClusterReactiveCommandsDelegate;
import org.apache.skywalking.apm.plugin.pt.commons.PressureTestContext;

import java.lang.reflect.Method;

/**
 * @author lijian
 * @since 2022/1/4
 */
public class ClusterConnPTInterceptor implements InstanceMethodsAroundInterceptor {

    @Override
    public void beforeMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, MethodInterceptResult result) throws Throwable {

    }

    @Override
    public Object afterMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, Object ret) throws Throwable {
        if (PressureTestContext.isTest()) {
            if (method.getName().equals("sync")) {
                   return new RedisAdvancedClusterCommandsDelegate((RedisAdvancedClusterCommands) ret);
            } else if (method.getName().equals("async")) {
                return new RedisAdvancedClusterAsyncCommandsDelegate((RedisAdvancedClusterAsyncCommands) ret);
            } else if (method.getName().equals("reactive")) {
                return new RedisAdvancedClusterReactiveCommandsDelegate((RedisAdvancedClusterReactiveCommands) ret);
            }
        }
        return ret;
    }

    @Override
    public void handleMethodException(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, Throwable t) {

    }
}
