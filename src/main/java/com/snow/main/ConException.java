package com.snow.main;

import com.snow.util.StringUtils;

/**
 * Description: TODO.
 * User: kevin
 * Date: 2018/1/1
 * Time: 下午2:50
 */
public class ConException extends Exception {

    private String errorCode = null;
    private Throwable cause = null;

    public ConException() {
        super();
    }

    public ConException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public ConException(String errorCode, Throwable cause) {
        super();
        this.errorCode = errorCode;
        this.cause = cause;
    }

    public ConException(Throwable cause) {
        this.cause = cause;
    }

    public Throwable getCause() {
        return cause;
    }

    public String getMessage() {
        if (StringUtils.isEmpty(errorCode)) {
            return "SYSTEM ERROR";
        }
        return errorCode;
    }
}
