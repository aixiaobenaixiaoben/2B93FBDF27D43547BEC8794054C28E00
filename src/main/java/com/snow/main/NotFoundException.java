package com.snow.main;

/**
 * Description: request route not found.
 * User: kevin
 * Date: 2018/1/1
 * Time: 下午2:50
 */
public class NotFoundException extends ConException {

    public NotFoundException() {
    }

    public NotFoundException(String errorCode) {
        super(errorCode);
    }

    public NotFoundException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return Response.NOT_FOUND_EXCEPTION_CODE + ":" + Response.NOT_FOUND_EXCEPTION_MSG;
    }
}
