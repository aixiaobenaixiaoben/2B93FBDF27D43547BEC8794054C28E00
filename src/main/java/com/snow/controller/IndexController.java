package com.snow.controller;

import com.snow.Domain.Item;
import com.snow.service.IndexService;
import com.snow.util.SpringContextUtil;
import org.apache.log4j.Logger;
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
public class IndexController {

    private static Logger logger = Logger.getLogger(IndexController.class);

    @Resource
    IndexService indexService;

    @RequestMapping("hello")
    @ResponseBody
    public String index() {
        logger.info("hello method is called");
        return indexService.index();
    }

    @RequestMapping("hello2")
    @ResponseBody
    public String index2() {
        logger.info("hello2 method is called");
        IndexService bean = (IndexService) SpringContextUtil.getBean("indexService");
        return bean.index();
    }

    @RequestMapping("item")
    @ResponseBody
    public Item getItem() {
        return indexService.getItem();
    }

    @RequestMapping("input")
    @ResponseBody
    public void inputItem(Item item) {
        System.out.println("input method,city:" + item.getCity() + " id:" + item.getId());
    }

    @RequestMapping("page")
    public String page() {
        return "main/page";
    }



}
