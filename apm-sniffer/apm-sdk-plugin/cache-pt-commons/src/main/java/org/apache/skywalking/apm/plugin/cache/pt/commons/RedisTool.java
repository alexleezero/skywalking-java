package org.apache.skywalking.apm.plugin.cache.pt.commons;

import io.lettuce.core.RedisURI;
import org.apache.skywalking.apm.plugin.pt.commons.enums.RedisConnMode;
import org.apache.skywalking.apm.plugin.pt.commons.exception.ConfigItemErrorException;
import org.apache.skywalking.apm.plugin.pt.commons.exception.ConfigItemNotFoundException;
import org.apache.skywalking.apm.plugin.pt.commons.util.StrUtil;
import org.apache.skywalking.apm.util.StringUtil;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lijian
 * @since 2022/1/4
 */
public final class RedisTool {

    private RedisConnMode redisConnMode;
    private String shadowKeySuffix;
    private String whiteKeys;
    private String shadowDBSentinelMasterId;
    private String shadowDBPUrl;
    private String shadowDBPassword;
    private volatile List<String> whiteKeyList;

    private RedisTool() {}

    public static class RedisToolBuilder {
        private final RedisTool redisTool;

        public RedisToolBuilder(RedisTool redisTool) {
            this.redisTool = redisTool;
        }

        public RedisToolBuilder withConnMode(RedisConnMode redisConnMode) {
            redisTool.redisConnMode = redisConnMode;
            return this;
        }

        public RedisToolBuilder withShadowKeySuffix(String shadowKeySuffix) {
            redisTool.shadowKeySuffix = shadowKeySuffix;
            return this;
        }

        public RedisToolBuilder withWhiteKeys(String whiteKeys) {
            redisTool.whiteKeys = whiteKeys;
            return this;
        }

        public RedisToolBuilder withShadowDBSentinelMasterId(String shadowSentinelMasterId) {
            redisTool.shadowDBSentinelMasterId = shadowSentinelMasterId;
            return this;
        }

        public RedisToolBuilder withShadowDBUrl(String shadowDBUrl) {
            redisTool.shadowDBPUrl = shadowDBUrl;
            return this;
        }

        public RedisToolBuilder withShadowDBPassword(String shadowDBPassword) {
            redisTool.shadowDBPassword = shadowDBPassword;
            return this;
        }

        public RedisTool build() {
            return this.redisTool;
        }
    }

    public static RedisToolBuilder builder() {
        return new RedisToolBuilder(new RedisTool());
    }

    public RedisURI createShadowRedisURI() {
        checkRedisConnConf();
        if (redisConnMode == RedisConnMode.STANDALONE) {
            return buildStandaloneClient();
        } else if (redisConnMode == RedisConnMode.SENTINEL) {
            return buildSentinelClient();
        } else if (redisConnMode == RedisConnMode.CLUSTER) {
            return buildClusterClient();
        } else {
            throw new ConfigItemErrorException("redis shadow mode item config error, please check it!");
        }
    }

    public <K> K convertShadowKey(K key) {
        if (key instanceof String || key instanceof byte[]) {
            String strKey;
            if (key instanceof String) {
                strKey = (String) key;
            } else {
                strKey = new String((byte[]) key, StandardCharsets.UTF_8);
            }
            boolean exists = whiteKeyExists(strKey);
            if (exists) {
                if (key instanceof String) {
                    return (K) (strKey + shadowKeySuffix);
                } else {
                    return (K) (strKey + shadowKeySuffix).getBytes(StandardCharsets.UTF_8);
                }
            }
        }
        return key;
    }

     public <K> K[] convertShadowKeys(K... keys) {
        List<K> newKeys = new ArrayList<>();
        for (K key : keys) {
            newKeys.add(convertShadowKey(key));
        }
        return (K[]) newKeys.toArray();
    }

    /**
     * judge the key exists in the white list
     * @param key name of the key
     * @return exists in the table's while list
     */
    public boolean whiteKeyExists(String key) {
        if (StringUtil.isEmpty(key)) {
            return false;
        }

        // if list not init, do init
        initList();

        for (String keyWildcard : whiteKeyList) {
            boolean matches = StrUtil.wildcardMatch(keyWildcard, key);
            if (matches) {
                return true;
            }
        }
        return false;
    }

    private void initList() {
        if (whiteKeyList == null) {
            synchronized (RedisTool.class) {
                if (whiteKeyList == null) {
                    whiteKeyList = new ArrayList<>();
                    if (!StringUtil.isEmpty(whiteKeys)) {
                        for (String keyWildcard : whiteKeys.split(",")) {
                            whiteKeyList.add(keyWildcard.toLowerCase());
                        }
                    }
                }
            }
        }
    }


    // ~~~ inner methods

    private RedisURI buildClusterClient() {
        RedisURI.Builder builder = RedisURI.builder().withPassword(shadowDBPassword);
        String[] uris = shadowDBPUrl.split(",");
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

    private RedisURI buildSentinelClient() {
        RedisURI.Builder builder = RedisURI.builder();
        checkRedisSentinelConnConf();
        builder.withSentinelMasterId(shadowDBSentinelMasterId);
        String[] uris = shadowDBPUrl.split(",");
        for (String uri : uris) {
            String[] split = uri.split(":");
            if (split.length != 2) {
                throw new ConfigItemErrorException("SHADOW_DB_URL config item is error, please check it!");
            }
            String host = split[0];
            int port = Integer.parseInt(split[1]);
            builder.withSentinel(host, port);
        }
        builder.withPassword(shadowDBPassword);
        return builder.build();
    }

    private RedisURI buildStandaloneClient() {
        Object[] shadowDBConnInfo = getRedisHostAndPort();
        String host = (String) shadowDBConnInfo[0];
        Integer port = (Integer) shadowDBConnInfo[1];
        return RedisURI.builder().withPassword(shadowDBPassword)
                .withHost(host).withPort(port).build();
    }

    private void checkRedisConnConf() {
        if (StringUtil.isEmpty(shadowDBPUrl)) {
            throw new ConfigItemNotFoundException("SHADOW_DB_URL not config");
        }
    }

    private void checkRedisSentinelConnConf() {
        if (StringUtil.isEmpty(shadowDBSentinelMasterId)) {
            throw new ConfigItemNotFoundException("redis sentinel mode: SHADOW_DB_SENTINEL_MASTER_ID not config");
        }
    }

    private Object[] getRedisHostAndPort() {
        Object[] hostAndPort = new Object[2];
        if (shadowDBPUrl.contains(":")) {
            String[] split = shadowDBPUrl.split(":");
            hostAndPort[0] = split[0];
            hostAndPort[1] = split[1];
        } else {
            hostAndPort[0] = shadowDBPUrl;
            // default redis port
            hostAndPort[1] = 6379;
        }
        return hostAndPort;
    }

}
