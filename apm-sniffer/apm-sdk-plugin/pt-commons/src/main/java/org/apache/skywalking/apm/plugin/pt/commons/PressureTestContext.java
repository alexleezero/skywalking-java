package org.apache.skywalking.apm.plugin.pt.commons;

import org.apache.skywalking.apm.agent.core.context.ContextManager;
import org.apache.skywalking.apm.agent.core.context.CorrelationContext;

import java.util.Optional;

/**
 * Pressure test context
 * @author lijian
 * @since 2021/12/30
 */
public final class PressureTestContext {

    public static boolean isTest() {
        CorrelationContext correlationContext = ContextManager.getCorrelationContext();
        if (correlationContext != null) {
            Optional<String> ptIdentifier = correlationContext.get(Constants.PT_KEY);
            return ptIdentifier.isPresent() && Boolean.parseBoolean(ptIdentifier.get());
        }
        return false;
    }

}
