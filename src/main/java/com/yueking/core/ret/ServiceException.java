package com.yueking.core.ret;

import java.io.Serializable;

/**
 * @Description 业务异常
 * @author yueking
 */
public class ServiceException extends RuntimeException implements Serializable {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
