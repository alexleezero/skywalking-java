package org.apache.skywalking.apm.plugin.pt.commons.exception;

/**
 * @author lijian
 * @since 2022/1/4
 */
public class ConfigItemErrorException extends RuntimeException {

    public ConfigItemErrorException(String message) {
        super(message);
    }
}
