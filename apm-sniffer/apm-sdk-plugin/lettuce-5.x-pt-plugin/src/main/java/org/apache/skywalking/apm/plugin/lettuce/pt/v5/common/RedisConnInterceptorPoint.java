package org.apache.skywalking.apm.plugin.lettuce.pt.v5.common;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.InstanceMethodsInterceptPoint;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @author lijian
 * @since 2022/1/4
 */
public class RedisConnInterceptorPoint implements InstanceMethodsInterceptPoint {

    private final String interceptClass;
    private final boolean isOverrideArgs;

    public RedisConnInterceptorPoint(String interceptClass, boolean isOverrideArgs) {
        this.interceptClass = interceptClass;
        this.isOverrideArgs = isOverrideArgs;
    }

    @Override
    public ElementMatcher<MethodDescription> getMethodsMatcher() {
        return named("sync").or(named("async")).or(named("reactive"));
    }

    @Override
    public String getMethodsInterceptor() {
        return interceptClass;
    }

    @Override
    public boolean isOverrideArgs() {
        return isOverrideArgs;
    }
}
