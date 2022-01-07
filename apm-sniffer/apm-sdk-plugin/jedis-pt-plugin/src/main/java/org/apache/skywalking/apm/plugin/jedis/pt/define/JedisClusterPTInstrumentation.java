package org.apache.skywalking.apm.plugin.jedis.pt.define;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.ConstructorInterceptPoint;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.InstanceMethodsInterceptPoint;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.ClassInstanceMethodsEnhancePluginDefine;
import org.apache.skywalking.apm.agent.core.plugin.match.ClassMatch;
import org.apache.skywalking.apm.agent.core.plugin.match.NameMatch;
import org.apache.skywalking.apm.plugin.jedis.pt.JedisMethodMatcher;

/**
 * @author lijian
 * @since 2022/1/6
 */
public class JedisClusterPTInstrumentation extends ClassInstanceMethodsEnhancePluginDefine {
	private final static String ENHANCE_CLASS = "redis.clients.jedis.JedisCluster";
	private static final String INTERCEPT_CLASS = "org.apache.skywalking.apm.plugin.jedis.pt.JedisClusterPTInterceptor";

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
						return JedisMethodMatcher.INSTANCE.getJedisClusterMethodMatcher();
					}

					@Override
					public String getMethodsInterceptor() {
						return INTERCEPT_CLASS;
					}

					@Override
					public boolean isOverrideArgs() {
						return true;
					}
				}
		};
	}
}
