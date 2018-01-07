package com.snow.service.sy;

import com.snow.main.BaseService;
import com.snow.main.ConException;
import com.snow.mapper.sy.SyusrinfMapper;
import com.snow.model.sy.Syusrinf;
import com.snow.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Description: TODO.
 * User: kevin
 * Date: 2018/1/1
 * Time: 下午12:38
 */
@Service
public class UserService extends BaseService {

    public static final String USER_KEY = "USER_KEY";

    @Resource
    SyusrinfMapper syusrinfMapper;

    public void register(Syusrinf syusrinf) throws ConException {

        if (StringUtils.isEmpty(syusrinf.getSuiusrnam())) {
            throw new ConException("用户名不能为空");
        }
        if (StringUtils.isEmpty(syusrinf.getSuimobile())) {
            throw new ConException("手机号不能为空");
        }
        if (StringUtils.isEmpty(syusrinf.getSuipaswrd())) {
            throw new ConException("密码不能为空");
        }
        if (syusrinfMapper.queryByMobile(syusrinf.getSuimobile()) != null) {
            throw new ConException("该手机号已经注册");
        }

        syusrinf.setSuiseqcod(commonMapper.sequence());
        syusrinf.setSuiverson(new Date());
        syusrinfMapper.insert(syusrinf);

        logger.info("注册用户名：" + syusrinf.getSuiusrnam() + " 手机号：" + syusrinf.getSuimobile());
    }

    public Syusrinf login(Syusrinf syusrinf, HttpSession session) throws ConException {

        if (StringUtils.isEmpty(syusrinf.getSuimobile())) {
            throw new ConException("手机号不能为空");
        }
        if (StringUtils.isEmpty(syusrinf.getSuipaswrd())) {
            throw new ConException("密码不能为空");
        }

        Syusrinf user = syusrinfMapper.queryByMobile(syusrinf.getSuimobile());
        if (user == null || !syusrinf.getSuipaswrd().equals(user.getSuipaswrd())) {
            throw new ConException("手机号或密码错误");
        }

        user.setSuipaswrd(null);
        session.setAttribute(USER_KEY, user);
        logger.info("登陆用户名:" + user.getSuiusrnam());
        return user;
    }

    public void logOut(HttpSession session) {
        Syusrinf syusrinf = (Syusrinf) session.getAttribute(UserService.USER_KEY);
        logger.info("注销用户名:" + syusrinf.getSuiusrnam() + " sessionID:" + session.getId());

        session.removeAttribute(USER_KEY);
    }

    public Syusrinf getUserInfoById(Syusrinf syusrinf) throws ConException {
        if (StringUtils.isEmpty(syusrinf.getSuiseqcod())) {
            throw new ConException("用户ID不能为空");
        }
        Syusrinf user = syusrinfMapper.selectByPrimaryKey(syusrinf.getSuiseqcod());
        if (user == null) {
            throw new ConException("用户不存在");
        }
        user.setSuipaswrd(null);
        return user;
    }
}
