package com.snow.main;

import com.snow.mapper.main.CommonMapper;
import org.apache.log4j.Logger;

import javax.annotation.Resource;

/**
 * Description: TODO.
 * User: kevin
 * Date: 2018/1/6
 * Time: 下午3:40
 */
public class BaseService {

    protected static final Logger logger = Logger.getLogger(BaseService.class);

    @Resource
    protected CommonMapper commonMapper;

}
