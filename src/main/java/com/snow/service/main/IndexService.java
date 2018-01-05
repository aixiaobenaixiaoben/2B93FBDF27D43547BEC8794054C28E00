package com.snow.service.main;

import com.snow.mapper.sy.SyusrinfMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Description: TODO.
 * User: kevin
 * Date: 2017/12/29
 * Time: 上午9:27
 */
@Service
public class IndexService {

    @Resource
    SyusrinfMapper syusrinfMapper;

    @Transactional(rollbackFor = Exception.class)
    public String index() {
        return "hello world" + syusrinfMapper.getUserCount();
    }

}
