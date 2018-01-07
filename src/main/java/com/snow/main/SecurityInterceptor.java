package com.snow.main;

import com.snow.model.sy.Syusrinf;
import com.snow.service.sy.UserService;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Description: TODO.
 * User: kevin
 * Date: 2018/1/1
 * Time: 下午12:29
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    public static final String POST = "POST";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnauthorizedException, ServletException, IOException {
        /**post requests permitted only */
        if (!POST.equalsIgnoreCase(request.getMethod())) {
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        }

        HttpSession session = request.getSession();
        Syusrinf syusrinf = (Syusrinf) session.getAttribute(UserService.USER_KEY);
        if (syusrinf == null) {
            throw new UnauthorizedException("请先登陆");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
