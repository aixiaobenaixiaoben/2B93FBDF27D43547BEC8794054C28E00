package com.snow.service.sy;

import com.snow.main.BaseService;
import com.snow.main.ConException;
import com.snow.main.DupException;
import com.snow.mapper.sy.SyusrinfMapper;
import com.snow.mapper.sy.SyvrymblMapper;
import com.snow.model.sy.Syusrinf;
import com.snow.model.sy.Syvrymbl;
import com.snow.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    @Resource
    SyvrymblMapper syvrymblMapper;

    @Transactional(rollbackFor = Exception.class)
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

    @Transactional(rollbackFor = Exception.class)
    public void updateUserInfo(Syusrinf syusrinf) throws ConException {
        if (StringUtils.isEmpty(syusrinf.getSuiseqcod())) {
            throw new ConException("用户ID不能为空");
        }
        if (StringUtils.isEmpty(syusrinf.getSuiusrnam())) {
            throw new ConException("用户名不能为空");
        }
        syusrinf.setOldverson(syusrinf.getSuiverson());
        syusrinf.setSuiverson(new Date());
        int count = syusrinfMapper.updateByPrimaryKeySelective(syusrinf);
        if (count == 0) {
            throw new DupException();
        }
    }

    /**
     * TODO
     */
    public List<Syusrinf> getUserList() {
        return syusrinfMapper.getUserList();
    }

    @Transactional(rollbackFor = Exception.class)
    public void sendRegisterVerifyCode(Syusrinf syusrinf) throws ConException {
        if (StringUtils.isEmpty(syusrinf.getSuimobile())) {
            throw new ConException("手机号不能为空");
        }
        Syusrinf existSyusrinf = syusrinfMapper.queryByMobile(syusrinf.getSuimobile());
        if (existSyusrinf != null) {
            throw new ConException("该手机号已经注册");
        }
        sendVerifyCode(syusrinf);
    }

    @Transactional(rollbackFor = Exception.class)
    public void sendResetVerifyCode(Syusrinf syusrinf) throws ConException {
        if (StringUtils.isEmpty(syusrinf.getSuimobile())) {
            throw new ConException("手机号不能为空");
        }
        Syusrinf existSyusrinf = syusrinfMapper.queryByMobile(syusrinf.getSuimobile());
        if (existSyusrinf == null) {
            throw new ConException("该手机号未注册");
        }
        sendVerifyCode(syusrinf);
    }

    private void sendVerifyCode(Syusrinf syusrinf) throws ConException {
        Date current = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        calendar.add(Calendar.MINUTE, 10);
        Date expire = calendar.getTime();

        Syvrymbl syvrymblCond = new Syvrymbl();
        syvrymblCond.setSvmmobile(syusrinf.getSuimobile());
        Syvrymbl existSyvrymbl = syvrymblMapper.selectByMobile(syvrymblCond);

        /** 验证码已存在、未校验、未过期，延后过期时间,重新发送 */
        if (existSyvrymbl != null && "0".equals(existSyvrymbl.getSvmusebfr()) && current.compareTo(existSyvrymbl.getSvmexpire()) < 0) {
            existSyvrymbl.setSvmexpire(expire);
            syvrymblMapper.updateByPrimaryKey(existSyvrymbl);

            sendMobileVerifyCode(existSyvrymbl);
            return;
        }

        /** 否则发送一个新的验证码 */
        Syvrymbl newSyvrymbl = new Syvrymbl();
        newSyvrymbl.setSvmseqcod(commonMapper.sequence());
        newSyvrymbl.setSvmmobile(syusrinf.getSuimobile());
        newSyvrymbl.setSvmvrycod(String.format("%06d", new Random().nextInt(1000000)));
        newSyvrymbl.setSvmusebfr("0");
        newSyvrymbl.setSvmexpire(expire);
        syvrymblMapper.insert(newSyvrymbl);

        sendMobileVerifyCode(newSyvrymbl);
    }

    @Transactional(rollbackFor = Exception.class)
    public Syusrinf verifyCode(Syvrymbl syvrymbl) throws ConException {
        if (StringUtils.isEmpty(syvrymbl.getSvmmobile())) {
            throw new ConException("手机号不能为空");
        }
        if (StringUtils.isEmpty(syvrymbl.getSvmvrycod())) {
            throw new ConException("验证码不能为空");
        }
        syvrymbl.setSvmusebfr("0");
        Syvrymbl existSyvrymbl = syvrymblMapper.selectByMobile(syvrymbl);
        if (existSyvrymbl == null) {
            throw new ConException("请先发送验证码");
        }
        Date current = new Date();
        if (current.compareTo(existSyvrymbl.getSvmexpire()) > 0) {
            throw new ConException("验证码已经过期，请重新发送");
        }
        if (!syvrymbl.getSvmvrycod().equals(existSyvrymbl.getSvmvrycod())) {
            throw new ConException("验证码错误");
        }

        existSyvrymbl.setSvmusebfr("1");
        syvrymblMapper.updateByPrimaryKeySelective(existSyvrymbl);

        Syusrinf syusrinf = syusrinfMapper.queryByMobile(existSyvrymbl.getSvmmobile());
        if (syusrinf != null) {
            syusrinf.setSuipaswrd(null);
        }
        return syusrinf;
    }

    /**
     * TODO
     */
    private void sendMobileVerifyCode(Syvrymbl syvrymbl) {
        System.out.println("VERIFY MOBILE: " + syvrymbl.getSvmmobile() + " : " + syvrymbl.getSvmvrycod());
    }

    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(Syusrinf syusrinf) throws ConException {
        if (StringUtils.isEmpty(syusrinf.getSuiseqcod())) {
            throw new ConException("用户ID不能为空");
        }
        if (StringUtils.isEmpty(syusrinf.getSuipaswrd())) {
            throw new ConException("新密码不能为空");
        }
        Syusrinf existSyusrinf = syusrinfMapper.selectByPrimaryKey(syusrinf.getSuiseqcod());
        if (existSyusrinf == null) {
            throw new ConException("用户不存在");
        }
        existSyusrinf.setSuipaswrd(syusrinf.getSuipaswrd());
        existSyusrinf.setOldverson(syusrinf.getSuiverson());
        existSyusrinf.setSuiverson(new Date());
        int count = syusrinfMapper.updateByPrimaryKeySelective(existSyusrinf);
        if (count == 0) {
            throw new DupException();
        }
    }

}
