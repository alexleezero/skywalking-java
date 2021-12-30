package org.apache.skywalking.apm.plugin.pt.commons.exception;

/**
 * @author lijian
 * @since 2021/12/30
 */
public class ConnInitException extends RuntimeException {

    public ConnInitException(String message) {
        super(message);
    }
}
