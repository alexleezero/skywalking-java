package org.apache.skywalking.apm.plugin.jedis.pt;

/**
 * @author lijian
 * @since 2022/1/6
 */
public interface MethodStrategy {

	String getMethodName();

	Class<?>[] getArgumentsTypes();

	MethodStrategy[] strategies();
}
