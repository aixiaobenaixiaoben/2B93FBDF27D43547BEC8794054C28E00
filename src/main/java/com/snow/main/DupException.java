package com.snow.main;

/**
 * Description: duplicate operation to db.
 * User: kevin
 * Date: 2018/1/1
 * Time: 下午2:50
 */
public class DupException extends ConException {
    public DupException() {
        super();
    }

    public DupException(String errorCode) {
        super(errorCode);
    }

    public DupException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public DupException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return Response.DUPLICATE_OPERATION_EXCEPTION_CODE + ":" + Response.DUPLICATE_OPERATION_EXCEPTION_MSG;
    }
}
