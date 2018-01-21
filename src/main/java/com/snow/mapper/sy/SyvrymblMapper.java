package com.snow.mapper.sy;

import com.snow.model.sy.Syvrymbl;

public interface SyvrymblMapper {
    int deleteByPrimaryKey(String svmseqcod);

    int insert(Syvrymbl record);

    int insertSelective(Syvrymbl record);

    Syvrymbl selectByPrimaryKey(String svmseqcod);

    int updateByPrimaryKeySelective(Syvrymbl record);

    int updateByPrimaryKey(Syvrymbl record);

    Syvrymbl selectByMobile(Syvrymbl syvrymbl);
}