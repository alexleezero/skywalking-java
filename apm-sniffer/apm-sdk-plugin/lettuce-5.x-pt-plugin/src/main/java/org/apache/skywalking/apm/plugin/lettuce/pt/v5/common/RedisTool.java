package org.apache.skywalking.apm.plugin.lettuce.pt.v5.common;

import io.lettuce.core.RedisURI;
import org.apache.skywalking.apm.plugin.lettuce.pt.v5.LettucePTPluginConfig;
import org.apache.skywalking.apm.plugin.pt.commons.enums.RedisConnMode;
import org.apache.skywalking.apm.plugin.pt.commons.exception.ConfigItemErrorException;
import org.apache.skywalking.apm.plugin.pt.commons.exception.ConfigItemNotFoundException;
import org.apache.skywalking.apm.util.StringUtil;

/**
 * @author lijian
 * @since 2022/1/4
 */
public final class RedisTool {

    public static RedisURI createShadowRedisURI() {
        checkRedisConnConf();
        if (LettucePTPluginConfig.Plugin.LettucePT.CONN_MODE == RedisConnMode.STANDALONE) {
            return buildStandaloneClient();
        } else if (LettucePTPluginConfig.Plugin.LettucePT.CONN_MODE == RedisConnMode.SENTINEL) {
            return buildSentinelClient();
        } else if (LettucePTPluginConfig.Plugin.LettucePT.CONN_MODE == RedisConnMode.CLUSTER) {
            return buildClusterClient();
        } else {
            throw new ConfigItemErrorException("redis shadow mode item config error, please check it!");
        }
    }

    private static RedisURI buildClusterClient() {
        RedisURI.Builder builder = RedisURI.builder().withPassword(LettucePTPluginConfig.Plugin.LettucePT.SHADOW_DB_PASSWORD);
        String[] uris = LettucePTPluginConfig.Plugin.LettucePT.SHADOW_DB_URL.split(",");
        for (String uri : uris) {
            String[] split = uri.split(":");
            if (split.length != 2) {
                throw new ConfigItemErrorException("SHADOW_DB_URL config item is error, please check it!");
            }
            String host = split[0];
            int port = Integer.parseInt(split[1]);
            builder.withHost(host).withPort(port);
        }
        return builder.build();
    }

    private static RedisURI buildSentinelClient() {
        RedisURI.Builder builder = RedisURI.builder();
        checkRedisSentinelConnConf();
        builder.withSentinelMasterId(LettucePTPluginConfig.Plugin.LettucePT.SHADOW_DB_SENTINEL_MASTER_ID);
        String[] uris = LettucePTPluginConfig.Plugin.LettucePT.SHADOW_DB_URL.split(",");
        for (String uri : uris) {
            String[] split = uri.split(":");
            if (split.length != 2) {
                throw new ConfigItemErrorException("SHADOW_DB_URL config item is error, please check it!");
            }
            String host = split[0];
            int port = Integer.parseInt(split[1]);
            builder.withSentinel(host, port);
        }
        builder.withPassword(LettucePTPluginConfig.Plugin.LettucePT.SHADOW_DB_PASSWORD);
        return builder.build();
    }

    private static RedisURI buildStandaloneClient() {
        Object[] shadowDBConnInfo = getRedisHostAndPort();
        String host = (String) shadowDBConnInfo[0];
        Integer port = (Integer) shadowDBConnInfo[1];
        return RedisURI.builder().withPassword(LettucePTPluginConfig.Plugin.LettucePT.SHADOW_DB_PASSWORD)
                .withHost(host).withPort(port).build();
    }

    private static void checkRedisConnConf() {
        if (StringUtil.isEmpty(LettucePTPluginConfig.Plugin.LettucePT.SHADOW_DB_URL)) {
            throw new ConfigItemNotFoundException("SHADOW_DB_URL not config");
        }
    }

    private static void checkRedisSentinelConnConf() {
        if (StringUtil.isEmpty(LettucePTPluginConfig.Plugin.LettucePT.SHADOW_DB_SENTINEL_MASTER_ID)) {
            throw new ConfigItemNotFoundException("redis sentinel mode: SHADOW_DB_SENTINEL_MASTER_ID not config");
        }
    }

    private static Object[] getRedisHostAndPort() {
        Object[] hostAndPort = new Object[2];
        if (LettucePTPluginConfig.Plugin.LettucePT.SHADOW_DB_URL.contains(":")) {
            String[] split = LettucePTPluginConfig.Plugin.LettucePT.SHADOW_DB_URL.split(":");
            hostAndPort[0] = split[0];
            hostAndPort[1] = split[1];
        } else {
            hostAndPort[0] = LettucePTPluginConfig.Plugin.LettucePT.SHADOW_DB_URL;
            // default redis port
            hostAndPort[1] = 6379;
        }
        return hostAndPort;
    }

}
