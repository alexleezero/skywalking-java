package org.apache.skywalking.apm.plugin.pt.commons.enums;

/**
 * @author lijian
 * @since 2022/1/4
 */
public enum CacheShadowMode {
    SHADOW_DB("SHADOW_DB"), SHADOW_KEY("SHADOW_KEY");

    private final String value;

    CacheShadowMode(String value) {
        this.value = value;
    }

    public CacheShadowMode getMode(String value) {
        if (value == null) {
            return null;
        }
        for (CacheShadowMode cacheShadowMode : values()) {
            if (cacheShadowMode.value.equals(value)) {
                return cacheShadowMode;
            }
        }
        return null;
    }

}
