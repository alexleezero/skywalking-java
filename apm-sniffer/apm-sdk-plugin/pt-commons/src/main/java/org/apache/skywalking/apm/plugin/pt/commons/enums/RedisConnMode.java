package org.apache.skywalking.apm.plugin.pt.commons.enums;

import org.apache.skywalking.apm.util.StringUtil;

/**
 * @author lijian
 * @since 2022/1/4
 */
public enum RedisConnMode {
    STANDALONE("STANDALONE"), SENTINEL("SENTINEL"), CLUSTER("CLUSTER");

    private String value;

    RedisConnMode(String value) {
        this.value = value;
    }

    public RedisConnMode getMode(String value) {
        if (StringUtil.isEmpty(value)) {
            return null;
        }

        for (RedisConnMode redisConnMode : values()) {
            if (redisConnMode.value.equals(value)) {
                return redisConnMode;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
