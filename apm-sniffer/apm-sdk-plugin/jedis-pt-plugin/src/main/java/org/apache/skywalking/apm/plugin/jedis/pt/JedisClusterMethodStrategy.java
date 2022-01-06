package org.apache.skywalking.apm.plugin.jedis.pt;

import org.apache.skywalking.apm.plugin.jedis.pt.common.JedisTool;

/**
 * @author lijian
 * @since 2022/1/6
 */
public enum JedisClusterMethodStrategy implements MethodStrategy {

	SET("", new Class[]{}) {
		@Override
		public void doArguments(Object[] allArguments) {

		}
	};

	private final String methodName;
	private final Class<?>[] argumentsTypes;

	JedisClusterMethodStrategy(String methodName, Class<?>[] argumentsTypes) {
		this.methodName = methodName;
		this.argumentsTypes = argumentsTypes;
	}

	public abstract void doArguments(Object[] allArguments);

	public static JedisClusterMethodStrategy getStrategy(String methodName, Class<?>[] argumentsTypes) {
		return (JedisClusterMethodStrategy) JedisTool.findStrategy(values(), methodName, argumentsTypes);
	}



	@Override
	public String toString() {
		return JedisTool.strategy2String(methodName, argumentsTypes);
	}

	@Override
	public String getMethodName() {
		return this.methodName;
	}

	@Override
	public Class<?>[] getArgumentsTypes() {
		return this.argumentsTypes;
	}

	@Override
	public MethodStrategy[] strategies() {
		return values();
	}
}
