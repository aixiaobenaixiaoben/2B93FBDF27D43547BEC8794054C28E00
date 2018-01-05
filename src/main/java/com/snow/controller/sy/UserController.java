package com.snow.controller.sy;

import com.snow.main.BaseController;
import com.snow.main.ConException;
import com.snow.model.sy.Syusrinf;
import com.snow.service.sy.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Description: TODO.
 * User: kevin
 * Date: 2018/1/1
 * Time: 下午12:37
 */
@RequestMapping("/user")
@Controller
public class UserController extends BaseController {

    @Resource
    UserService userService;

    @RequestMapping("register")
    @ResponseBody
    public void register(Syusrinf syusrinf) {
        userService.register(syusrinf);
    }

    @RequestMapping("login")
    @ResponseBody
    public String login(Syusrinf syusrinf, HttpSession session, HttpServletRequest request) throws ConException {
        userService.login(syusrinf, session);
        return request.getContextPath();
    }

    @RequestMapping("logout")
    @ResponseBody
    public void logout(HttpSession session) {
        userService.logOut(session);
    }


}
