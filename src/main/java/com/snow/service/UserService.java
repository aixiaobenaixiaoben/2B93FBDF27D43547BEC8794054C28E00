package com.snow.service;

import com.snow.Domain.Item;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Description: TODO.
 * User: kevin
 * Date: 2018/1/1
 * Time: 下午12:38
 */
@Service
public class UserService {

    public static final String USER_KEY = "USER_KEY";

    public void login(Item item, HttpSession session) {
        session.setAttribute(USER_KEY, item);
    }

    public void logOut(HttpSession session) {
        session.removeAttribute(USER_KEY);
    }
}
