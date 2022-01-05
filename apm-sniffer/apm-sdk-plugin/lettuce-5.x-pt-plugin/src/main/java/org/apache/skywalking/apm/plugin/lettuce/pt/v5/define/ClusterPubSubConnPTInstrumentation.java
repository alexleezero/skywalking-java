package org.apache.skywalking.apm.plugin.lettuce.pt.v5.define;

import org.apache.skywalking.apm.agent.core.plugin.interceptor.ConstructorInterceptPoint;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.InstanceMethodsInterceptPoint;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.ClassInstanceMethodsEnhancePluginDefine;
import org.apache.skywalking.apm.agent.core.plugin.match.ClassMatch;
import org.apache.skywalking.apm.agent.core.plugin.match.HierarchyMatch;
import org.apache.skywalking.apm.plugin.lettuce.pt.v5.common.RedisConnInterceptorPoint;

/**
 * @author lijian
 * @since 2022/1/4
 */
public class ClusterPubSubConnPTInstrumentation extends ClassInstanceMethodsEnhancePluginDefine {
    private static final String ENHANCE_CLASS = "io.lettuce.core.cluster.pubsub.StatefulRedisClusterPubSubConnection";
    private static final String INTERCEPT_CLASS = "org.apache.skywalking.apm.plugin.lettuce.pt.v5.ClusterPubSubConnPTInterceptor";

    @Override
    protected ClassMatch enhanceClass() {
        return HierarchyMatch.byHierarchyMatch(ENHANCE_CLASS);
    }

    @Override
    public ConstructorInterceptPoint[] getConstructorsInterceptPoints() {
        return new ConstructorInterceptPoint[0];
    }

    @Override
    public InstanceMethodsInterceptPoint[] getInstanceMethodsInterceptPoints() {
        return new InstanceMethodsInterceptPoint[] {
                new RedisConnInterceptorPoint(INTERCEPT_CLASS, false)
        };
    }
}
