/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywalking.apm.plugin.servlet.pt;

import org.apache.skywalking.apm.agent.core.context.ContextManager;
import org.apache.skywalking.apm.agent.core.context.CorrelationContext;
import org.apache.skywalking.apm.agent.core.context.trace.AbstractSpan;
import org.apache.skywalking.apm.agent.core.context.trace.SpanLayer;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.InstanceMethodsAroundInterceptor;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import org.apache.skywalking.apm.network.trace.component.ComponentsDefine;
import org.apache.skywalking.apm.plugin.pt.commons.Constants;
import org.apache.skywalking.apm.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * Interceptor of pressure testing for servlet, if the header of http request has 'PRESSURE_TEST' key and header value is '1' or 'true',
 * it will be putting 'PRESSURE_TEST' identifier to {@link org.apache.skywalking.apm.network.logging.v3.TraceContext},
 * then this identifier will propagate through the trace context of distributed environment
 * @author lijian
 * @since 2021/12/30
 */
public class ServletPressureTestInterceptor implements InstanceMethodsAroundInterceptor {

    @Override
    public void beforeMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, MethodInterceptResult result) throws Throwable {
        HttpServletRequest req = (HttpServletRequest) allArguments[0];
        String ptHeaderValue = req.getHeader(Constants.PT_KEY);

        // if trace context already has the pt identifier, then ignore
        CorrelationContext correlationContext = ContextManager.getCorrelationContext();
        if (correlationContext != null) {
            Optional<String> ptIdentifier = correlationContext.get(Constants.PT_KEY);
            if (ptIdentifier.isPresent() && Boolean.parseBoolean(ptIdentifier.get())) {
                return;
            }
        }

        // if header value exists, create local span and set the identifier to trace context
        if (!StringUtil.isEmpty(ptHeaderValue)) {
            AbstractSpan localSpan = ContextManager.createLocalSpan(Constants.PT_SPAN_NAME);
            localSpan.setComponent(ComponentsDefine.JDK_HTTP);
            localSpan.setLayer(SpanLayer.HTTP);
            ContextManager.getCorrelationContext().put(Constants.PT_KEY, Boolean.TRUE.toString());
        }
    }

    @Override
    public Object afterMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, Object ret) throws Throwable {
        return ret;
    }

    @Override
    public void handleMethodException(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, Throwable t) {

    }
}
