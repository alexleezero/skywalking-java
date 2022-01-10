package org.apache.skywalking.apm.plugin.jedis.pt;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @author lijian
 * @since 2022/1/6
 */
public enum JedisMethodMatcher {
	INSTANCE;


	public ElementMatcher.Junction<MethodDescription> getJedisMethodMatcher() {
		return named("set").or(named("lpush"));
	}

	public ElementMatcher.Junction<MethodDescription> getJedisClusterMethodMatcher() {
		return named("set").or(named("lpush"));
	}
}
