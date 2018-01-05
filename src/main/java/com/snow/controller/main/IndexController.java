package com.snow.controller.main;

import com.snow.main.BaseController;
import com.snow.service.main.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Description: TODO.
 * User: kevin
 * Date: 2017/12/28
 * Time: 下午2:24
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

    @Resource
    IndexService indexService;

    @RequestMapping("hello")
    @ResponseBody
    public String index() {
        return indexService.index();
    }

    @RequestMapping("page")
    public String page() {
        return "main/page";
    }



}
