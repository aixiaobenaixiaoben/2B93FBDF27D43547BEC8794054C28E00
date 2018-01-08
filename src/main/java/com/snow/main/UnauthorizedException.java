package com.snow.main;

/**
 * Description: Unauthorized request.
 * User: kevin
 * Date: 2018/1/1
 * Time: 下午2:50
 */
public class UnauthorizedException extends ConException {
    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String errorCode) {
        super(errorCode);
    }

    public UnauthorizedException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return Response.UNAUTHORIZED_EXCEPTION_CODE + ":" + Response.UNAUTHORIZED_EXCEPTION_MSG;
    }
}
