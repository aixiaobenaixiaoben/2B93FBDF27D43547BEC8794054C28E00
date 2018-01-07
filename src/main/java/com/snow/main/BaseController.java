package com.snow.main;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: TODO.
 * User: kevin
 * Date: 2018/1/1
 * Time: 下午3:03
 */
public class BaseController {

    protected final Logger logger = Logger.getLogger(BaseController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response handleException(Exception e) {

        String CODE;
        String MSG;

        if (e instanceof NotFoundException) {
            logger.error("NOT FOUND EXCEPTION", e);
            CODE = Response.NOT_FOUND_EXCEPTION_CODE;
            MSG = Response.NOT_FOUND_EXCEPTION_MSG;

        } else if (e instanceof UnauthorizedException) {
            logger.error("UNAUTHORIZED EXCEPTION", e);
            CODE = Response.UNAUTHORIZED_EXCEPTION_CODE;
            MSG = Response.UNAUTHORIZED_EXCEPTION_MSG;

        } else if (e instanceof DupException) {
            logger.error("DATABASE EXCEPTION", e);
            CODE = Response.DUPLICATE_OPERATION_EXCEPTION_CODE;
            MSG = Response.DUPLICATE_OPERATION_EXCEPTION_MSG;

        } else if (e instanceof ConException) {
            logger.error("SYSTEM EXCEPTION", e);
            CODE = Response.SYSTEM_EXCEPTION_CODE;
            String message = e.getMessage();
            MSG = (message == null || "".equals(message)) ? Response.SYSTEM_EXCEPTION_MSG : message;

        } else {
            logger.error("UNKNOWN EXCEPTION", e);
            CODE = Response.UNKNOWN_EXCEPTION_CODE;
            MSG = Response.UNKNOWN_EXCEPTION_MSG;
        }

        return Response.ERROR(CODE, MSG);
    }

    /**
     * 检查登陆注册方法(不经过spring拦截器)的请求方式
     * 如果不是post，转发请求到首页
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected boolean checkValidRequestMethod(HttpServletRequest request,
                                             HttpServletResponse response) throws ServletException, IOException {

        if (!SecurityInterceptor.POST.equalsIgnoreCase(request.getMethod())) {
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        }
        return true;
    }
}
