package com.snow.main;

import com.snow.model.sy.Syusrinf;
import com.snow.service.sy.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Description: TODO.
 * User: kevin
 * Date: 2018/1/1
 * Time: 下午12:05
 */
public class SessionListener implements HttpSessionListener {

    private static final Logger LOGGER = Logger.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        if (session != null) {
            LOGGER.info("session销毁:" + session.getId());

            Syusrinf syusrinf = (Syusrinf) session.getAttribute(UserService.USER_KEY);
            if (syusrinf != null) {
                //session销毁
                session.invalidate();
            }
        }
    }
}
