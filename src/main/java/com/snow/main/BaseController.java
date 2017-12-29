package com.snow.main;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: TODO.
 * User: kevin
 * Date: 2018/1/1
 * Time: 下午3:03
 */
public class BaseController {

    private static Logger logger = Logger.getLogger(BaseController.class);

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request) {

        if (e instanceof ConException) {
            logger.error("SYSTEM EXCEPTION", e);
        } else {
            logger.error("UNKNOWN EXCEPTION", e);
        }
        request.setAttribute("MSG", e.getMessage());
        return "main/exception";
    }
}
