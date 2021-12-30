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
package org.apache.skywalking.apm.plugin.db.pt;

import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.StaticMethodsAroundInterceptor;
import org.apache.skywalking.apm.plugin.db.pt.model.PressureTestConnection;
import org.apache.skywalking.apm.plugin.db.pt.support.ShadowConnInitializer;
import org.apache.skywalking.apm.plugin.pt.commons.PressureTestContext;

import java.lang.reflect.Method;
import java.sql.Connection;

/**
 * deal with pressure test request, db will route the request to 'shadow table' or 'shadow database'
 * @author lijian
 * @since 2021/12/30
 */
public class DatabasePTInterceptor implements StaticMethodsAroundInterceptor {

    @Override
    public void beforeMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, MethodInterceptResult result) {

    }

    @Override
    public Object afterMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret) {
        // if test context exists, return delegate database connection
        if (PressureTestContext.isTest()) {
            Connection originalConn = (Connection) ret;
            Connection shadowConn = ShadowConnInitializer.getShadowConn();
            return new PressureTestConnection(originalConn, shadowConn);
        }
        return ret;
    }

    @Override
    public void handleMethodException(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t) {

    }
}
