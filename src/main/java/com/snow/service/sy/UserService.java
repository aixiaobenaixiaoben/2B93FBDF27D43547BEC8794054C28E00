package com.snow.service.sy;

import com.snow.main.BaseService;
import com.snow.model.sy.Syusrinf;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Description: TODO.
 * User: kevin
 * Date: 2018/1/1
 * Time: 下午12:38
 */
@Service
public class UserService extends BaseService {

    public static final String USER_KEY = "USER_KEY";

    public void register(Syusrinf syusrinf) {
        logger.info("注册用户名：" + syusrinf.getSuiusrnam() + " 手机号：" + syusrinf.getSuimobile());
    }

    public void login(Syusrinf syusrinf, HttpSession session) {
        session.setAttribute(USER_KEY, syusrinf);

        logger.info("登陆用户名:" + syusrinf.getSuiusrnam());
    }

    public void logOut(HttpSession session) {
        Syusrinf syusrinf = (Syusrinf) session.getAttribute(UserService.USER_KEY);
        logger.info("注销用户名:" + syusrinf.getSuiusrnam() + " sessionID:" + session.getId());

        session.removeAttribute(USER_KEY);
    }
}
