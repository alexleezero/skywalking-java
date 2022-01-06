package org.apache.skywalking.apm.plugin.jedis.pt;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;

/**
 * @author lijian
 * @since 2022/1/6
 */
public enum JedisMethodMatcher {
	INSTANCE;


	public ElementMatcher.Junction<MethodDescription> getJedisMethodMatcher() {
		return null;
	}

	public ElementMatcher.Junction<MethodDescription> getJedisSentinelMethodMatcher() {
		return null;
	}

	public ElementMatcher.Junction<MethodDescription> getJedisClusterMethodMatcher() {
		return null;
	}
}
