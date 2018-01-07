package com.snow.controller.main;

import com.snow.main.BaseController;
import com.snow.main.ConException;
import com.snow.main.NotFoundException;
import com.snow.main.Response;
import com.snow.service.main.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Description: 该controller部分方法在正式项目中不需要,通过浏览器访问域名只需要显示web.xml中配置的首页.
 * User: kevin
 * Date: 2017/12/28
 * Time: 下午2:24
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

    @Resource
    IndexService indexService;

    /**
     * 通过该测试方法可以验证数据库是否正常
     * @return
     * @throws ConException
     */
    @RequestMapping("hello")
    @ResponseBody
    public Response index() throws ConException {
        return Response.SUCCESS(indexService.index());
    }

    /**
     * 未来如果需要在浏览器访问域名时呈现更多页面，可以通过如下方式实现
     * @return
     */
    @RequestMapping("page")
    public String page() {
        return "main/page";
    }

    /**
     * web.xml中404错误时由该方法处理
     * @throws NotFoundException
     */
    @RequestMapping("error")
    @ResponseBody
    public void error() throws NotFoundException {
        throw new NotFoundException("请求路径未找到");
    }



}
