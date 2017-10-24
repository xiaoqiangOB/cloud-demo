package com.ahut.core.dao;

import com.ahut.core.dao.entity.Demo;

public interface DemoMapper {
    int insert(Demo record);

    int insertSelective(Demo record);

    Demo query(Demo record);
}