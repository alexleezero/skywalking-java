package org.apache.skywalking.apm.plugin.lettuce.pt.v5.define;

import io.lettuce.core.RedisURI;
import io.lettuce.core.codec.RedisCodec;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.ConstructorInterceptPoint;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.InstanceMethodsInterceptPoint;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.ClassInstanceMethodsEnhancePluginDefine;
import org.apache.skywalking.apm.agent.core.plugin.match.ClassMatch;
import org.apache.skywalking.apm.agent.core.plugin.match.NameMatch;

import java.time.Duration;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.takesArguments;

/**
 * @author lijian
 * @since 2022/1/4
 */
public class LettucePTInstrumentation extends ClassInstanceMethodsEnhancePluginDefine {

    private static final String ENHANCE_CLASS = "io.lettuce.core.RedisClient";
    private static final String SHADOW_CONN_INTERCEPT_CLASS = "org.apache.skywalking.apm.plugin.lettuce.pt.v5.LettucePTShadowConnInterceptor";

    @Override
    protected ClassMatch enhanceClass() {
        return NameMatch.byName(ENHANCE_CLASS);
    }

    @Override
    public ConstructorInterceptPoint[] getConstructorsInterceptPoints() {
        return new ConstructorInterceptPoint[0];
    }

    @Override
    public InstanceMethodsInterceptPoint[] getInstanceMethodsInterceptPoints() {
        return new InstanceMethodsInterceptPoint[] {
                new InstanceMethodsInterceptPoint() {
                    @Override
                    public ElementMatcher<MethodDescription> getMethodsMatcher() {
                        return named("connectStandaloneAsync")
                                .and(takesArguments(3)
                                        .and(takesArguments(RedisCodec.class, RedisURI.class, Duration.class)));
                    }

                    @Override
                    public String getMethodsInterceptor() {
                        return SHADOW_CONN_INTERCEPT_CLASS;
                    }

                    @Override
                    public boolean isOverrideArgs() {
                        return true;
                    }
                },
                new InstanceMethodsInterceptPoint() {
                    @Override
                    public ElementMatcher<MethodDescription> getMethodsMatcher() {
                        return named("connectPubSubAsync")
                                .and(takesArguments(3)
                                        .and(takesArguments(RedisCodec.class, RedisURI.class, Duration.class)));
                    }

                    @Override
                    public String getMethodsInterceptor() {
                        return SHADOW_CONN_INTERCEPT_CLASS;
                    }

                    @Override
                    public boolean isOverrideArgs() {
                        return true;
                    }
                },
                new InstanceMethodsInterceptPoint() {
                    @Override
                    public ElementMatcher<MethodDescription> getMethodsMatcher() {
                        return named("connectSentinelAsync")
                                .and(takesArguments(3)
                                        .and(takesArguments(RedisCodec.class, RedisURI.class, Duration.class)));
                    }

                    @Override
                    public String getMethodsInterceptor() {
                        return SHADOW_CONN_INTERCEPT_CLASS;
                    }

                    @Override
                    public boolean isOverrideArgs() {
                        return true;
                    }
                }
        };
    }
}
