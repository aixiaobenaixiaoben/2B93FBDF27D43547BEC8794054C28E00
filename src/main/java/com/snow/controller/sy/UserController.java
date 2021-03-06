package com.snow.controller.sy;

import com.snow.main.BaseController;
import com.snow.main.ConException;
import com.snow.main.Response;
import com.snow.model.sy.Syusrinf;
import com.snow.model.sy.Syvrymbl;
import com.snow.service.sy.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
    public Response register(Syusrinf syusrinf,
                             HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException, ConException {

        /** post requests permitted only */
        if (!checkValidRequestMethod(request, response)) {
            return null;
        }
        userService.register(syusrinf);
        return Response.SUCCESS();
    }

    @RequestMapping("login")
    @ResponseBody
    public Response login(Syusrinf syusrinf,
                        HttpSession session,
                        HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException, ConException {

        /**post requests permitted only */
        if (!checkValidRequestMethod(request, response)) {
            return null;
        }
        return Response.SUCCESS(userService.login(syusrinf, session));
    }

    @RequestMapping("logout")
    @ResponseBody
    public Response logout(HttpSession session) {
        userService.logOut(session);
        return Response.SUCCESS();
    }

    @RequestMapping("userInfoById")
    @ResponseBody
    public Response userInfoById(Syusrinf syusrinf) throws ConException {
        return Response.SUCCESS(userService.getUserInfoById(syusrinf));
    }

    @RequestMapping("updateUserInfo")
    @ResponseBody
    public Response updateUserInfo(Syusrinf syusrinf) throws ConException {
        userService.updateUserInfo(syusrinf);
        return Response.SUCCESS();
    }

    /**
     * TODO
     */
    @RequestMapping("userList")
    @ResponseBody
    public Response userList() {
        return Response.SUCCESS(userService.getUserList());
    }

    @RequestMapping("sendRegisterVerifyCode")
    @ResponseBody
    public Response sendRegisterVerifyCode(Syusrinf syusrinf,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws ConException, ServletException, IOException {
        /**post requests permitted only */
        if (!checkValidRequestMethod(request, response)) {
            return null;
        }
        userService.sendRegisterVerifyCode(syusrinf);
        return Response.SUCCESS();
    }

    @RequestMapping("sendResetVerifyCode")
    @ResponseBody
    public Response sendResetVerifyCode(Syusrinf syusrinf,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws ConException, ServletException, IOException {
        /**post requests permitted only */
        if (!checkValidRequestMethod(request, response)) {
            return null;
        }
        userService.sendResetVerifyCode(syusrinf);
        return Response.SUCCESS();
    }

    @RequestMapping("registerVerifyCode")
    @ResponseBody
    public Response registerVerifyCode(Syvrymbl syvrymbl,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws ConException, ServletException, IOException {
        /**post requests permitted only */
        if (!checkValidRequestMethod(request, response)) {
            return null;
        }
        userService.verifyCode(syvrymbl);
        return Response.SUCCESS();
    }

    @RequestMapping("resetVerifyCode")
    @ResponseBody
    public Response resetVerifyCode(Syvrymbl syvrymbl,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws ConException, ServletException, IOException {
        /**post requests permitted only */
        if (!checkValidRequestMethod(request, response)) {
            return null;
        }
        return Response.SUCCESS(userService.verifyCode(syvrymbl));
    }

    @RequestMapping("resetPassword")
    @ResponseBody
    public Response resetPassword(Syusrinf syusrinf,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws ConException, ServletException, IOException {
        /**post requests permitted only */
        if (!checkValidRequestMethod(request, response)) {
            return null;
        }
        userService.resetPassword(syusrinf);
        return Response.SUCCESS();
    }

}
