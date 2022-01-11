package org.apache.skywalking.apm.plugin.redisson.pt.define;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.StaticMethodsInterceptPoint;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.ClassStaticMethodsEnhancePluginDefine;
import org.apache.skywalking.apm.agent.core.plugin.match.ClassMatch;
import org.apache.skywalking.apm.agent.core.plugin.match.NameMatch;
import org.redisson.config.Config;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.takesArguments;

/**
 * @author lijian
 * @since 2022/1/11
 */
public class RedissonPTReactiveInstrumentation extends ClassStaticMethodsEnhancePluginDefine {
	private static final String ENHANCE_CLASS = "org.redisson.Redisson";
	private static final String INTERCEPT_CLASS = "org.apache.skywalking.apm.plugin.redisson.pt.RedissonPTReactiveInterceptor";


	@Override
	protected ClassMatch enhanceClass() {
		return NameMatch.byName(ENHANCE_CLASS);
	}

	@Override
	public StaticMethodsInterceptPoint[] getStaticMethodsInterceptPoints() {
		return new StaticMethodsInterceptPoint[] {
				new StaticMethodsInterceptPoint() {
					@Override
					public ElementMatcher<MethodDescription> getMethodsMatcher() {
						return named("createReactive").and(takesArguments(Config.class)).and(takesArguments(1));
					}

					@Override
					public String getMethodsInterceptor() {
						return INTERCEPT_CLASS;
					}

					@Override
					public boolean isOverrideArgs() {
						return false;
					}
				}
		};
	}
}
