package org.apache.skywalking.apm.plugin.cache.pt.commons;

import org.apache.skywalking.apm.plugin.pt.commons.enums.RedisConnMode;
import org.apache.skywalking.apm.plugin.pt.commons.exception.ConfigItemNotFoundException;
import org.apache.skywalking.apm.plugin.pt.commons.util.StrUtil;
import org.apache.skywalking.apm.util.StringUtil;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private volatile Set<RedisUrl> redisUrls;

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
                if (!strKey.endsWith(shadowKeySuffix)) {
                    if (key instanceof String) {
                        return (K) (strKey + shadowKeySuffix);
                    } else {
                        return (K) (strKey + shadowKeySuffix).getBytes(StandardCharsets.UTF_8);
                    }
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

    public Set<RedisUrl> getRedisUrls() {
        // if not init, do init
        initShadowRedisUrl();

        return redisUrls;
    }


    public static class RedisUrl {
        private final String host;
        private final Integer port;

        public RedisUrl(String host, Integer port) {
            this.host = host;
            this.port = port;
        }

        public String getHost() {
            return host;
        }

        public Integer getPort() {
            return port;
        }
    }


    // ~~~ inner methods

    private void initShadowRedisUrl() {
        if (redisUrls == null) {
            synchronized (this) {
                if (redisUrls == null) {
                    redisUrls = new HashSet<>();
                    if (StringUtil.isEmpty(shadowDBPUrl)) {
                        throw new ConfigItemNotFoundException("SHADOW_DB_URL not config");
                    }
                    String[] urls = shadowDBPUrl.split(",");
                    for (String url : urls) {
                        String[] split = url.split(":");
                        String host = split[0];
                        Integer port = split.length == 2 ? Integer.parseInt(split[1]) : 6379;
                        redisUrls.add(new RedisUrl(host, port));
                    }
                }
            }
        }
    }

    private void initList() {
        if (whiteKeyList == null) {
            synchronized (this) {
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

}
