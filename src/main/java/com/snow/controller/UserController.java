package com.snow.controller;

import com.snow.Domain.Item;
import com.snow.main.BaseController;
import com.snow.main.ConException;
import com.snow.service.UserService;
import org.apache.log4j.Logger;
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

    private static Logger logger = Logger.getLogger(IndexController.class);

    @Resource
    UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public String login(Item item, HttpSession session, HttpServletRequest request) throws ConException {
        userService.login(item, session);
        logger.info("登陆用户ID:" + item.getId() + " 名称:" + item.getCity());
        return request.getContextPath();
    }

    @RequestMapping("logout")
    @ResponseBody
    public void logout(HttpSession session) {
        Item item = (Item) session.getAttribute(UserService.USER_KEY);
        logger.info("注销用户ID:" + item.getId() + " 名称:" + item.getCity());
        userService.logOut(session);
    }


}
