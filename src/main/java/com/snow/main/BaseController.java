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

    protected final Logger logger = Logger.getLogger(BaseController.class);

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request) {

        if (e instanceof DupException) {
            logger.error("DATABASE EXCEPTION", e);
            request.setAttribute("MSG", e.getMessage());
        } else if (e instanceof ConException) {
            logger.error("SYSTEM EXCEPTION", e);
            request.setAttribute("MSG", e.getMessage());
        } else {
            logger.error("UNKNOWN EXCEPTION", e);
            request.setAttribute("MSG", "UNKNOWN EXCEPTION");
        }
        return "main/exception";
    }
}
