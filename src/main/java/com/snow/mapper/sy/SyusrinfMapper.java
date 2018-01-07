package com.snow.mapper.sy;

import com.snow.model.sy.Syusrinf;

public interface SyusrinfMapper {
    int deleteByPrimaryKey(Syusrinf record);

    int insert(Syusrinf record);

    int insertSelective(Syusrinf record);

    Syusrinf selectByPrimaryKey(String suiseqcod);

    int updateByPrimaryKeySelective(Syusrinf record);

    int updateByPrimaryKey(Syusrinf record);

    Long getUserCount();

    Syusrinf queryByMobile(String suimobile);
}